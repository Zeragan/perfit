package zeragan.perfit.core.collector;

import java.io.IOException;

import zeragan.perfit.core.Node;
import zeragan.perfit.core.hotspot.HotSpotCollection;

public class HotSpotCollector extends AbstractCollector {

    private HotSpotCollection collection = null;

    public HotSpotCollector(org.w3c.dom.Node conf) throws IOException, ReflectiveOperationException {
        super(conf);
    }

    @Override
    public void activate(CollectedData collectedData) {
        super.activate(collectedData);
        collection = new HotSpotCollection();
    }

    @Override
    public void exit(Node node) {
        if (isActive()) {
            collection.add(node);
        }
    }

    @Override
    public CollectedData getData() {
        CollectedData data = super.getData();
        data.setData(collection);
        return data;
    }

}
