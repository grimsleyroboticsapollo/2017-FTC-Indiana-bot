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
        boolean clawOpen;
        boolean badClawOpen;
        boolean clawClose;
        boolean BCClose;
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
        // TODO #GAMEDAY if not using gamepad2 then replace this next line ...
        //speedMult2 = speedMult1;
        // TODO #GAMEDAY ... with:
        // speedMult2 = speedMult1;

        MotorHelper.drive(joystickAngle, joySpeed * speedMult1, rightX, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);

        if (noTurnLeft) {
            // TODO #JK I don't quite understand what you want to do. If you just want the robot to drive to the left
            // TODO #JK without turning, then you could just use the left joystick button and move it to the left. If
            // TODO #JK that makes the robot turn then there's a problem elsewhere (either in the motors or in the programming).
            // TODO #JK But if you just want a shortcut for driving to the left, then use the MotorHelper.drive function
            // TODO #JK as such:
            // MotorHelper.drive(90., speedMult1, 0, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);
            robot.leftFrontDrive.setPower(-1);
            robot.leftBackDrive.setPower(1);
            robot.rightFrontDrive.setPower(1);
            robot.rightBackDrive.setPower(-1);
        } else if (noTurnRight) {
            // TODO #JK similar to the above, don't power motors directly but use the MotorHelper:
            // MotorHelper.drive(270., speedMult1, 0, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);
            robot.leftFrontDrive.setPower(1);
            robot.leftBackDrive.setPower(-1);
            robot.rightFrontDrive.setPower(-1);
            robot.rightBackDrive.setPower(1);
        } else {

        }

        if (clawOpen) {
            robot.clawServoLeft1.setPosition(-1);
            robot.clawServoRight1.setPosition(1);

            telemetry.addData("CLAW", "open button has been pressed");
        } else if (clawClose) {
            robot.clawServoLeft1.setPosition(1);
            robot.clawServoRight1.setPosition(-1);
        } else {

        }

        if (badClawOpen) {
            robot.clawServoLeft2.setPosition(-180);
            robot.clawServoRight2.setPosition(180);

            telemetry.addData("CLAW", "open button has been pressed");
        } else if (BCClose) {
            robot.clawServoLeft2.setPosition(180);
            robot.clawServoRight2.setPosition(-180);
        } else {

        }

        if (badClawUp) {
            MotorHelper.claw_Hand(robot.clawMotor, .05);
            MotorHelper.claw_Hand(robot.clawMotor, .5);
            telemetry.addData("CLAW", "up button has been pressed");
        } else if (badClawDown) {
            MotorHelper.claw_Hand(robot.clawMotor, -.3);
            telemetry.addData("CLAW", "down button has been pressed");
        } else {
            MotorHelper.claw_Hand(robot.clawMotor, 0);
        }

        if (stringUp) {
            MotorHelper.claw_Hand(robot.motor6, 2);
            telemetry.addData("CLAW", "up button has been pressed");
        } else if (stringDown) {
            MotorHelper.claw_Hand(robot.motor6, -1);
            telemetry.addData("CLAW", "down button has been pressed");
        } else {
            MotorHelper.claw_Hand(robot.motor5, 0);
        }

        if (clawUp) {
            MotorHelper.claw_Hand(robot.motor5, 2);
            telemetry.addData("CLAW", "up button has been pressed");
        } else if (clawDown) {
            MotorHelper.claw_Hand(robot.motor5, -1);
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
