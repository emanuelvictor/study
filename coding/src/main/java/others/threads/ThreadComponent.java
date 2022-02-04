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

    public void execute(final Consumer<?> then, final Runnable... runnables) {

        final Collection<Future<?>> futures = new ArrayList<>();
        for (final Runnable runnable : runnables) {
            futures.add(executorService.submit(runnable));
        }

        final Consumer<Boolean> done = isDone -> {
            if (isDone)
                then.accept(null);
        };

        while (!futures.stream().allMatch(Future::isDone)) {
            done.accept(false);
        }

        done.accept(true);

    }

}
