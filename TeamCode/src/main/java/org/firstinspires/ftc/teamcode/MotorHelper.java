package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

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

        // TODO make sure driveSpeed is not larger than 1.0 or smaller than -1.0

        // calculate motor speed for driving
        double leftFrontSpeed = driveSpeed * Math.cos((angle + 45.) / RAD_TO_DEGREES);
        double rightFrontSpeed = 0; // TODO implement
        double leftBackSpeed = 0; // TODO implement
        double rightBackSpeed = 0; // TODO implement

        // turn on the spot
        leftFrontSpeed += turnOnTheSpotSpeed;
        // TODO calculate the other three speeds

        // TODO perform limit check to make sure that all four motor speeds are within -1.0 and 1.0

        // TODO power the motors

    }

}
