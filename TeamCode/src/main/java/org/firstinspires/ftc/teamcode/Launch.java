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

    private double FAST_SPEED = 0.70;
    private double SLOW_SPEED = 0.5;

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
    public void runGamepad() {
        if (layout.launch_high()) {
            spin_fast();
        } else if (layout.launch_low()) {
            spin_slow();
        } else if (layout.launch_reverse()) {
          spin_reverse();
        } else {
          stop();
          // spin(layout.launch_power());
        }
    }

    // TODO: warm_up mechanism

    /**
     * Spins the launch servos.
     */
    public void spin_fast() {
        hardware.leftLaunchMotor.setPower(FAST_SPEED);
        hardware.rightLaunchMotor.setPower(FAST_SPEED);
    }

    public void spin_slow() {
        hardware.leftLaunchMotor.setPower(SLOW_SPEED);
        hardware.rightLaunchMotor.setPower(SLOW_SPEED);
    }

    public void spin(double speed) {
        hardware.leftLaunchMotor.setPower(speed);
        hardware.rightLaunchMotor.setPower(speed);
    }

    /**
     * Stops the launch servos.
     */
    public void stop() {
        hardware.leftLaunchMotor.setPower(0.0);
        hardware.rightLaunchMotor.setPower(0.0);
    }

    public void spin_reverse() {
        hardware.leftLaunchMotor.setPower(-1.0);
        hardware.rightLaunchMotor.setPower(-1.0);
    }
}

