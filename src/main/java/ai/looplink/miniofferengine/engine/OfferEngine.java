package ai.looplink.miniofferengine.engine;

import ai.looplink.miniofferengine.model.Transaction;

public interface OfferEngine {
    OfferResult apply(Transaction transaction);
}
