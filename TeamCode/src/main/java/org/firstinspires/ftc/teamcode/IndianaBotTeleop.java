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
        String helloWorld = "YO, it's tyme to do the robet. Prez buton to stert!1!";
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
        boolean clawOpen;
        boolean badClawOpen;
        boolean clawUp;
        boolean clawDown;
        boolean badClawUp;
        boolean badClawDown;
        double speedMult1;
        double speedMult2;

        leftX = gamepad1.left_stick_x;
        leftY = gamepad1.left_stick_y;
        rightX = gamepad1.right_stick_x;
        clawOpen = gamepad1.a;
        badClawOpen = gamepad1.b;
        clawUp = gamepad1.x;
        clawDown = gamepad1.y;
        badClawUp = gamepad1.left_bumper;
        badClawDown = gamepad1.right_bumper;
        double joystickAngle = JoystickHelper.getAngle(leftX, leftY);
        double joySpeed = Math.sqrt(leftX * leftX + leftY * leftY);
        double leftTrigger1 = gamepad1.left_trigger;
        double rightTrigger1 = gamepad1.right_trigger;
        double leftTrigger2 = gamepad2.left_trigger; // TODO #GAMEDAY delete gamepad2 if not needed
        double rightTrigger2 = gamepad2.right_trigger; // TODO #GAMEDAY delete gamepad2 if not needed

        speedMult1 = 1. + rightTrigger1 - leftTrigger1 / 2.;
        // TODO #GAMEDAY if not using gamepad2 then replace this next line ...
        speedMult2 = 1. + rightTrigger2 - leftTrigger2 / 2.;
        // TODO #GAMEDAY ... with:
        // speedMult2 = speedMult1;

        MotorHelper.drive(joystickAngle, joySpeed * speedMult1, rightX, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);

        if (clawOpen) {
            robot.clawServoLeft1.setPosition(.9);
            robot.clawServoRight1.setPosition(-.9);

            telemetry.addData("CLAW", "open button has been pressed");
        } else if (!clawOpen) {
            robot.clawServoLeft1.setPosition(0.);
            robot.clawServoRight1.setPosition(0.);
        }

        if (badClawOpen) {
            robot.clawServoLeft2.setPosition(.9);
            robot.clawServoRight2.setPosition(-.9);

            telemetry.addData("CLAW", "open button has been pressed");
        } else if (!badClawOpen) {
            robot.clawServoLeft2.setPosition(0.);
            robot.clawServoRight2.setPosition(0.);
        }

        if (clawUp) {
            MotorHelper.claw_Hand(robot.clawMotor, speedMult2);
            telemetry.addData("CLAW", "up button has been pressed");
        } else if (clawDown) {
            MotorHelper.claw_Hand(robot.clawMotor, -speedMult2);
            telemetry.addData("CLAW", "down button has been pressed");
        } else {
            MotorHelper.claw_Hand(robot.clawMotor, 0);
        }

        if (badClawUp) {
            MotorHelper.claw_Hand(robot.motor5, speedMult2);
            telemetry.addData("CLAW", "up button has been pressed");
        } else if (badClawDown) {
            MotorHelper.claw_Hand(robot.motor5, -speedMult2);
            telemetry.addData("CLAW", "down button has been pressed");
        } else {
            MotorHelper.claw_Hand(robot.motor5, 0);
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
