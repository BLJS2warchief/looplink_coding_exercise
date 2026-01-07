package ai.looplink.miniofferengine.model.offer;

import lombok.Data;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class StickerRules {
    private BigDecimal spendPerSticker;
    private String promoCategory;
    private int promoBonusPerUnit;
    private int perTransactionCap;
}
