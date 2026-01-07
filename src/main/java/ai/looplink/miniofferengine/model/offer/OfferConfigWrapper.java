package ai.looplink.miniofferengine.model.offer;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class OfferConfigWrapper {
    private List<OfferConfig> offers;
}
