package zeragan.perfit.other;

import org.xml.sax.InputSource;

import zeragan.perfit.core.NodeSource;
import zeragan.perfit.core.NodeSourceAdapter;
import zeragan.perfit.core.configuration.NodeSourceBuilder;

public class ConfigurableNodeSource implements NodeSourceAdapter
{

    private final NodeSource nodeSource;

    public ConfigurableNodeSource(String filepath) throws Exception
    {

        InputSource source = new InputSource(filepath);
        nodeSource = NodeSourceBuilder.build(source);

    }

    @Override
    public NodeSource getNodeSource()
    {
        return nodeSource;
    }

}
