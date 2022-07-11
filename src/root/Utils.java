package root;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;

public class Utils {
    public static int randInRange(int s, int e){
        int diff = e - s + 1;
        return (int)(s + (Math.random() * diff));
    }

    public static void closeAll(Closeable ... closeables) {
        Arrays.stream(closeables).forEach(closeable -> {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
