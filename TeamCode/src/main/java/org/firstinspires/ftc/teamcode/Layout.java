package org.firstinspires.ftc.teamcode.layout;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Manages the controller layout.
 */
public class Layout {
    private OpMode opMode;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

    /**
     * Initialises the OpMode and gamepad objects.
     *
     * @param opMode the OpMode object
     */
    public Layout(OpMode opMode) {
        this.opMode = opMode;
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
    }

    /**
     * Controlling the launch mechanism.
     *
     * @return whether or not a is pressed
     */
    public boolean ShortSpinBelt() {
        return gamepad1.dpad_right;
    }

    public boolean launchLow() {
        return gamepad1.dpad_left;
    }

    public boolean aim() {
        return gamepad1.left_bumper;
    }

    public boolean fire() {
        return gamepad1.right_bumper;
    }

    public boolean launchReverse() {
        return gamepad1.b;
    }

    public double launchPower() {
        return -gamepad1.right_stick_x;
    }

    public boolean frontFront() {
        return gamepad1.x;
    }

    public boolean frontBack() {
        return gamepad1.y;
    }

    /**
     * Controlling the intake mechanism.
     *
     * @return whether or not b is pressed
     */
    public boolean intakeForward() {
        return gamepad1.dpad_up;
    }

    public boolean intakeBackward() {
        return gamepad1.dpad_down;
    }

    public boolean intakeStop() {
        return gamepad1.a;
    }

    public double beltPower() {
        return -gamepad1.right_stick_y;
    }

    public boolean menuUp() {
        return gamepad1.dpad_up;
    }

    public boolean menuDown() {
        return gamepad1.dpad_down;
    }

    public boolean menuSelect() {
        return gamepad1.a;
    }

    // TODO: tune this, so it feels better

    /**
     * Controls driving the robot forward/back.
     *
     * @return how far forward/back
     */
    public double driveForwardAmount() {
        return -Math.pow(gamepad1.left_stick_y, 3);
    }

    /**
     * Controls strafing the robot.
     *
     * @return how far lef/right
     */
    public double driveStrafeAmount() {
        return -Math.pow(gamepad1.left_stick_x, 3);
    }

    /**
     * Controls turning the robot. Use triggers for rotation, so right stick can be used for
     * scoring/hanging, and so we don't have the awkwardness of the controls for two different
     * things on one joystick.
     */
    public double driveYawAmount() {
        // not a huge difference, but this version feels nice
        return gamepad1.left_trigger - gamepad1.right_trigger;
        // return -Math.pow(gamepad1.right_stick_x, 3);

        // double leftTrigger = gamepad1.left_trigger;
        // double rightTrigger = gamepad1.right_trigger;
        // // manual abs cause rightTrigger has to be negative
        // return leftTrigger > rightTrigger ? leftTrigger : -rightTrigger;
    }
}
