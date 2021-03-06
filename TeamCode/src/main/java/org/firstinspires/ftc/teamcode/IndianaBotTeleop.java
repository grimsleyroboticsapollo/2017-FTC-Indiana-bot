package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Team 3391 Neptune - primary TELEOP
 */
@TeleOp(name = "MOTORBOT_TELEOP", group = "teleop")
//@Disabled
public class IndianaBotTeleop extends OpMode {

    //int. debug mode
    boolean IN_DEBUG_MODE = false;

    /* Declare OpMode members. */
    HardwareIndianaBot robot = new HardwareIndianaBot(); // use the class created to define a Pushbot's hardware

    /*
    Let's not worry about the gyro until we have an otherwise working robot.

    // Gyro sensor
    // The IMU sensor object
    BNO055IMU imu;

    // State used for updating telemetry
    Orientation angles;
    Acceleration gravity;

     */

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        String helloWorld = "YO! IT'S TYME TO DO THE ROBIT! PRESS B TO STERT!1!";
        telemetry.addData("Say", helloWorld);    //
        TextReader.speak(hardwareMap.appContext, helloWorld);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        // TODO #JK if we're using the gyro, then here would be where we would determine
        // TODO #JK its initial position through a moving exponential average.
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        double leftX;
        double rightX;
        double leftY;
        boolean noTurnLeft = false; //this is a 'blueprint' for linear movement. Will remove once finished
        boolean noTurnRight = false; // same here
        boolean clawOpen; // TODO right now coded to open the artifact claw
        boolean badClawOpen; // TODO right now coded to close the glyph claw
        boolean clawClose; // TODO right now coded to close the artifact claw
        boolean BCClose; // TODO right now coded to open the glyph claw
        boolean stringUp;
        boolean stringDown;
        boolean clawUp;
        boolean clawDown;
        boolean badClawUp;
        boolean badClawDown;
        boolean lockClaw;
        double speedMult1;

        leftX = gamepad1.left_stick_x;
        leftY = gamepad1.left_stick_y;
        rightX = gamepad1.right_stick_x;
        noTurnLeft = gamepad1.dpad_left;
        noTurnRight = gamepad1.dpad_right;
        clawOpen = gamepad1.a;
        badClawOpen = gamepad1.b;
        clawClose = gamepad1.x;
        BCClose = gamepad1.y;
        clawUp = gamepad2.a;
        clawDown = gamepad2.b;
        stringUp = gamepad2.x;
        stringDown = gamepad2.y;
        badClawUp = gamepad2.left_bumper;
        badClawDown = gamepad2.right_bumper;
        lockClaw = gamepad1.left_bumper;
        double joystickAngle = JoystickHelper.getAngle(leftX, leftY);
        double joySpeed = Math.sqrt(leftX * leftX + leftY * leftY);
        double leftTrigger1 = gamepad1.left_trigger;
        double rightTrigger1 = gamepad1.right_trigger;

        speedMult1 = 1. + rightTrigger1 - leftTrigger1 / 2.;

        MotorHelper.drive(joystickAngle, joySpeed * speedMult1, rightX, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);

        if (noTurnLeft) {
            MotorHelper.drive(90., speedMult1, 0, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);
        } else if (noTurnRight) {
            MotorHelper.drive(270., speedMult1, 0, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);
        }

        if (clawOpen) { // TODO opens artifact claw
            robot.clawServoLeft1.setPosition(0.7);
            robot.clawServoRight1.setPosition(0.3);

            telemetry.addData("CLAW", "open button has been pressed");
        } else if (clawClose) { // TODO closes artifact claw
            robot.clawServoLeft1.setPosition(1);
            robot.clawServoRight1.setPosition(0);
        }

        if (badClawOpen) { // TODO closes (yes, CLOSES) the glyph claw
            robot.clawServoLeft2.setPosition(0.25); // servo #3
            robot.clawServoRight2.setPosition(0.75); // servo #4

            telemetry.addData("CLAW", "open button has been pressed");
        } else if (BCClose) { // TODO opens (yes, OPENS) the glyph claw
            robot.clawServoLeft2.setPosition(0.7); // servo #3
            robot.clawServoRight2.setPosition(0.3); // servo #4
        }

        if (badClawUp) {
            MotorHelper.claw_Hand(robot.clawMotor, .8);
            telemetry.addData("CLAW", "up button has been pressed");
        } else if (badClawDown) {
            MotorHelper.claw_Hand(robot.clawMotor, -.3);
            telemetry.addData("CLAW", "down button has been pressed");
        } else {
            MotorHelper.claw_Hand(robot.clawMotor, 0);
        }

        if (stringUp) {
            MotorHelper.claw_Hand(robot.motor6, 1.);
            telemetry.addData("CLAW", "up button has been pressed");
        } else if (stringDown) {
            MotorHelper.claw_Hand(robot.motor6, -1.);
            telemetry.addData("CLAW", "down button has been pressed");
        } else {
            MotorHelper.claw_Hand(robot.motor5, 0);
        }

        if (clawUp) {
            MotorHelper.claw_Hand(robot.motor5, 0.5);
            telemetry.addData("CLAW", "up button has been pressed");
        } else if (clawDown) {
            MotorHelper.claw_Hand(robot.motor5, -0.5);
            telemetry.addData("CLAW", "down button has been pressed");
        } else {
            MotorHelper.claw_Hand(robot.motor5, 0);
        }

        if (lockClaw) {
            MotorHelper.claw_Hand(robot.motor5, 0.03);
        } else {

        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        // stop the robot
        MotorHelper.drive(0, 0, 0, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);
        MotorHelper.claw_Hand(robot.clawMotor, 0);
    }
}
