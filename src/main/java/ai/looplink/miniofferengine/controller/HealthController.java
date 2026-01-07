package ai.looplink.miniofferengine.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health", description = "Service health and liveness checks")
public class HealthController {
    @GetMapping("/health")
    public String health() {
        return "APPLICATION UP";
    }
}