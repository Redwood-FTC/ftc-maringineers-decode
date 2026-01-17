package org.firstinspires.ftc.teamcode;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.layout.Layout;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

/**
 * Manage overall control of the robot during teleop, specifically wrt the intake and launch modes,
 * and directing *how* the robot should be controlled. It is meant to be highly encapsulated, with
 * as many methods as possible in other classes.
 */
public class Control {
    private Layout layout;
    private Hardware hardware;
    private Drive drive;
    private Tel tel;
    private OpMode opMode;
    private Limelight limelight;
    private Launch launch;
    private Belt belt;

    private Menu menu;

    private TelemetryManager telemetryM;

    private boolean started = false;
    private boolean runMenu = false;
    private boolean runAuto = false;

    // TODO: probably have something like last year, with a Pickup mode,
    // for intaking, and a Launch mode, for which the front of the robot
    // is reversed

    /**
     * Controls the robot's functions
     *
     * @param opMode the OpMode object
     */
    public Control(OpMode opMode) {
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        this.opMode = opMode;
        layout = new Layout(opMode);
        hardware = new Hardware(opMode);
        tel = new Tel(opMode, hardware);
        belt = new Belt(opMode, hardware, layout);
        limelight = new Limelight(opMode, hardware, telemetryM);
        drive = new Drive(opMode, hardware, layout, limelight);
        launch = new Launch(opMode, hardware, layout, drive, belt, telemetryM);


        update();
    }

    /**
     * Starts the robot
     */
    public void start() {
        started = true;
    }

    /**
     * Sets the menu objet to a new menu, sets runMenu to true
     */
    public void runMenu() {
        menu = new Menu(layout, opMode, opMode.telemetry);
        runMenu = true;
    }

    /**
     * Sets runAuto to true and runMenu to false
     */
    public void runAuto() {
        runAuto = true;
        runMenu = false;
    }

    /**
     * Stops the robot
     */
    public void stop() {
        drive.stopRobot();
    }

    /**
     * Temporary dead reckoning algorithm to position and launch three balls
     */
    private void auto() {
        // TEMPORARY
        // dead reckoning algorithm, to just position ourselves correctly and launch three balls
        switch (menu.selected()) {
            case RED_FRONT:
            case BLUE_FRONT:
                // TODO: drive functions in drive, rather than setting powers here
                // for front:move (forward?) for so long, fire
                // if (opMode.time < 1) {
                //     drive.setDrive(.25, 0, 0);
                // } else if (opMode.time < 1.5) {
                //     drive.setDrive(0, 0, 0);
                //     launch.spinSlow();
                // } else {
                //     launch.spinSlow();
                //     belt.runFull();
                // }
                break;
            case RED_REAR:
                // just immediately go?
                // telemetry.addLine(" Selected RED_REAR");
                // break;
            case BLUE_REAR:
                // if (opMode.time < .5) {
                //     drive.setDrive(0, 0, 0);
                //     launch.spinFast();
                // } else {
                //     launch.spinFast();
                //     belt.runFull();
                // }
                break;
        }
    }

    /**
     * If should is true, then moves away from the target. Then aims and shoots.
     *
     * @param should whether it should move towards the target
     */
    public void MovesAwayFromTarget(boolean should) {
        // jlasdf
    }

    /**
     * Updates drive, telemetry, and the limelight.
     */
    public void update() {
        if (runMenu) {
            menu.update();
            return;
        }
        // opMode.telemetry.addData("MENU: ", runMenu);boolean

        if (runAuto) {
            auto();
            return;
        }

        if (started) {
            run();
        }

        opMode.telemetry.addLine("running");

        // TODO: move to tel
        // telemetryM.debug("position", follower.getPose());
        // telemetryM.debug("velocity", follower.getVelocity());

        telemetryM.debug("right stick x amount", opMode.gamepad1.right_stick_x);
        telemetryM.debug("right stick y amount", opMode.gamepad1.right_stick_y);

        telemetryM.debug("driveForwardAmount", layout.driveForwardAmount());
        telemetryM.debug("driveStrafeAmount", layout.driveStrafeAmount());
        telemetryM.debug("driveYawAmount", layout.driveYawAmount());

        // if (limelight.resultValid()) {
        //     telemetryM.debug("pose", limelight.pose().toString());
        //     opMode.telemetry.addData("pose", limelight.pose());
        //     opMode.telemetry.addData("tx", limelight.getTx());
        //     opMode.telemetry.addData("txnc", limelight.getTxNC());
        //     opMode.telemetry.addData("ty", limelight.getTy());
        //     opMode.telemetry.addData("tync", limelight.getTyNC());
        // } else {
        //     opMode.telemetry.addLine("no pose");
        // }

        opMode.telemetry.addData("time: ", opMode.time);

        telemetryM.update();
        tel.update();
    }

    /**
     * Runs the robot functions
     */
    private void run() {
        drive.update();
        limelight.update();

        drive.gamepadDrive();

        launch.runGamepad();
        belt.runGamepad();
    }
}
