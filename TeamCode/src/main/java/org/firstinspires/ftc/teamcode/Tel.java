package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// control the printing of telemetry in a centralized class
// instead of having the caller decide which to be printed.....
// just edit the variables here
// motivation:
// it'll always require a recompile anyways, and this way is not
// only less boilerplate, but also, it's being controlled from the same place
// as where it's defined

public class Tel {
    private Hardware hardware;
    // private Hang hang;
    private LinearOpMode opMode;
    private Telemetry telemetry;

    private boolean printServos = false;
    private boolean printGamepad = false;
    private boolean printMotors = true;
    private boolean printLimitSwitches = false;
    private boolean printLimelight = false;

    // we can't call it telemetry, so tel's an ok alternative
    public Tel(LinearOpMode opMode, Hardware hardware) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.telemetry = opMode.telemetry;
    }

    // for when it's necessary to print stuff that depends on control flow
    // etc. (which would ideally be temporary during troubleshooting)
    // ALTERNATIVELY: expose a function for the caller to add a line, that
    // we then actually pass to Telemetry --- though, them calling it
    // is probably fine, as long as this.update() is always called
    // after everything else in the main loop
    public Telemetry telemetry() {
        return telemetry;
    }

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
        if (printLimelight) {
            telemetry.addLine("\nLIMELIGHT:");
        }

        telemetry.update();
    }
}
