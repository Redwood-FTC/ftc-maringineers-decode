package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import java.util.Arrays;

// TODO: do we want to have the methods to specifically control the physical
// hardware in here, or in the more specific class?
// determine that once we start doing some of that --- if it's pretty
// painless, leave it in other classes, if it requires lots of code
// just for specifically controlling the hardware, move it here
// also, code reuse, if necessary

/**
 * Initiates the hardware, holds the references to it, and has constants for various positions to
 * move to.
 */
public class Hardware {
    private OpMode opMode;
    public DcMotorEx leftFrontDriveMotor;
    public DcMotorEx leftRearDriveMotor;
    public DcMotorEx rightFrontDriveMotor;
    public DcMotorEx rightRearDriveMotor;

    public DcMotorEx leftLaunchMotor;
    public DcMotorEx rightLaunchMotor;

    public DcMotorEx beltMotor;

    public Servo intakeServo;

    public Limelight3A limelight;

    /**
     * Initialise the motors, encoders, servos, and limelight.
     *
     * @param opMode the OpMode object
     */
    public Hardware(OpMode opMode) {
        this.opMode = opMode;

        // MAKE MORE FUNCTIONS AS NEEDED, DO NOT PUT SPECIFIC INITIALIZATION IN
        // THE CONSTRUCTOR ITSELF, OR IN ANY OTHER CLASS
        initServos();
        initEncoders();
        initMotors();
        initLimelight();
    }

    /**
     * Initialises the limelight.
     */
    private void initLimelight() {
        limelight = opMode.hardwareMap.get(Limelight3A.class, "limelight");
    }


    /**
     * Initialises the servos.
     */
    private void initServos() {
        intakeServo = opMode.hardwareMap.get(Servo.class, "intakeServo");

        for (Servo servo : new Servo[]{}) {
            servo.setDirection(Servo.Direction.REVERSE);
        }
    }

    /**
     * Initialises the encoders.
     */
    private void initEncoders() {
    }

    /**
     * Initialises the motors.
     */
    private void initMotors() {
        leftFrontDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "leftFrontDriveMotor");
        leftRearDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "leftRearDriveMotor");
        rightFrontDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "rightFrontDriveMotor");
        rightRearDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "rightRearDriveMotor");

        leftLaunchMotor = opMode.hardwareMap.get(DcMotorEx.class, "leftLaunchMotor");
        rightLaunchMotor = opMode.hardwareMap.get(DcMotorEx.class, "rightLaunchMotor");

        beltMotor = opMode.hardwareMap.get(DcMotorEx.class, "beltMotor");

        // each side is flipped in comparison to the other, so set the right side to reverse
        // TODO: determine what we want for this
        for (DcMotorEx motor : new DcMotorEx[]{rightRearDriveMotor, leftRearDriveMotor, leftLaunchMotor}) {
            motor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        for (DcMotorEx motor : new DcMotorEx[]{rightLaunchMotor, rightFrontDriveMotor, beltMotor, leftFrontDriveMotor}) {
            motor.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        for (DcMotorEx motor : new DcMotorEx[]{leftFrontDriveMotor, leftRearDriveMotor, rightFrontDriveMotor,
                rightRearDriveMotor, beltMotor}) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        for (DcMotorEx motor : new DcMotorEx[]{leftLaunchMotor, rightLaunchMotor}) {
            // temporary, for debugging position
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
        // for (DcMotorEx motor : new DcMotorEx[]{
        //     // leftFrontDriveMotor, leftRearDriveMotor,
        //     //     rightFrontDriveMotor, rightRearDriveMotor
        //     }) {
        //     motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // }
        for (DcMotorEx motor : new DcMotorEx[]{leftFrontDriveMotor, leftRearDriveMotor,
                rightFrontDriveMotor, rightRearDriveMotor, leftLaunchMotor, rightLaunchMotor,
            beltMotor}) {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        for (DcMotorEx motor : new DcMotorEx[]{}) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        for (DcMotorEx motor : new DcMotorEx[]{}) {
            motor.setTargetPosition(0);
        }
        for (DcMotorEx motor : new DcMotorEx[]{}) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // temp, for debugging position
            // motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        for (DcMotorEx motor : new DcMotorEx[]{}) {
            // motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // temp, for debugging position
            // motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
