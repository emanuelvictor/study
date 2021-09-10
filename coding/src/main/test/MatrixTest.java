import algoritmo.Matrix;
import org.junit.Test;
import org.springframework.util.Assert;

public class MatrixTest {

    @Test
    public void testGenerateRandomRouteMustPass() {
        final int[] array = Matrix.getInstance().generateRandomRoute(100);
        Assert.isTrue(!isOrdering(array), "Is not random");
        Assert.isTrue(isRandom(array), "Is not random");
    }

    @Test
    public void testGenerateOrderingRouteMustPass() {
        final int[] array = Matrix.getInstance().generateOrderingRoute(100);
        Assert.isTrue(isOrdering(array), "Is random");
        Assert.isTrue(!isRandom(array), "Is random");
    }

    private boolean isRandom(final int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if ((array[i] + 1) != array[i + 1])
                return true;
        }
        return false;
    }

    private boolean isOrdering(final int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if ((array[i] + 1) != array[i + 1])
                return false;
        }
        return true;
    }
}
