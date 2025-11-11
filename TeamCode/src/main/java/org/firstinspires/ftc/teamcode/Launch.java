package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.layout.Layout;

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
        if (layout.launchHigh()) {
            spinFast();
        } else if (layout.launchLow()) {
            spinSlow();
        } else if (layout.launchReverse()) {
            spinReverse();
        } else {
            stop();
            // spin(layout.launchPower());
        }
    }

    // TODO: warm_up mechanism

    /**
     * Spins the launch servos.
     */
    public void spinFast() {
        hardware.leftLaunchMotor.setPower(FAST_SPEED);
        hardware.rightLaunchMotor.setPower(FAST_SPEED);
    }

    public void spinSlow() {
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

    public void spinReverse() {
        hardware.leftLaunchMotor.setPower(-1.0);
        hardware.rightLaunchMotor.setPower(-1.0);
    }
}

