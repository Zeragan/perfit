package zeragan.perfit.util.reporting;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import zeragan.perfit.core.CollectedData;
import zeragan.perfit.core.reporting.TextualReportGenerator;

public class VelocityTemplateGenerator implements TextualReportGenerator {

    private final Template template;

    public VelocityTemplateGenerator(Node confNode) {

        String templateName = null;
        NodeList parameters = confNode.getChildNodes();
        for (int index = 0; index < parameters.getLength(); index++) {
            org.w3c.dom.Node parameter = parameters.item(index);
            if ("template".equals(parameter.getNodeName())) {
                templateName = parameter.getTextContent();
                break;
            }
        }

        Velocity.init();
        this.template = Velocity.getTemplate(templateName);
    }

    @Override
    public String generate(CollectedData data) {
        VelocityContext context = new VelocityContext();
        context.put("data", data);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

}
