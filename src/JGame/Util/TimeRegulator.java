package JGame.Util;

import java.util.concurrent.TimeUnit;

public class TimeRegulator{

    private final long periodMilliseconds;
    private long lastTick = 0L;
    private long startTime = 0L;

    public TimeRegulator(long periodMilliseconds){
        this.startTime = System.nanoTime();
        this.periodMilliseconds = periodMilliseconds;
    }

    public long timePassed(){
        long now = System.nanoTime();
        long diffNanos = now - startTime;
        return TimeUnit.NANOSECONDS.toMillis(diffNanos);
    }
    
    public void reset(){
        this.lastTick = 0L;
        this.startTime = System.nanoTime();
    }

    public float secondsPassed(){
        long now = System.nanoTime();
        long diffNanos = now - startTime;
        return (float)TimeUnit.NANOSECONDS.toMillis(diffNanos) / 1000;
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
