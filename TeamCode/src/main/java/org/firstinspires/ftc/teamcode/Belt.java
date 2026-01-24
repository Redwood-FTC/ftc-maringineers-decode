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
    private final double SHORT_SPIN_DURATION = 0.25;

    private double beltEndTime = 0.0;
    private boolean wasShortSpinPressed = false;

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
    // public void runGamepad() {
    //   // reuse this switch here, since for our purpose right now we want
    //   // them to run at the same time
    //   // TODO: once we're doing proper pedropathing launching, this'll change
    //     if (layout.run_intake()) {
    //         spin();
    //     } else {
    //         stop();
    //     }
    // }

    /**
     * Manually controlling the belt from the gamepad.
     */
    public void runGamepad() {
        // if (Math.abs(layout.beltPower()) > .2) {
        //   launch = false;
        // }

        // if (!launch) {
        hardware.beltMotor.setPower(layout.beltPower());

        double now = opMode.getRuntime();
        boolean shortSpinPressed = layout.shortSpinBelt();

        if (shortSpinPressed && !wasShortSpinPressed) {
            beltEndTime = now + SHORT_SPIN_DURATION;
        }

        wasShortSpinPressed = shortSpinPressed;

        if (now < beltEndTime) {
            hardware.beltMotor.setPower(BELT_SPEED);
        } else {
            hardware.beltMotor.setPower(layout.beltPower());
        }
        // }
    }

    public void run(double speed) {
      hardware.beltMotor.setPower(speed);
    }

    /**
     * Set belt to BELT_SPEED power
     */
    public void runFull() {
        hardware.beltMotor.setPower(BELT_SPEED);
    }
    
    /**
     * Stops the belt motor.
     */
    public void stop() {
        // launch = false;
        hardware.beltMotor.setPower(0.0);
    }
}
