package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.layout.Layout;

import java.util.Arrays;

/**
 * Controls the launching mechanism.
 */
public class Launch {
    private Hardware hardware;
    private OpMode opMode;
    private Layout layout;

    private double LAUNCH_SPEED = 0.85;

    /**
     * Initialises the OpMode, Hardware, and Layout objects.
     *
     * @param opMode   the OpMode object
     * @param hardware the Hardware object
     * @param layout   the Layout object
     */
    public Launch(OpMode opMode, Hardware hardware, Layout layout) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.layout = layout;
    }

    /**
     * Updates the launch mechanism.
     */
    public void update() {
        if (layout.run_launch()) {
            spin();
        } else {
            stop();
        }
    }

    // TODO: warm_up mechanism

    /**
     * Spins the launch servos.
     */
    public void spin() {
        hardware.leftLaunchMotor.setPower(LAUNCH_SPEED);
        hardware.rightLaunchMotor.setPower(LAUNCH_SPEED);
    }

    /**
     * Stops the launch servos.
     */
    public void stop() {
        hardware.leftLaunchMotor.setPower(0.0);
        hardware.rightLaunchMotor.setPower(0.0);
    }
}

