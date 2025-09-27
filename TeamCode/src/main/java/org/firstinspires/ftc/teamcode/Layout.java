package org.firstinspires.ftc.teamcode.layout;

import org.firstinspires.ftc.teamcode.Pickup;

public class Layout {
    private LinearOpMode opMode;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

    // TODO: turn this, so it feels better
    public double driveForwardAmount() {
        return Math.pow(gamepad1.left_stick_y, 3);
    }

    public double driveStrafeAmount() {
        return Math.pow(gamepad1.left_stick_x, 3);
    }
    double driveYawAmount();

    // use triggers for rotation, so right stick can be used for scoring/hanging;
    // and we don't have the awkwardness of the controls for two different things on one stick
    public double driveYawAmount() {
        // not a huge difference, but this version feels nice
       return gamepad1.left_trigger - gamepad1.right_trigger;
        // return -Math.pow(gamepad1.right_stick_x, 3);

       // double leftTrigger = gamepad1.left_trigger;
       // double rightTrigger = gamepad1.right_trigger;
       // // manual abs cause rightTrigger has to be negative
       // return leftTrigger > rightTrigger ? leftTrigger : -rightTrigger;
    }

    double hangAngleAmount();
    double hangExtendAmount();

    boolean scoreArmClaw();

    boolean intakeIn();
    boolean intakeOut();
    boolean pickupArmExtendToggle();
    boolean HangTurnToggle();

    Pickup.Command pickupArm();
}
