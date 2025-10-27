package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Manages printing telemetry.
 */
public class Tel {
    private Hardware hardware;
    // private Hang hang;
    private OpMode opMode;
    private Telemetry telemetry;

    private boolean printServos = false;
    private boolean printGamepad = false;
    private boolean printMotors = true;
    private boolean printLimitSwitches = false;
    private boolean printPedroPathing = true;
    private boolean printLimelight = false;

    /**
     * Initialises the OpMode, Hardware, and Telemetry objects.
     *
     * @param opMode   the OpMode object
     * @param hardware the Hardware object
     */
    public Tel(OpMode opMode, Hardware hardware) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.telemetry = opMode.telemetry;
    }

    /**
     * For when it's necessary to print stuff that depends on control flow
     * etc (which would ideally be temporary during troubleshooting).
     * ALTERNATIVELY: expose a function for the caller to add a line, that
     * we then actually pass to Telemetry - though, them calling it
     * is probably fine, as long as this.update() is always called
     * after everything else in the main loop.
     */
    public Telemetry telemetry() {
        return telemetry;
    }

    // TODO: we'll be using panels for this, so just set up this with panels

    /**
     * Updates telemetry.
     */
    public void update() {
        // TODO: order
        if (printServos) {
            telemetry.addLine("SERVOS:");
        }
        if (printMotors) {
            telemetry.addLine("\nMOTORS:");
        }
        if (printLimitSwitches) {
            telemetry.addLine("\nLIMIT SWITCHES:");
        }
        if (printGamepad) {
            telemetry.addLine("\nGAMEPAD:");
            telemetry.addData("right stick x amount: ", opMode.gamepad1.right_stick_x);
            telemetry.addData("right stick y amount: ", opMode.gamepad1.right_stick_y);
        }
        if (printPedroPathing) {
            telemetry.addLine("\nPEDROPATHING:");
        }
        if (printLimelight) {
            telemetry.addLine("\nLIMELIGHT:");
        }

        telemetry.update();
    }
}
