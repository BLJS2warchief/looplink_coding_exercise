package ai.looplink.miniofferengine.controller;

import ai.looplink.miniofferengine.engine.OfferEngine;
import ai.looplink.miniofferengine.engine.OfferResult;
import ai.looplink.miniofferengine.model.Transaction;
import ai.looplink.miniofferengine.service.TransactionService;
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

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @Operation(summary = "Process a transaction and apply offers")
    @PostMapping
    public OfferResult process(@RequestBody Transaction transaction) {
        return service.process(transaction);
    }
}
