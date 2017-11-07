package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class MotorHelper {

    private final static double RAD_TO_DEGREES = 180. / Math.PI;

    /**
     * Given an angle, drive speed, and turn-on-the-spot speed, drives the four motors
     * of an omniwheel drive. Orientation is robot orientation, with angle 0 facing forward.
     *
     * @param angle From 0 to 360. degrees, with 0 degree being "forward" and 90 degrees "left"
     * @param driveSpeed Speed with which to go into the direction of angle [ -1.0, 1.0 ]
     * @param turnOnTheSpotSpeed Speed with which to turn on the spot [ -1.0, 1.0 ]
     * @param leftFrontDrive DcMotor object from the hardware map. Positive speed drives forward.
     * @param rightFrontDrive DcMotor object from the hardware map. Positive speed drives forward.
     * @param leftBackDrive DcMotor object from the hardware map. Positive speed drives forward.
     * @param rightBackDrive DcMotor object from the hardware map. Positive speed drives forward.
     *
     * Usage example:
     *
     * MotorHelper.drive(driveAngle, speed, turnSpeed, robot.leftFrontDrive, ...)
     */
    public static void drive(double angle,
                             double driveSpeed,
                             double turnOnTheSpotSpeed,
                             DcMotor leftFrontDrive,
                             DcMotor rightFrontDrive,
                             DcMotor leftBackDrive,
                             DcMotor rightBackDrive
                             ) {

        if (driveSpeed < -1.) {
            driveSpeed = -1.;
        } else if (driveSpeed > 1.) {
            driveSpeed = 1.;
        }

        // calculate motor speed for driving
        double leftFrontSpeed = driveSpeed * Math.cos((angle + 45.) / RAD_TO_DEGREES);
        double rightFrontSpeed = driveSpeed * Math.cos((angle - 45.) / RAD_TO_DEGREES);
        double leftBackSpeed = driveSpeed * Math.cos((angle - 45.) / RAD_TO_DEGREES);
        double rightBackSpeed = driveSpeed * Math.cos((angle + 45.) / RAD_TO_DEGREES);

        // turn on the spot
        leftFrontSpeed += turnOnTheSpotSpeed;
        leftBackSpeed += turnOnTheSpotSpeed;
        rightFrontSpeed -= turnOnTheSpotSpeed;
        rightBackSpeed -= turnOnTheSpotSpeed;

        // make sure it's between 1 & -1

        leftFrontSpeed = Range.clip(leftFrontSpeed, -1, 1);
        leftBackSpeed = Range.clip(leftBackSpeed, -1, 1);
        rightFrontSpeed = Range.clip(rightFrontSpeed, -1, 1);
        rightBackSpeed = Range.clip(rightBackSpeed, -1, 1);

        // TODO power the motors

    }

}
