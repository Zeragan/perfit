package zeragan.perfit.util.reporting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.w3c.dom.Node;

import zeragan.perfit.core.reporting.TextualReportPublisher;

public class FilePublisher implements TextualReportPublisher {

    private final String fileName;

    public FilePublisher(Node confNode) {
        this.fileName = confNode.getAttributes().getNamedItem("filename").getNodeValue();
    }

    @Override
    public void publish(String text) {
        try {
            Files.write(Paths.get(fileName), text.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
