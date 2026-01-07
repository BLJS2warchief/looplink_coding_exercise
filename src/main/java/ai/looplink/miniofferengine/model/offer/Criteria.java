package ai.looplink.miniofferengine.model.offer;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class Criteria {
    private List<String> skuIn;
    private BigDecimal minCartTotal;
    private String sku;
}
