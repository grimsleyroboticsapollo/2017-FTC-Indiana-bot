package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by aidan on 10/7/2017.
 */

public class DebugCode {
    public static double speedMult (Gamepad gamepad) {
        double leftTrigger = gamepad.left_trigger;
        double rightTrigger = gamepad.right_trigger;

        return 1. + rightTrigger - leftTrigger/2.;
    }
}
