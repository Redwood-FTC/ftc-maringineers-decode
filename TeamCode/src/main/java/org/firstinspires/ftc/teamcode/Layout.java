package org.firstinspires.ftc.teamcode.layout;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Layout {
    private OpMode opMode;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

    public Layout(OpMode opMode) {
        this.opMode = opMode;
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
    }

    // TODO: turn this, so it feels better
    public double driveForwardAmount() {
        return -Math.pow(gamepad1.left_stick_y, 3);
    }

    public double driveStrafeAmount() {
        return -Math.pow(gamepad1.left_stick_x, 3);
    }

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
}
