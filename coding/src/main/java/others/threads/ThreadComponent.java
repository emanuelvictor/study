package others.threads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class ThreadComponent {

    /**
     *
     */
    private boolean block = false;

    /**
     *
     */
    private final Collection<Future<?>> futures = new ArrayList<>();

    /**
     *
     */
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * @param threadPool int
     */
    private ThreadComponent(final int threadPool) {
        executorService = Executors.newFixedThreadPool(threadPool);
    }

    /**
     * @param threadPool int
     * @return ThreadComponent
     */
    public static ThreadComponent pool(final int threadPool) {
        return new ThreadComponent(threadPool);
    }

    /**
     * @param runnables Runnable...
     * @return ThreadComponent
     */
    public ThreadComponent execute(final Runnable... runnables) {
        for (final Runnable runnable : runnables) {
            futures.add(executorService.submit(runnable));
        }
        return this;
    }

    /**
     * @return ThreadComponent
     */
    public ThreadComponent block() {
        this.block = true;
        return this;
    }

    /**
     * @param then Consumer<?>
     */
    public void then(final Consumer<?> then) {
        if (block)
            accept(then);
        else {
            final Runnable runnable = () -> accept(then);
            final ExecutorService executorService = Executors.newFixedThreadPool(2);
            executorService.execute(runnable);
            executorService.shutdown();
        }
    }

    /**
     * @param then Consumer<?>
     */
    private void accept(final Consumer<?> then) {
        int j = 1;
        for (int i = 0; i < j++; i++) {
            if (futures.stream().allMatch(Future::isDone)) {
                then.accept(null);
                break;
            }
            j++;
        }
    }

}
