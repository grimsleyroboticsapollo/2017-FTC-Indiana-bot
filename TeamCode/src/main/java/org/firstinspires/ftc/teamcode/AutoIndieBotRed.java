package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Autonomous when on the RED alliance
 * <p>
 * TODO implement
 */
@Autonomous(name = "AUTO_MOTORBOT_RED", group = "Pushbot")
//@Disabled
public class AutoIndieBotRed extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareIndianaBot robot = new HardwareIndianaBot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();
    ColorSensor sensorRGB;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        sensorRGB = hardwareMap.colorSensor.get("sensor_color");

        String helloWorld = "Autonomous Red Alliance. Go Red!";
        telemetry.addData("Say", helloWorld);    //
        TextReader.speak(hardwareMap.appContext, helloWorld);

        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1: Drive forward for one second

        /*
        Don't do this (drive individual motors:

        robot.leftFrontDrive.setPower(1);
        robot.leftBackDrive.setPower(-1);
        robot.rightBackDrive.setPower(-1);
        robot.rightFrontDrive.setPower(1);

        Do the following instead (use our MotorHelper)
         */

        // Move forward (angle=0) at half speed (driveSpeed=0.5) without spinning (turnOnTheSpotSpeed=0):
        MotorHelper.drive(0, 0.5, 0, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 2:  detect color

        // TODO: Apply color sensor code to autonomous code.

        /*
        Color.RGBToHSV((sensorRGB.red() * 255) / 800, (sensorRGB.green() * 255) / 800, (sensorRGB.blue() * 255) / 800, hsvValues);

        telemetry.addData("Red  ", sensorRGB.red());
        telemetry.addData("Green", sensorRGB.green());
        telemetry.addData("Blue ", sensorRGB.blue());
        telemetry.addData("Hue", hsvValues[0]);
        telemetry.addData("Current_time", System.currentTimeMillis());
        */

        // Step 3:  TODO (figure it out - MotorHelper( .... what? ... ), claw servos maybe?

        // TODO #JK do it!

        // stop
        MotorHelper.drive(0, 0, 0, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
