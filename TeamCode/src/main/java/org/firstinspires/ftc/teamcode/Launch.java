package org.firstinspires.ftc.teamcode;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.layout.Layout;

/**
 * Controls the launching mechanism.
 */
public class Launch {
    private Hardware hardware;
    private OpMode opMode;
    private Layout layout;
    private Drive drive;
    private Belt belt;
    private TelemetryManager telemetryM;

    private double FAST_SPEED = 0.52;
    private double SLOW_SPEED = 0.45;

    private int queue = 0;
    private double queueTime = 0;

    /**
     * Initialises the OpMode, Hardware, and Layout objects.
     *
     * @param opMode   the OpMode object
     * @param hardware the Hardware object
     * @param layout   the Layout object
     */
    public Launch(OpMode opMode, Hardware hardware, Layout layout, Drive drive, Belt belt, TelemetryManager telemetryM) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.layout = layout;
        this.drive = drive;
        this.belt = belt;
        this.telemetryM = telemetryM;
    }

    /**
     * Updates the launch mechanism.
     */
    private boolean firePressed = false;

    public void runGamepad() {
        // opMode.telemetry.addData("left vel: ", hardware.leftLaunchMotor.getVelocity());
        // opMode.telemetry.addData("right vel: ", hardware.rightLaunchMotor.getVelocity());
        opMode.telemetry.addData("queue: ", queue);
        opMode.telemetry.addData("queueTime: ", queueTime);

        // opMode.telemetry.addData("left speed: ", hardware.leftLaunchMotor.getPower());
        // opMode.telemetry.addData("right speed: ", hardware.rightLaunchMotor.getPower());
        telemetryM.debug("left vel: ", hardware.leftLaunchMotor.getVelocity());
        telemetryM.debug("right vel: ", hardware.rightLaunchMotor.getVelocity());

        if (layout.launchReverse()) {
            spinReverse();
            return;
        }

        if (layout.aim()) {
            spinSlow();
        } else if (layout.fire()) {
            spinFast();
        } else {
            stop();
        }

        if (!firePressed) {
            return;
        }
        if (firePressed) {
            return;
        }

        // if (layout.aim()) {
        //     drive.aimTarget();
        // }

        // if (layout.fire() && !firePressed) {
        //     firePressed = true;

        //     if (queue == 0) {
        //         queueTime = opMode.time;
        //     }

        //     queue += 1;
        //     if (queue > 3) {
        //         queue = 3;
        //     }
        //     // spinFast(); // TODO: proper speed control, and launch queueing
        // } else if (!layout.fire()) {
        //     firePressed = false;
        // }

        // if (queue == 0) {
        //     return;
        // }

        // if (opMode.time - queueTime < 1) {
        //     if (launchHigh) {
        //         spinFast();
        //     } else {
        //         spinSlow();
        //     }
        // } else if (opMode.time - queueTime <1 + 1) {
        //     if (launchHigh) {
        //         spinFast();
        //     } else {
        //         spinSlow();
        //     }
        //     intake.in();
        //     belt.launch();
        // } else {
        //     queue -= 1;
        //     queueTime = opMode.time;
        //     // queueTime = 0;
        //     // queue = 0;
        //     // stop();
        //     // belt.stop();
        // }

        // if (queue == 0) {
        //     queueTime = 0;
        //     stop();
        //     belt.stop();
        // }

        // // else if (layout.launchReverse()) {
        // // } else {
        // //     stop();
        // //     // spin(layout.launchPower());
        // // }
    }

    // public void run() {
    //     if (layout.aim()) {
    //         drive.aimTarget();
    //     }

    //     if (layout.fire()) {
    //         spinFast(); // TODO: proper speed control, and launch queueing
    //     }

    //     // if aim() do the drive aim thing
    //     // if fire, todo --- for now as a temp just spinfast
    // }

    // TODO: method to see if it's at the correct speed

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
