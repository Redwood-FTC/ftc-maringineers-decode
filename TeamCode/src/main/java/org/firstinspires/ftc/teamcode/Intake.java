package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.layout.Layout;

import java.util.Arrays;

/**
 * Controls the intake mechanism.
 */
public class Intake {
    private Hardware hardware;
    private OpMode opMode;
    private Layout layout;

    private double INTAKE_SPEED = 1.0;

    /**
     * Initialises the OpMode, Hardware, and Layout.
     *
     * @param opMode   the OpMode object
     * @param hardware the Hardware object
     * @param layout   the Layout object
     */
    public Intake(OpMode opMode, Hardware hardware, Layout layout) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.layout = layout;
    }

    /**
     * Updates the intake mechanism.
     */
    public void update() {
        if (layout.run_intake()) {
            spin();
        } else {
            stop();
        }
    }

    // TODO: warm_up mechanism

    /**
     * Makes the intake servo spin.
     */
    public void spin() {
      hardware.intakeServo.setPosition(0.0);
    }

    /**
     * Stops the intake servo.
     */
    public void stop() {
      hardware.intakeServo.setPosition(0.5);
    }
}

