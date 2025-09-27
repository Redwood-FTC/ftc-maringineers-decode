package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.Arrays;

// class for initiating hadrware, and holding the references to it;
// as well as holding constants describing positions, etc.

// TODO: do we want to have the methods to specifically control the physical
// hardware in here, or in the more specific class?
// determine that once we start doing some of that --- if it's pretty
// painless, leave it in other classes, if it requires lots of code
// just for specifically controlling the hardware, move it here
// also, code reuse, if necessary

public class Hardware {
    LinearOpMode opMode;

        public DcMotorEx leftFrontDriveMotor;
        public DcMotorEx leftRearDriveMotor;
        public DcMotorEx rightFrontDriveMotor;
        public DcMotorEx rightRearDriveMotor;

    public Hardware(LinearOpMode opMode) {
        this.opMode = opMode;
        initMotors(opMode);
    }

    private void initMotors(LinearOpMode opMode) {
            leftFrontDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "leftFrontDriveMotor");
            leftRearDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "leftRearDriveMotor");
            rightFrontDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "rightFrontDriveMotor");
            rightRearDriveMotor = opMode.hardwareMap.get(DcMotorEx.class, "rightRearDriveMotor");

        
            // each side is flipped in comparison to the other, so set the right side to reverse;
            // so that forward on each side is the same
            // TODO: determine what we want for this
            for (DcMotorEx motor : new DcMotorEx[] {leftFrontDriveMotor, leftRearDriveMotor}) {
                motor.setDirection(DcMotorSimple.Direction.FORWARD);
            }
            for (DcMotorEx motor : new DcMotorEx[] {rightFrontDriveMotor, rightRearDriveMotor}) {
                motor.setDirection(DcMotorSimple.Direction.REVERSE);
            }

            for (DcMotorEx motor : new DcMotorEx[] {leftFrontDriveMotor, leftRearDriveMotor, rightFrontDriveMotor,
                    rightRearDriveMotor}) {
                motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
            for (DcMotorEx motor : new DcMotorEx[] {}) {
                // temporary, for debugging position
                motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }

            for (DcMotorEx motor : new DcMotorEx[] {leftFrontDriveMotor, leftRearDriveMotor,
                rightFrontDriveMotor, rightRearDriveMotor}) {
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            for (DcMotorEx motor : new DcMotorEx[] {}) {
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            for (DcMotorEx motor : new DcMotorEx[] {}) {
                motor.setTargetPosition(0);
            }
            for (DcMotorEx motor : new DcMotorEx[] {}) {
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // temp, for debugging position
                // motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            for (DcMotorEx motor : new DcMotorEx[] {}) {
                // motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // temp, for debugging position
                // motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
    }

}
