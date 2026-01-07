package ai.looplink.miniofferengine.config;

import ai.looplink.miniofferengine.model.offer.OfferConfig;
import ai.looplink.miniofferengine.model.offer.OfferConfigWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;

public class OfferConfigLoader {
    public static List<OfferConfig> loadOffers() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("blah");
            InputStream is = new ClassPathResource("examples/sample-offers.json").getInputStream();
            System.out.println("blah");
            OfferConfigWrapper wrapper = mapper.readValue(is, OfferConfigWrapper.class);
            System.out.println("blah");
            return wrapper.getOffers();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load offer configuration", e);
        }
    }
}
