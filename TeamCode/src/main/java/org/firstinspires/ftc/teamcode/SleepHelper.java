package org.firstinspires.ftc.teamcode;

public class SleepHelper {
    public static void waitMilliseconds (long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            //do nothing
        }
    }
}
