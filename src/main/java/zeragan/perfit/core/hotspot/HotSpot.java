package zeragan.perfit.core.hotspot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import zeragan.perfit.core.Node;

public class HotSpot implements Node {

    private final HotSpot parent;

    private final List<HotSpot> children = new ArrayList<>();

    private final UUID sourceId;

    private final String threadName;

    private final String spotName;

    private final TimeUnit timeUnit;

    private long totalTime = 0;

    private long innerTime = 0;

    private int hits = 0;

    public HotSpot() {
        this(null, null, null, null, null);
    }

    public HotSpot(Node initialNode) {
        this(null, initialNode.getSourceId(), initialNode.getThreadName(), initialNode.getNodeName(), initialNode.getTimeUnit());
    }

    public HotSpot(String hotspotName, TimeUnit timeUnit) {
        this(null, null, null, hotspotName, timeUnit);
    }

    public HotSpot(HotSpot parent, String hotspotName, TimeUnit timeUnit) {
        this(parent, null, null, hotspotName, timeUnit);
    }

    public HotSpot(HotSpot parent, UUID sourceId, String threadName, String hotspotName, TimeUnit timeUnit) {
        this.parent = parent;
        this.sourceId = sourceId;
        this.threadName = threadName;
        this.spotName = hotspotName;
        this.timeUnit = timeUnit;
        if (this.parent != null) {
            this.parent.addChild(this);
        }
    }

    public void add(Node other) {
        // TODO : vérifier que other possède le même sourceId, threadName, nodeName et timeUnit
        this.totalTime += other.getTotalTime();
        this.innerTime += other.getInnerTime();
        this.hits++;
    }

    @Override
    public UUID getSourceId() {
        return sourceId;
    }

    @Override
    public String getThreadName() {
        return threadName;
    }

    @Override
    public String getNodeName() {
        return spotName;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public long getTotalTime() {
        return totalTime;
    }

    @Override
    public long getInnerTime() {
        return innerTime;
    }

    public int getHitCount() {
        return hits;
    }

    public double getAverageInnerTime() {
        return innerTime / hits;
    }

    public double getAverageTotalTime() {
        return totalTime / hits;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HotSpot [spotName=");
        builder.append(spotName);
        builder.append(", innerTime=");
        builder.append(innerTime);
        builder.append(", totalTime=");
        builder.append(totalTime);
        builder.append(", hits=");
        builder.append(hits);
        builder.append("]");
        return builder.toString();
    }

    private void addChild(HotSpot child) {
        children.add(child);
    }

}
