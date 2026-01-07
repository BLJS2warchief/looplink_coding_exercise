package ai.looplink.miniofferengine.model.offer;

import ai.looplink.miniofferengine.model.OfferType;
import lombok.Data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class OfferConfig {
    private String id;
    private OfferType type;
    private String name;
    private Criteria criteria;
    private Discount discount;
    private StickerRules rules;
}
