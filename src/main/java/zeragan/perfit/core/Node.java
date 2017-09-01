package zeragan.perfit.core;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public interface Node
{

    UUID getSourceId();

    String getThreadName();

    String getNodeName();

    TimeUnit getTimeUnit();

    long getTotalTime();

    long getInnerTime();

}