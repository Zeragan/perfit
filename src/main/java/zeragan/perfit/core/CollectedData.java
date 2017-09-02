package zeragan.perfit.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import zeragan.perfit.core.hotspot.HotSpot;

public final class CollectedData {

    private final List<HotSpot> nodes = new ArrayList<>();

    public CollectedData() {
    }

    public CollectedData(Collection<HotSpot> nodes) {
        this.nodes.addAll(nodes);
    }

    public List<HotSpot> getList() {
        return nodes;
    }

}
