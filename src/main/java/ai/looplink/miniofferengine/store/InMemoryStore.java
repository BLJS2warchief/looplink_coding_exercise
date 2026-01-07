package ai.looplink.miniofferengine.store;


import ai.looplink.miniofferengine.engine.OfferResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryStore {

    private final Map<String, OfferResult> transactions = new ConcurrentHashMap<>();

    private final Map<String, Integer> stickerBalances = new ConcurrentHashMap<>();

    private final Map<String, List<String>> shopperTransactions = new ConcurrentHashMap<>();

    public OfferResult getTransaction(String txId) {
        return transactions.get(txId);
    }

    public void saveTransaction(String txId, OfferResult result) {
        transactions.put(txId, result);
    }

    public int addStickers(String shopperId, int delta) {
        return stickerBalances.merge(shopperId, delta, Integer::sum);
    }

    public int getStickerBalance(String shopperId) {
        return stickerBalances.getOrDefault(shopperId, 0);
    }

    public void addShopperTransaction(String shopperId, String txId) {
        shopperTransactions
                .computeIfAbsent(shopperId, k -> new CopyOnWriteArrayList<>())
                .add(txId);
    }

    public List<String> getShopperTransactions(String shopperId) {
        return shopperTransactions.getOrDefault(shopperId, List.of());
    }
}