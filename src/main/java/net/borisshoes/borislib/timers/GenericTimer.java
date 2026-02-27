package net.borisshoes.borislib.timers;

/**
 * Shim for net.borisshoes.borislib.timers.GenericTimer.
 * The class was present in the compile-time borislib jar but missing from the runtime jar.
 * This shim recreates the original behavior: run a Runnable after a given number of ticks.
 */
public class GenericTimer extends TickTimerCallback {
    private final Runnable callback;

    public GenericTimer(int ticks, Runnable callback) {
        super(ticks, null, null);
        this.callback = callback;
    }

    @Override
    public void onTimer() {
        if (callback != null) {
            callback.run();
        }
    }
}
