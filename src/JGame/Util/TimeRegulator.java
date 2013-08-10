package JGame.Util;

import java.util.concurrent.TimeUnit;

public class TimeRegulator{

    private final long periodMilliseconds;
    private long lastTick = 0L;

    public TimeRegulator(long periodMilliseconds){
        this.periodMilliseconds = periodMilliseconds;
    }

    public boolean checkTime(){
        long now = System.nanoTime();
        long diffNanos = now - lastTick;
        long diffMilliseconds = TimeUnit.NANOSECONDS.toMillis(diffNanos);
        if(diffMilliseconds < periodMilliseconds){
            return false;
        }
        lastTick = now;
        return true;
    }
}
