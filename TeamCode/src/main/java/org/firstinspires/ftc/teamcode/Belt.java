package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.layout.Layout;

import java.util.Arrays;

/**
 * Controls the belt mechanism.
 */
public class Belt {
    private Hardware hardware;
    private OpMode opMode;
    private Layout layout;

    private double BELT_SPEED = 1.0;

    /**
     * Initialises the OpMode, Hardware, and Layout.
     *
     * @param opMode   the OpMode object
     * @param hardware the Hardware object
     * @param layout   the Layout object
     */
    public Belt(OpMode opMode, Hardware hardware, Layout layout) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.layout = layout;
    }

    /**
     * Updates the belt mechanism.
     */
    public void update() {
        spin();
    }

    /**
     * Makes the belt motor spin.
     */
    public void spin() {
        hardware.beltMotor.setPower(BELT_SPEED);
    }

    /**
     * Stops the belt motor.
     */
    public void stop() {
        hardware.beltMotor.setPower(0.0);
    }
}
