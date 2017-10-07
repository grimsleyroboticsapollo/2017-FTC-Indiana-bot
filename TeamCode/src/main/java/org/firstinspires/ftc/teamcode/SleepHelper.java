package org.firstinspires.ftc.teamcode;

/**
 * Created by aidan on 10/7/2017.
 */

public class SleepHelper {
    public static void waitMilliseconds (long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            //do nothing
        }
    }
}
