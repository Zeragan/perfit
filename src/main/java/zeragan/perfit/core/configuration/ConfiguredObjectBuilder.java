package zeragan.perfit.core.configuration;

import java.io.IOException;
import java.lang.reflect.Constructor;

import org.w3c.dom.Node;

public final class ConfiguredObjectBuilder {

    private ConfiguredObjectBuilder() {

    }

    @SuppressWarnings("unchecked")
    public static <T> T build(Node configurationNode) throws IOException, ReflectiveOperationException {

        Node confNodeName = configurationNode.getAttributes().getNamedItem("type");
        T object = null;

        Class<?> objectClass = Class.forName(confNodeName.getNodeValue());
        for (Constructor<?> constructor : objectClass.getConstructors()) {
            if (constructor.getParameterTypes().length == 1 && constructor.getParameterTypes()[0].equals(Node.class)) {
                object = (T) constructor.newInstance(configurationNode);
                break;
            }
        }
        if (object == null) {
            object = (T) objectClass.newInstance();
        }

        return object;
    }

}
