package ai.looplink.miniofferengine.controller;

import ai.looplink.miniofferengine.model.response.ShopperSummaryResponse;
import ai.looplink.miniofferengine.store.InMemoryStore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppers")
@Tag(name = "Shoppers")
public class ShopperController {
    private final InMemoryStore store;

    public ShopperController(InMemoryStore store) {
        this.store = store;
    }

    @Operation(summary = "Get shopper sticker balance and transaction history")
    @GetMapping("/{shopperId}")
    public ShopperSummaryResponse getShopper(@PathVariable("shopperId") String shopperId) {
        ShopperSummaryResponse response = new ShopperSummaryResponse();
        response.setShopperId(shopperId);
        response.setStickerBalance(store.getStickerBalance(shopperId));
        response.setTransactions(store.getShopperTransactions(shopperId));
        response.setRedemptions(store.getRedemptions(shopperId));

        return response;
    }
}
