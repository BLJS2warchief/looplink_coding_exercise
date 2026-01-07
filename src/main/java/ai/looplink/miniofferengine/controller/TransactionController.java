package ai.looplink.miniofferengine.controller;

import ai.looplink.miniofferengine.engine.OfferEngine;
import ai.looplink.miniofferengine.engine.OfferResult;
import ai.looplink.miniofferengine.model.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions")
public class TransactionController {

    private final OfferEngine offerEngine;

    public TransactionController(OfferEngine offerEngine) {
        this.offerEngine = offerEngine;
    }

    @Operation(summary = "Process a transaction and apply offers")
    @PostMapping
    public OfferResult process(@RequestBody Transaction transaction) {
        return offerEngine.apply(transaction);
    }
}
