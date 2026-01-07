package ai.looplink.miniofferengine.model.offer;

import lombok.Data;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class Discount {
    private BigDecimal percent;
    private BigDecimal amount;
    private String currency;
    private int buyQty;
    private int getQty;
}
