import java.awt.*;
import java.awt.event.KeyEvent;

public class Driver {

    private static Robot robot;
    private static long startTime;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static void keepAwake() {
        while (true) {
            if (elapsedTime() > 1000) {
                startTime = System.currentTimeMillis();
                robot.keyPress(KeyEvent.VK_F15);
            }
        }
    }

    private static long elapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        keepAwake();
    }

}
