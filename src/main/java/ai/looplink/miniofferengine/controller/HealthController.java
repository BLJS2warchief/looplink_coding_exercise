package ai.looplink.miniofferengine.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health", description = "Service health and liveness checks")
public class HealthController {
    @GetMapping("/health")
    @Operation(
            summary = "Health check",
            description = "Simple liveness endpoint to verify the service is running"
    )
    public String health() {
        return "APPLICATION UP";
    }
}