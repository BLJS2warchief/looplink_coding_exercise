package ai.looplink.miniofferengine.model.response;

import lombok.Data;

import java.util.List;

@Data
public class ShopperSummaryResponse {
    private String shopperId;
    private int stickerBalance;
    private List<String> transactions;
}
