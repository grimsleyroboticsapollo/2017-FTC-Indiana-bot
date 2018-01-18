/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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

        String helloWorld = "Autonomous Blue Alliance. Go Blue!";
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

        // Step 3:  TODO (figure it out - MotorHelper( .... what? ... ), claw servos maybe?

        // TODO #JK do it!

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
