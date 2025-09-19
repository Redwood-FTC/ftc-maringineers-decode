package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

// adapted from arts Config class, in ../common

// TODO: fix the inner classes thing
// not doing it at this instant because I don't want to get hung up on it right now, and
// I really like how stuff is accessed currently, and I'm not sure if there's a good way to do that
// without having a bunch of different files, or doing what I'm doing here; not getting hung up on
// it right now
//
// why I like the way this class is accessed:
// it provides syntactic meaning to the names, so I can just have 'leftFront' instead of 'leftFrontMotor',
// and it's still obvious what it is, because it's accessed as motor.leftFront
//
public class HardwareToPosition extends Hardware {
    // if I do threads, make it possible for one thread to lock access to certain variables?

    // fields to hold inner classes after instantiation
    public LinearOpMode opMode;
    public Motors motors;
    public Servos servos;

    public TouchSensor scoreArmLimitSensor;
    public TouchSensor pickupArmLimitSensor;
    public TouchSensor leftHangArmLimitSensor;
    public TouchSensor rightHangArmLimitSensor;

    public HardwareToPosition(LinearOpMode opMode) {
        super(opMode);
        this.opMode = opMode;

        motors = new Motors(opMode);
        servos = new Servos(opMode);

        scoreArmLimitSensor = opMode.hardwareMap.get(TouchSensor.class, "scoreArmLimitSensor");
        pickupArmLimitSensor = opMode.hardwareMap.get(TouchSensor.class, "pickupArmLimitSensor");
        leftHangArmLimitSensor = opMode.hardwareMap.get(TouchSensor.class, "leftHangArmLimitSensor");
        rightHangArmLimitSensor = opMode.hardwareMap.get(TouchSensor.class, "rightHangArmLimitSensor"); // pickup right left
    }

    public static class Servos {
        // 0.5 seems ok for within limits
        public static final double HANG_SERVO_STARTING_POSITION = 0.5;
        public static final double HANG_SERVO_IDLE_POSITION = 0.5;
        public static final double HANG_SERVO_MAX_POSITION = 0.57;
        public static final double HANG_SERVO_MIN_POSITION = 0.455;
        // could be positive or negative, need to calibrate
        // they look equal??? I have no idea
        public static final double LEFT_HANG_SERVO_OFFSET = 0;
        public static final double RIGHT_HANG_SERVO_OFFSET = 0;

        public Servo leftHangServo;
        public Servo rightHangServo;
        public Servo bucketServo;
        public Servo pickupBaseServo;
        public Servo pickupShoulderServo; // TODO: configure this
        public CRServo intakeServo;

        private Servos(LinearOpMode opMode) {
            leftHangServo = opMode.hardwareMap.get(Servo.class, "leftHangServo");
            rightHangServo = opMode.hardwareMap.get(Servo.class, "rightHangServo");
            bucketServo = opMode.hardwareMap.get(Servo.class, "bucketServo");
            pickupBaseServo = opMode.hardwareMap.get(Servo.class, "pickupBaseServo");
            pickupShoulderServo = opMode.hardwareMap.get(Servo.class, "pickupShoulderServo");
            intakeServo = opMode.hardwareMap.get(CRServo.class, "intakeServo");
//          pickupBaseServo = opMode.hardwareMap.get(Servo.class, "pickupIntakeServo");

            // even though some of these only have one element *now*, the point is that we'll likely
            // have more eventually, and thus changing the number of servos being configured in some
            // way is as easy to change as possible, while also being more consistent
            for (Servo servo : new Servo[]{leftHangServo, bucketServo}) {
                servo.setDirection(Servo.Direction.FORWARD);
            }

            for (Servo servo : new Servo[]{rightHangServo}) {
                servo.setDirection(Servo.Direction.REVERSE);
            }

            // find max and min values first
//            leftHangServo.scaleRange(LEFT_HANG_SERVO_MIN_POSITION, LEFT_HANG_SERVO_MAX_POSITION);
//            rightHangServo.scaleRange(RIGHT_HANG_SERVO_MIN_POSITION, RIGHT_HANG_SERVO_MAX_POSITION);
        }

        private void setStartingPositions() {
            // TODO
            // one of these functions for the motors as well, once the limit switches are in
        }
    }

    // any motors
    public static class Motors {
        public static double SCORE_MOTOR_POWER = 1;
        public static double HANG_MOTOR_POWER = 1;
        public static int HANG_MOTOR_STARTING_POSITION = 0;
        public static int HANG_MOTOR_MAX_POSITION = 3100;
        public static int HANG_MOTOR_MIN_POSITION = 0;
        public static int HANG_MOTOR_IDLE_POSITION = 0;
        public static int SCORE_MOTOR_CLAW_PICKUP_POSITION = 0;
        public static int PICKUP_MOTOR_MAX_POSITION = 1500; // read as 1586

        public DcMotorEx leftFrontDriveMotor;
        public DcMotorEx leftRearDriveMotor;
        public DcMotorEx rightFrontDriveMotor;
        public DcMotorEx rightRearDriveMotor;



        public DcMotorEx scoreMotor;
        public DcMotorEx pickupMotor;


        private Motors(LinearOpMode opMode) {
            leftFrontDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "leftFrontDriveMotor");
            leftRearDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "leftRearDriveMotor");
            rightFrontDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "rightFrontDriveMotor");
            rightRearDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "rightRearDriveMotor");
            scoreMotor = opMode.hardwareMap.get(DcMotorEx.class, "scoreMotor");
            pickupMotor = opMode.hardwareMap.get(DcMotorEx.class, "pickupMotor");

            // each side is flipped in comparison to the other, so set the right side to reverse;
            // so that forward on each side is the same
            for (DcMotorEx motor : new DcMotorEx[]{leftFrontDriveMotor, leftRearDriveMotor, }) {
                motor.setDirection(DcMotorSimple.Direction.FORWARD);
                // determine if to swap hanging
            }
            for (DcMotorEx motor : new DcMotorEx[]{rightFrontDriveMotor, rightRearDriveMotor, scoreMotor, pickupMotor}) {
                motor.setDirection(DcMotorSimple.Direction.REVERSE);
            }

            for (DcMotorEx motor : new DcMotorEx[]{leftFrontDriveMotor, leftRearDriveMotor, rightFrontDriveMotor,
                    rightRearDriveMotor, scoreMotor, pickupMotor}) {
                motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
            for (DcMotorEx motor : new DcMotorEx[]{scoreMotor}) {
                // temporary, for debugging position
                motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }
            resetDriveMotorEncoders();
            for (DcMotorEx motor : new DcMotorEx[]{scoreMotor, pickupMotor}) {
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            for (DcMotorEx motor : new DcMotorEx[]{scoreMotor, pickupMotor}) {
                motor.setTargetPosition(0);
            }
            for (DcMotorEx motor : new DcMotorEx[]{scoreMotor, pickupMotor}) {
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // temp, for debugging position
//                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            for (DcMotorEx motor : new DcMotorEx[]{}) {
//                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // temp, for debugging position
//                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }

        public void resetDriveMotorEncoders() {
            for (DcMotorEx motor : new DcMotorEx[]{leftFrontDriveMotor, leftRearDriveMotor, rightFrontDriveMotor, rightRearDriveMotor}) {
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motor.setTargetPosition(0);
                motor.setPower(.5);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
        }
        public boolean isBusy(){
            return leftFrontDriveMotor.isBusy() || leftRearDriveMotor.isBusy() || rightFrontDriveMotor.isBusy() || rightRearDriveMotor.isBusy();
        }
    }

    public void resetDriveMotorEncoders() {
        motors.resetDriveMotorEncoders();
    }

    public static double ticksPerInch = 384.5 / 4.094 * 3.1415/4.8;
    public static double rotConversionFactor = 3.1415 * 22*.70 / 360;

    public void moveMotors(double x, double y, double rot, double pow) {

        motors.resetDriveMotorEncoders();
        motors.leftFrontDriveMotor.setPower(pow);
        motors.leftRearDriveMotor.setPower(pow);
        motors.rightFrontDriveMotor.setPower(pow);
        motors.rightRearDriveMotor.setPower(pow);
        int lf = (int) ((x - y + rot * rotConversionFactor) * ticksPerInch);
        int lr = (int) ((x + y + rot * rotConversionFactor) * ticksPerInch);
        int rf = (int) ((x + y - rot * rotConversionFactor) * ticksPerInch);
        int rr = (int) ((x - y - rot * rotConversionFactor) * ticksPerInch);
        motors.leftFrontDriveMotor.setTargetPosition(lf);
        motors.leftRearDriveMotor.setTargetPosition(lr);
        motors.rightFrontDriveMotor.setTargetPosition(rf);
        motors.rightRearDriveMotor.setTargetPosition(rr);
    }


}

// robot class
// init constructor, builder constructor

// not inner classes
// keep it flexible