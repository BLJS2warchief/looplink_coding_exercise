package ai.looplink.miniofferengine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedemptionReward {
    MUG("Mug", 10),
    TOTE_BAG("Tote Bag", 20);

    private final String displayName;
    private final int cost;
}