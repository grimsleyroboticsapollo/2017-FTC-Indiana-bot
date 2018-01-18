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

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 * <p>
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 * <p>
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 * <p>
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwareIndianaBot {

    /* Public OpMode members. */
    public DcMotor leftFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor rightBackDrive = null;
    public DcMotor clawMotor = null; // TODO #JK this variable is never mapped to a motor (big problem)
    public DcMotor motor5 = null; // TODO #JK I see the mapping to nonsense names, but no usage; give meaningful variable name
    public DcMotor motor6 = null; // TODO #JK same here -"-
    public DcMotor motor7 = null; // TODO #JK same here -"-
    public Servo clawServoLeft1 = null; // TODO #JK please label the servos (or document somewhere) which is 1 or 2
    public Servo clawServoLeft2 = null;
    public Servo clawServoRight1 = null;
    public Servo clawServoRight2 = null;

    public static final double MID_SERVO = 0.5;
    public static double OPEN_SERVO = 00.00;
    public static double CLOSE_SERVO = 0.;

    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor */
    public HardwareIndianaBot() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFrontDrive = hwMap.get(DcMotor.class, "LFD");
        rightFrontDrive = hwMap.get(DcMotor.class, "RFD");
        leftBackDrive = hwMap.get(DcMotor.class, "LBD");
        rightBackDrive = hwMap.get(DcMotor.class, "RBD");
        // TODO #JK I would expect a mapping for clawMotor here
        motor5 = hwMap.get(DcMotor.class, "TRASH"); // TODO #JK what is this?
        motor6 = hwMap.get(DcMotor.class, "GARBAGE"); // TODO #JK ?
        motor7 = hwMap.get(DcMotor.class, "AIDS"); // TODO #JK ???

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        clawServoLeft1 = hwMap.get(Servo.class, "CLAW"); // TODO #JK this is not descriptive as to which servo it is
        clawServoLeft1.setPosition(MID_SERVO);

        clawServoRight1 = hwMap.get(Servo.class, "CLAU"); // TODO #JK what does "U" stand for?
        clawServoRight1.setPosition(MID_SERVO);

        clawServoLeft2 = hwMap.get(Servo.class, "CLAOU"); // TODO #JK don't know
        clawServoLeft2.setPosition(MID_SERVO);

        clawServoRight2 = hwMap.get(Servo.class, "CLAOFKDFVNTFDS"); // TODO #JK ??
        clawServoRight2.setPosition(MID_SERVO);
    }
}

