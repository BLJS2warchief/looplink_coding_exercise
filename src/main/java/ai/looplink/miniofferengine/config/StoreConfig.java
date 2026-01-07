package ai.looplink.miniofferengine.config;

import ai.looplink.miniofferengine.store.InMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreConfig {

    @Bean
    public InMemoryStore inMemoryStore() {
        return new InMemoryStore();
    }
}