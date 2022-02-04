package others.threads;

import algorithm.Matrix;
import algorithm.memetico.Memetic;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

import static others.threads.ThreadComponent.pool;

public class ThreadComponentTests {

    /**
     *
     */
    @Before
    public void before() {
        Matrix.getInstance().generateMatrix(25);
    }

    /**
     *
     */
    @Test
    public void run() {
        pool(1).
                execute(
                        (Consumer<?>) o -> System.out.println("TERMINOU"),
                        () -> new Memetic().execute("1 ="),
                        () -> new Memetic().execute("2 ="),
                        () -> new Memetic().execute("3 ="),
                        () -> new Memetic().execute("4 ="),
                        () -> new Memetic().execute("5 =")
                );
    }
}
