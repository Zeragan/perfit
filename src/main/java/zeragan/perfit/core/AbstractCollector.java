package zeragan.perfit.core;

import java.io.IOException;

import org.w3c.dom.NodeList;

public abstract class AbstractCollector implements NodeCollector
{

    protected AbstractCollector()
    {
        super();
    }

    protected AbstractCollector(org.w3c.dom.Node conf) throws IOException, ReflectiveOperationException
    {
        super();

        NodeList parameters = conf.getChildNodes();
        for (int index = 0; index < parameters.getLength(); index++)
        {
            org.w3c.dom.Node parameter = parameters.item(index);
            if ("logOnShutdown".equals(parameter.getNodeName()))
            {
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        System.err.println(AbstractCollector.this.toString());
                    }
                }));
            }
        }
    }

    @Override
    public boolean isActive()
    {
        return true;
    }

    @Override
    public void enter(Node node)
    {

    }

    @Override
    public void exit(Node node)
    {

    }

}
