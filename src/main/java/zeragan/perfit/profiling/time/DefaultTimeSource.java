package zeragan.perfit.profiling.time;

import java.util.concurrent.TimeUnit;

public class DefaultTimeSource implements TimeSource
{

    @Override
    public long tick()
    {
        return System.currentTimeMillis();
    }

    @Override
    public TimeUnit unit()
    {
        return TimeUnit.MILLISECONDS;
    }

}
