package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareIndianaBot {

    /* Public OpMode members. */

    public DcMotor leftFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor rightBackDrive = null;
    public DcMotor clawMotor = null;
    public DcMotor motor5 = null;
    public DcMotor motor6 = null;
    //public DcMotor motor7 = null;
    public Servo clawServoLeft1 = null;
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

        clawMotor = hwMap.get(DcMotor.class, "CLAW");

        motor5 = hwMap.get(DcMotor.class, "5");
        motor6 = hwMap.get(DcMotor.class, "6");
        //motor7 = hwMap.get(DcMotor.class, "7");

        // TODO #GAMEDAY Tweak motor direction until robot drives the way you want.
        // TODO #GAMEDAY -----
        // TODO #GAMEDAY ----- IMPORTANT: ONLY MAKE CHANGES HERE FOR DRIVING!!!
        // TODO #GAMEDAY ----- Changes here will then work everywhere (teleop and autonomous).
        // TODO #GAMEDAY -----
        // TODO #GAMEDAY Left joystick: Drive in robot orientation.
        // TODO #GAMEDAY Right joystick: left/right turns robot on the spot.
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);
        clawMotor.setPower(0);
        motor5.setPower(0);
        motor6.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        clawServoLeft1 = hwMap.get(Servo.class, "CLAWL1");
        //clawServoLeft1.setPosition(MID_SERVO);

        clawServoRight1 = hwMap.get(Servo.class, "CLAWR1");
        //clawServoRight1.setPosition(MID_SERVO);

        clawServoLeft2 = hwMap.get(Servo.class, "CLAWL2");
        //clawServoLeft2.setPosition(MID_SERVO);

        clawServoRight2 = hwMap.get(Servo.class, "CLAWR2");
        //clawServoRight2.setPosition(MID_SERVO);
    }
}
