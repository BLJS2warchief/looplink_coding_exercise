package ai.looplink.miniofferengine.engine;

import ai.looplink.miniofferengine.model.AppliedOffer;
import ai.looplink.miniofferengine.model.Transaction;
import ai.looplink.miniofferengine.model.TransactionItem;
import ai.looplink.miniofferengine.model.offer.OfferConfig;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ConfigurableOfferEngine implements OfferEngine {
    private final List<OfferConfig> offers;

    public ConfigurableOfferEngine(List<OfferConfig> offers) {
        this.offers = offers;
    }

    @Override
    public OfferResult apply(Transaction transaction) {

        BigDecimal originalTotal = calculateOriginalTotal(transaction);
        BigDecimal runningTotal = originalTotal;
        List<AppliedOffer> appliedOffers = new ArrayList<>();
        int stickersEarned = 0;

        for (OfferConfig offer : offers) {
            switch (offer.getType()) {
                case PRODUCT_PERCENT_DISCOUNT: runningTotal = runningTotal.subtract(
                        applyProductDiscount(offer, transaction, appliedOffers)
                );
                    break;
                case BOGO: runningTotal = runningTotal.subtract(
                        applyBogo(offer, transaction, appliedOffers)
                );
                    break;
                case CART_FIXED_DISCOUNT: runningTotal = runningTotal.subtract(
                        applyCartDiscount(offer, originalTotal, appliedOffers)
                );
                    break;
                case STICKER_EARN: stickersEarned = calculateStickers(offer, transaction, originalTotal);
                    break;
            }
        }
        return new OfferResult(
                originalTotal,
                runningTotal.max(BigDecimal.ZERO),
                appliedOffers,
                stickersEarned
        );
    }

    private BigDecimal calculateOriginalTotal(Transaction transaction) {
        return transaction.getItems().stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal applyProductDiscount(OfferConfig offer, Transaction transaction, List<AppliedOffer> applied) {
        BigDecimal discount = transaction.getItems().stream()
                .filter(i -> offer.getCriteria().getSkuIn().contains(i.getSku()))
                .map(i -> i.getUnitPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity()))
                        .multiply(offer.getDiscount().getPercent()
                                .divide(BigDecimal.valueOf(100))))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        if (discount.compareTo(BigDecimal.ZERO) > 0) {
            applied.add(new AppliedOffer(
                    offer.getId(),
                    offer.getType(),
                    offer.getName(),
                    discount
            ));
        }
        return discount;
    }

    private BigDecimal applyBogo(OfferConfig offer, Transaction transaction, List<AppliedOffer> applied) {
        return transaction.getItems().stream()
                .filter(i -> offer.getCriteria().getSku().equals(i.getSku()))
                .map(i -> {
                    int freeUnits = i.getQuantity() / (offer.getDiscount().getBuyQty() + offer.getDiscount().getGetQty());
                    BigDecimal d = i.getUnitPrice().multiply(BigDecimal.valueOf(freeUnits));
                    applied.add(new AppliedOffer(
                            offer.getId(),
                            offer.getType(),
                            offer.getName(),
                            d
                    ));
                    return d;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal applyCartDiscount(OfferConfig offer, BigDecimal originalTotal, List<AppliedOffer> applied) {
        if (originalTotal.compareTo(offer.getCriteria().getMinCartTotal()) >= 0) {
            BigDecimal d = offer.getDiscount().getAmount();
            applied.add(new AppliedOffer(
                    offer.getId(),
                    offer.getType(),
                    offer.getName(),
                    d
            ));
            return d;
        }
        return BigDecimal.ZERO;
    }

    private int calculateStickers(OfferConfig offer, Transaction transaction, BigDecimal originalTotal) {
        int base = originalTotal
                .divide(offer.getRules().getSpendPerSticker(), RoundingMode.FLOOR)
                .intValue();

        int promo = transaction.getItems().stream()
                .filter(i -> offer.getRules().getPromoCategory().equalsIgnoreCase(i.getCategory()))
                .mapToInt(TransactionItem::getQuantity)
                .sum();

        return Math.min(base + promo, offer.getRules().getPerTransactionCap());
    }
}
