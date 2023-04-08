package traffic;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static traffic.Main.SCANNER;

public class Util {
    private static final String INPUT_ERROR = "Error! Incorrect Input. Try again!";

    private Util() {
    }

    public static AtomicInteger getPositiveInt(int min, String message) {
        System.out.print(message);
        try {
            int positiveInt = Integer.parseInt(SCANNER.nextLine());
            if (positiveInt < min) {
                return getPositiveInt(min, INPUT_ERROR);
            }
            return new AtomicInteger(positiveInt);
        } catch (Exception e) {
            return getPositiveInt(min, INPUT_ERROR);
        }
    }

    public static void clearConsoleOutput() {
        try {
            System.in.read();
        } catch (IOException ignored) {
        }
    }
}
