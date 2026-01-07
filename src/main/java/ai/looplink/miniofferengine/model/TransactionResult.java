package ai.looplink.miniofferengine.model;

import java.math.BigDecimal;
import java.util.List;

public class TransactionResult {
    private String transactionId;
    private BigDecimal originalTotal;
    private BigDecimal finalTotal;
    private List<AppliedOffer> appliedOffers;
    private int stickersEarned;
}
