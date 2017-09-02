package zeragan.perfit.core.collector;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import zeragan.perfit.core.hotspot.HotSpot;

public final class CollectedData {

    private final LocalDateTime datetime = LocalDateTime.now();

    private final UUID sourceId;

    private final TimeUnit timeUnit;

    private final List<HotSpot> nodes = new ArrayList<>();

    public CollectedData(UUID sourceId, TimeUnit timeUnit) {
        this.sourceId = sourceId;
        this.timeUnit = timeUnit;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public UUID getSourceId() {
        return sourceId;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public List<HotSpot> getHotSpots() {
        return nodes;
    }

    void setHotSpots(List<HotSpot> hotSpots) {
        nodes.addAll(hotSpots);
    }

}
