package zeragan.perfit.profiling;

import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;

import zeragan.perfit.core.AbstractNodeSource;
import zeragan.perfit.profiling.time.DefaultTimeSource;
import zeragan.perfit.profiling.time.TimeSource;

public final class Profiler extends AbstractNodeSource {

    private final TimeSource timeSource;

    private ProfiledNode currentNode;

    public Profiler(org.w3c.dom.Node conf) throws ReflectiveOperationException, DOMException {
        TimeSource timeSource = new DefaultTimeSource();
        NodeList childNodes = conf.getChildNodes();
        for (int index = 0; index < childNodes.getLength(); index++) {
            org.w3c.dom.Node confNode = childNodes.item(index);
            if ("timesource".equals(confNode.getNodeName())) {
                org.w3c.dom.Node timeSourceName = confNode.getAttributes().getNamedItem("type");
                timeSource = (TimeSource) Class.forName(timeSourceName.getNodeValue()).newInstance();
                break;
            }
        }
        this.timeSource = timeSource;
        setTimeUnit(timeSource.unit());
    }

    public final synchronized void enter(String nodeName) throws InterruptedException {
        if (!hasActiveCollector()) {
            return;
        }

        long beginProfiling = timeSource.tick(); // begin profiling

        String threadName = Thread.currentThread().getName();
        if (currentNode != null && !threadName.equals(currentNode.getThreadName())) {
            wait();
        }

        currentNode = new ProfiledNode(currentNode, threadName, nodeName);
        currentNode.setBeginProfiling(beginProfiling);

        try {
            notifyEnter(currentNode);
        } finally {
            currentNode.setBegin(timeSource.tick());
        }
    }

    public final synchronized void exit() {
        if (!hasActiveCollector()) {
            return;
        }

        if (currentNode != null) {
            currentNode.setEnd(timeSource.tick());

            try {
                notifyExit(currentNode);
            } finally {
                ProfiledNode previousNode = currentNode;
                currentNode = currentNode.getParent();
                previousNode.setEndProfiling(timeSource.tick());
            }
        }
    }

}
