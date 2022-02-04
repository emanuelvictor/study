package others.threads;

import algorithm.Matrix;
import algorithm.memetico.Memetic;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicBoolean;

import static others.threads.ThreadComponent.pool;

@FixMethodOrder(MethodSorters.JVM)
public class ThreadComponentTests {

    private static final int POOL = 4;

    private static final int CITIES = 20;

    private static final int SLEEP = 5000;

    /**
     *
     */
    @Before
    public void before() {
        Matrix.getInstance().generateMatrix(CITIES);
    }

    /**
     *
     */
    @After
    public void after() {
        sleep(SLEEP);
    }

    /**
     *
     */
    @Test
    public void executeAndThen() {

        final AtomicBoolean done = new AtomicBoolean(false);

        pool(POOL).
                execute(
                        () -> new Memetic().execute("1 ="),
                        () -> new Memetic().execute("2 ="),
                        () -> new Memetic().execute("3 ="),
                        () -> new Memetic().execute("4 ="),
                        () -> new Memetic().execute("5 =")
                )
                .then(o -> done.set(true));

        System.out.println("IT MUST SHOWING BEFORE OTHER OBJECTS");

        Assert.isTrue(!done.get(), "The threads is blocking the principal thread");

    }

    /**
     *
     */
    @Test
    public void executeAndThenBlockingPrincipalThread() {

        final AtomicBoolean done = new AtomicBoolean(false);

        pool(POOL).
                execute(
                        () -> new Memetic().execute("1 ="),
                        () -> new Memetic().execute("2 ="),
                        () -> new Memetic().execute("3 ="),
                        () -> new Memetic().execute("4 ="),
                        () -> new Memetic().execute("5 =")
                )
                .block()
                .then(o -> done.set(true));

        System.out.println("IT MUST SHOWING AFTER OTHER OBJECTS");

        Assert.isTrue(done.get(), "The threads is not blocking the principal thread");

    }

    private static void sleep(final int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
