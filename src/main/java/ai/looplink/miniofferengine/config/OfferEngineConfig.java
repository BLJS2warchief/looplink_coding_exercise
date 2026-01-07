package ai.looplink.miniofferengine.config;


import ai.looplink.miniofferengine.engine.ConfigurableOfferEngine;
import ai.looplink.miniofferengine.engine.OfferEngine;
import ai.looplink.miniofferengine.model.offer.OfferConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OfferEngineConfig {
    @Bean
    public OfferEngine offerEngine() {
        List<OfferConfig> offers = OfferConfigLoader.loadOffers();
        return new ConfigurableOfferEngine(offers);
    }
}
