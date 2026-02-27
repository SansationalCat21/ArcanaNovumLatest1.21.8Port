package net.borisshoes.borislib.timers;

/**
 * Shim for net.borisshoes.borislib.timers.RepeatTimer.
 * Runs a Runnable every intervalTicks for a total of totalTicks, then stops.
 * Usage: new RepeatTimer(intervalTicks, totalTicks, runnable, null)
 */
public class RepeatTimer extends TickTimerCallback {
    private final int intervalTicks;
    private final int totalTicks;
    private final Runnable callback;
    private int elapsed;

    public RepeatTimer(int intervalTicks, int totalTicks, Runnable callback, Object ignored) {
        super(intervalTicks, null, null);
        this.intervalTicks = intervalTicks;
        this.totalTicks = totalTicks;
        this.callback = callback;
        this.elapsed = 0;
    }

    @Override
    public void onTimer() {
        elapsed += intervalTicks;
        if (callback != null) {
            callback.run();
        }
        // Re-schedule if there's still time remaining
        if (elapsed < totalTicks) {
            net.borisshoes.borislib.BorisLib.addTickTimerCallback(new RepeatTimer(intervalTicks, totalTicks - elapsed, callback, null));
        }
    }
}
