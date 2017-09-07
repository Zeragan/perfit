package zeragan.perfit.core.hotspot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import zeragan.perfit.core.Node;

public class HotSpotCollection implements Iterable<HotSpot> {

    private final HotSpotCollection parent;

    private final Map<String, HotSpotCollection> children = new HashMap<>();

    private final Map<String, HotSpot> hotSpots = new HashMap<>();

    public HotSpotCollection() {
        this.parent = null;
    }

    private HotSpotCollection(HotSpotCollection parent) {
        this.parent = parent;
    }

    public HotSpotCollection enter(Node n) {
        return getChild(n);
    }

    public HotSpotCollection exit(Node n) {
        parent.add(n);
        return parent;
    }

    public void add(Node n) {
        HotSpot hotSpot = getHotSpot(n);
        hotSpot.add(n);
    }

    public List<HotSpot> toList() {
        return new ArrayList<>(hotSpots.values());
    }

    private HotSpot getHotSpot(Node n) {
        HotSpot hotSpot = hotSpots.get(n.getNodeName());
        if (hotSpot == null) {
            hotSpot = new HotSpot(n);
            hotSpots.put(n.getNodeName(), hotSpot);
            children.put(n.getNodeName(), new HotSpotCollection(this));
        }
        return hotSpot;
    }

    private HotSpotCollection getChild(Node n) {
        HotSpotCollection child = children.get(n.getNodeName());
        if (child == null) {
            child = new HotSpotCollection(this);
            hotSpots.put(n.getNodeName(), new HotSpot(n));
            children.put(n.getNodeName(), child);
        }
        return child;
    }

    @Override
    public Iterator<HotSpot> iterator() {
        return hotSpots.values().iterator();
    }

}
