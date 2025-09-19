package org.firstinspires.ftc.teamcode.layout;

// de facto control scheme, more can be added as need for them arises

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Pickup;
import org.firstinspires.ftc.teamcode.Score;

public class Default implements Layout {
    private LinearOpMode opMode;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

    public Default(LinearOpMode opMode) {
        this.opMode = opMode;
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
    }

    @Override
    public double driveForwardAmount() {
        return Math.pow(gamepad1.left_stick_y, 3);
    }

    @Override
    public double driveStrafeAmount() {
        return Math.pow(gamepad1.left_stick_x, 3);
    }

    // use triggers for rotation, so right stick can be used for scoring/hanging;
    // and we don't have the awkwardness of the controls for two different things on one stick
    @Override
    public double driveYawAmount() {
        // not a huge difference, but this version feels nice
//        return gamepad1.left_trigger - gamepad1.right_trigger;
        return -Math.pow(gamepad1.right_stick_x, 3);

//        double leftTrigger = gamepad1.left_trigger;
//        double rightTrigger = gamepad1.right_trigger;
//        // manual abs cause rightTrigger has to be negative
//        return leftTrigger > rightTrigger ? leftTrigger : -rightTrigger;
    }


    // disregard these 3
    @Override
    public boolean intakeIn() {
        return gamepad1.x;
    }

    @Override
    public boolean intakeOut() {
        return gamepad1.y;
    }

    @Override
    public boolean pickupArmExtendToggle() {
        return gamepad1.b;
    }

    @Override
    public boolean HangTurnToggle() {
        return gamepad1.b;
    }

    @Override
    public double hangAngleAmount() {
        return gamepad1.right_stick_x;
    }

    @Override
    public double hangExtendAmount() {
        return -gamepad1.right_stick_y;
    }

    @Override
    public boolean scoreArmClaw() {
        return gamepad1.a;
    }

    @Override
    public Pickup.Command pickupArm() {
        return Pickup.Command.Pickup;
    }
}
