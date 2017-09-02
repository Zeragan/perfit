package zeragan.perfit.core.hotspot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zeragan.perfit.core.Node;

public class HotSpotCollection {

    private Map<String, HotSpot> hotSpots = new HashMap<>();

    public void add(Node n) {
        HotSpot hotSpot = hotSpots.get(n.getNodeName());
        if (hotSpot == null) {
            hotSpot = new HotSpot(n.getSourceId(), n.getThreadName(), n.getNodeName(), n.getTimeUnit());
            hotSpots.put(n.getNodeName(), hotSpot);
        }
        hotSpot.add(n);
    }

    public List<HotSpot> toList() {
        return new ArrayList<>(hotSpots.values());
    }

}
