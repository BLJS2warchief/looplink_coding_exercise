package ai.looplink.miniofferengine.model;

import lombok.Data;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class Transaction {
    private String transactionId;
    private String shopperId;
    private String storeId;
    private Instant timestamp;
    private List<TransactionItem> items;
}
