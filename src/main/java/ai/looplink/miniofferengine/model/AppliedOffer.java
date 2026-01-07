package ai.looplink.miniofferengine.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
public class AppliedOffer {
    private String offerId;
    private OfferType type;
    private String description;
    private BigDecimal discountAmount;
}
