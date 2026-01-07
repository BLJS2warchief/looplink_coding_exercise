package ai.looplink.miniofferengine.model;

import java.time.Instant;
import java.util.List;

public class Transaction {
    private String transactionId;
    private String shopperId;
    private String storeId;
    private Instant timestamp;
    private List<TransactionItem> items;
}
