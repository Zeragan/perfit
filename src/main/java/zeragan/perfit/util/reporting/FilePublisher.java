package zeragan.perfit.util.reporting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import zeragan.perfit.core.reporting.TextualReportPublisher;

public class FilePublisher implements TextualReportPublisher {

    private final String fileName;

    public FilePublisher(Node confNode) {
        String filenameTmp = null;
        NodeList parameters = confNode.getChildNodes();
        for (int index = 0; index < parameters.getLength(); index++) {
            org.w3c.dom.Node parameter = parameters.item(index);
            if ("filename".equals(parameter.getNodeName())) {
                filenameTmp = parameter.getTextContent();
                break;
            }
        }
        this.fileName = filenameTmp;
    }

    @Override
    public void publish(String text) {
        try {
            Files.write(Paths.get(fileName), text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
