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
     * Spin the belt for a quarter second.
     *
     * @return whether or not dpad right is pressed
     */
    public boolean shortSpinBelt() {
        return gamepad1.dpad_right;
    }

    /**
     * Launch the ball low
     *
     * @return whether or not dpad left is pressed
     */
    public boolean launchLow() {
        return gamepad1.dpad_left;
    }

    /**
     * Drive to launch position
     *
     * @return Whether or not left bumper is pressed
     */
    public boolean aim() {
        return gamepad1.left_bumper;
    }

    /**
     * Launch the ball
     *
     * @return whether or not right bumper is pressed
     */
    public boolean fire() {
        return gamepad1.right_bumper;
    }

    /**
     * Intake the ball
     *
     * @return whether or not b is pressed
     */
    public boolean launchReverse() {
        return gamepad1.b;
    }

    /**
     *
     * @return
     */
    public double launchPower() {
        return -gamepad1.right_stick_x;
    }

    /**
     * Change the front to the front of the robot
     *
     * @return whether or not x is pressed
     */
    public boolean frontFront() {
        return gamepad1.x;
    }

    /**
     * Change the front to the back of the robot
     *
     * @return whether or not y is pressed
     */
    public boolean frontBack() {
        return gamepad1.y;
    }

    /**
     * Move the intake forward.
     *
     * @return whether or not dpad up is pressed
     */
    public boolean intakeForward() {
        return gamepad1.dpad_up;
    }

    /**
     * Move the intake backward
     *
     * @return whether or not dpad down is pressed
     */
    public boolean intakeBackward() {
        return gamepad1.dpad_down;
    }

    /**
     * Stop the intake
     *
     * @return whether or not a is pressed
     */
    public boolean intakeStop() {
        return gamepad1.a;
    }

    /**
     * Set the belt power
     *
     * @return the reversed y value of the right joystick
     */
    public double beltPower() {
        return -gamepad1.right_stick_y;
    }

    /**
     * Increase the menu by one
     *
     * @return whether or not dpad up is pressed
     */
    public boolean menuUp() {
        return gamepad1.dpad_up;
    }

    /**
     * Decrease by menu by one
     *
     * @return whether or not dpad down is pressed
     */
    public boolean menuDown() {
        return gamepad1.dpad_down;
    }

    /**
     * Not sure what this does
     *
     * @return whether or not a is pressed
     */
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
