package ai.looplink.miniofferengine.engine;

import ai.looplink.miniofferengine.model.AppliedOffer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class OfferResult {
    private final BigDecimal originalTotal;
    private final BigDecimal finalTotal;
    private final List<AppliedOffer> appliedOffers;
    private final int stickersEarned;

    public OfferResult(BigDecimal originalTotal,
                       BigDecimal finalTotal,
                       List<AppliedOffer> appliedOffers,
                       int stickersEarned) {
        this.originalTotal = originalTotal;
        this.finalTotal = finalTotal;
        this.appliedOffers = appliedOffers;
        this.stickersEarned = stickersEarned;
    }
}
