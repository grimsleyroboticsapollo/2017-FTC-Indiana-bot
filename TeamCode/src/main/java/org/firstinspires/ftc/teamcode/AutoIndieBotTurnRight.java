package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Autonomous for driving forward and turning right
 * <p>
 * TODO #GAMEDAY tweak path / speed / turning radius based on tests
 */
@Autonomous(name = "AUTO turn right", group = "autonomous")
//@Disabled
public class AutoIndieBotTurnRight extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareIndianaBot robot = new HardwareIndianaBot();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        String helloWorld = "Time to start the autonomous! Let's turn right!!!";
        telemetry.addData("Say", helloWorld);
        TextReader.speak(hardwareMap.appContext, helloWorld);

        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Move forward (angle=0),
        // at half speed (driveSpeed=0.5),
        // turning right (turnOnTheSpotSpeed=0.2):
        // TODO #GAMEDAY tweak
        MotorHelper.drive(0, 0.5, 0.2, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);

        runtime.reset();

        // do this for four seconds (runtime seconds < 4.0):
        // TODO #GAMEDAY tweak
        while (opModeIsActive() && (runtime.seconds() < 4.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // TODO #GAMEDAY tweak path as needed

        // stop
        MotorHelper.drive(0, 0, 0, robot.leftFrontDrive, robot.rightFrontDrive, robot.leftBackDrive, robot.rightBackDrive);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
