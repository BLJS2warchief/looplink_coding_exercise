package ai.looplink.miniofferengine.controller;

import ai.looplink.miniofferengine.model.RedemptionReward;
import ai.looplink.miniofferengine.model.request.RedemptionRequest;
import ai.looplink.miniofferengine.store.InMemoryStore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redemptions")
@Tag(name = "Redemptions")
public class RedemptionController {

    private final InMemoryStore store;

    public RedemptionController(InMemoryStore store) {
        this.store = store;
    }

    @Operation(summary = "Redeem stickers for a reward")
    @PostMapping("/{shopperId}")
    public String redeem(
            @PathVariable("shopperId") String shopperId,
            @RequestBody RedemptionRequest request) {

        RedemptionReward reward =
                RedemptionReward.valueOf(request.getReward());

        store.redeem(shopperId, reward);

        return "Redeemed " + reward.getDisplayName()
                + " for " + reward.getCost() + " stickers";
    }
}