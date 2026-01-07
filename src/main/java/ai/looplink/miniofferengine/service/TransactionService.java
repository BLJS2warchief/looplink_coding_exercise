package ai.looplink.miniofferengine.service;

import ai.looplink.miniofferengine.engine.OfferEngine;
import ai.looplink.miniofferengine.engine.OfferResult;
import ai.looplink.miniofferengine.model.Transaction;
import ai.looplink.miniofferengine.store.InMemoryStore;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final OfferEngine offerEngine;
    private final InMemoryStore store;

    public TransactionService(OfferEngine offerEngine, InMemoryStore store) {
        this.offerEngine = offerEngine;
        this.store = store;
    }

    public OfferResult process(Transaction transaction) {
        String txId = transaction.getTransactionId();

        OfferResult cached = store.getTransaction(txId);
        if (cached != null) {
            return cached;
        }

        OfferResult result = offerEngine.apply(transaction);

        store.saveTransaction(txId, result);
        store.addStickers(transaction.getShopperId(), result.getStickersEarned());
        store.addShopperTransaction(transaction.getShopperId(), txId);

        return result;
    }
}