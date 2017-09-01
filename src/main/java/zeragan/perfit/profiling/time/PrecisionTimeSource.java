package zeragan.perfit.profiling.time;

import java.util.concurrent.TimeUnit;

public class PrecisionTimeSource implements TimeSource
{

    @Override
    public long tick()
    {
        return System.nanoTime();
    }

    @Override
    public TimeUnit unit()
    {
        return TimeUnit.NANOSECONDS;
    }

}
