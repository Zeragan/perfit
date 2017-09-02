package zeragan.perfit.core.collector;

import java.io.IOException;

import zeragan.perfit.core.Node;
import zeragan.perfit.core.hotspot.HotSpotCollection;

public class HotSpotCollector extends AbstractCollector {

    private final HotSpotCollection collection = new HotSpotCollection();

    public HotSpotCollector(org.w3c.dom.Node conf) throws IOException, ReflectiveOperationException {
        super(conf);
    }

    @Override
    public void exit(Node node) {
        collection.add(node);
    }

    @Override
    public CollectedData getData() {
        CollectedData data = super.getData();
        data.setHotSpots(collection.toList());
        return data;
    }

}
