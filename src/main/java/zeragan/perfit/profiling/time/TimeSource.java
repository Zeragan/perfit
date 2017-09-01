package zeragan.perfit.profiling.time;

import java.util.concurrent.TimeUnit;

public interface TimeSource
{

    long tick();

    TimeUnit unit();

}
