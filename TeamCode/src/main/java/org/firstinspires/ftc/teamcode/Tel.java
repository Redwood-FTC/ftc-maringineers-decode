package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Tel {
    private Hardware hardware;
    // private Hang hang;
    private LinearOpMode opmode;
    private Score score;
    private long counter = 0;
    private Telemetry telemetry;

    private boolean printServos = false;
    private boolean printGamepad = false;
    private boolean printMotors = false;
    private boolean printHang = false;
    private boolean printLimitSwitches = false;
    private boolean printCounter = false;
    private boolean printScore = false;
    private boolean printLimelight = false;

    // we can't call it telemetry, so tel's an ok alternative
    // this is just a pretty simlpe builder-like class
    public Tel(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public void update() {
        // TODO: order
        if (printServos) {
            // TODO: print "servos:" before this, and etc for the rest
            // telemetry.addData("left hang servo: ", hardware.servos.leftHangServo.getPosition());
            // telemetry.addData("right hang servo: ", hardware.servos.rightHangServo.getPosition());
            telemetry.addData("bucket servo position: ", hardware.servos.bucketServo.getPosition());
            telemetry.addData("pickup base servo position: ", hardware.servos.pickupBaseServo.getPosition());
            telemetry.addData("pickup shoulder servo position: ", hardware.servos.pickupShoulderServo.getPosition());
            // telemetry.addData("left hang servo position: ", hardware.servos.leftHangServo.getPosition());
            // telemetry.addData("right hang servo position: ", hardware.servos.rightHangServo.getPosition());
        }
        if (printMotors) {
            telemetry.addData("score arm motor position: ", hardware.motors.scoreMotor.getCurrentPosition());

            telemetry.addData("pickup arm motor position: ", hardware.motors.pickupMotor.getCurrentPosition());

            telemetry.addData("score arm power: ", hardware.motors.scoreMotor.getPower());
            telemetry.addData("score arm motor target position: ", hardware.motors.scoreMotor.getTargetPosition());


            telemetry.addData("pickup motor target position: ", hardware.motors.pickupMotor.getTargetPosition());
        }
        // if (printHang) {
        //     telemetry.addData("angle amount: ", hang.angleAmount());
        //     telemetry.addData("extend amount: ", hang.extendAmount());
        // }
        if (printLimitSwitches) {
            telemetry.addData("score arm limit switch activated: ", hardware.scoreArmLimitSensor.isPressed());
            telemetry.addData("pickup arm limit switch activated: ", hardware.pickupArmLimitSensor.isPressed());
            telemetry.addData("left hang limit switch activated: ", hardware.leftHangArmLimitSensor.isPressed());
            telemetry.addData("right hang arm limit switch activated: ", hardware.rightHangArmLimitSensor.isPressed());
        }
        if (printCounter) {
            telemetry.addData("counter", counter);
        }
        if (printGamepad) {
            telemetry.addData("right stick x amount: ", opmode.gamepad1.right_stick_x);
            telemetry.addData("right stick y amount: ", opmode.gamepad1.right_stick_y);
        }
        if (printScore) {
            telemetry.addData("score position: ", score.position());
            telemetry.addData("scoreClawArmPressed: ", score.scoreClawArmPressed());
            telemetry.addData("deliverHigh: ", score.deliverHigh());
        }
        if (printLimelight) {

        }
        telemetry.update();
    }

    public Tel servos(Hardware hardware) {
        this.hardware = hardware;
        printServos = true;
        return this;
    }

    public Tel motors(Hardware hardware) {
        this.hardware = hardware;
        printMotors = true;
        return this;
    }

    // public Tel hang(Hang hang) {
    //     this.hang = hang;
    //     printHang = true;
    //     return this;
    // }

    public Tel gamepad(LinearOpMode opmode) {
        this.opmode = opmode;
        printGamepad = true;
        return this;
    }

    public Tel limitSwitches(Hardware hardware) {
        this.hardware = hardware;
        printLimitSwitches = true;
        return this;
    }

    public Tel counter() {
        printCounter = true;
        return this;
    }

    public Tel score(Score score) {
        this.score = score;
        printScore = true;
        return this;
    }

    public Tel limelight() {
        printLimelight = true;
        return this;
    }
}
