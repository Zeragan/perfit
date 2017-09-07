package zeragan.perfit.core.collector;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import zeragan.perfit.core.hotspot.HotSpotCollection;

public final class CollectedData {

    private final LocalDateTime datetime = LocalDateTime.now();

    private final UUID sourceId;

    private final TimeUnit timeUnit;

    private HotSpotCollection hotSpots;

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

    public HotSpotCollection getHotSpots() {
        return hotSpots;
    }

    void setData(HotSpotCollection dataview) {
        this.hotSpots = dataview;
    }

}
