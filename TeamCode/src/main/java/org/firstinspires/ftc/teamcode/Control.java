package org.firstinspires.ftc.teamcode;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.layout.Layout;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

// class to provide overall controll of the robot, during teleop
// specifically wrt the intake and launch modes, and directing *how*
// the robot should be controlled --- though other classes primarily handle the
// specifics of that, with methods we will probably use in here
// as much as possible, delegate specific stuff to other classes, and try
// to keep this class pretty encapsulated, lots of functions as much as possible
public class Control {
    private Layout layout;
    private Hardware hardware;
    private Drive drive;
    private Tel tel;
    private LinearOpMode opMode;

    private Follower follower;
    private TelemetryManager telemetryM;

    // TODO: probably have something like last year, with a Pickup mode,
    // for intaking, and a Launch mode, for which the front of the robot
    // is reversed

    public Control(LinearOpMode opMode) {
        this.opMode = opMode;
        layout = new Layout(opMode);
        hardware = new Hardware(opMode);
        drive = new Drive(hardware);
        tel = new Tel(opMode, hardware);

        tel.update();

        follower = Constants.createFollower(opMode.hardwareMap);
        // TODO: set it with limelight if possible, else default)
        // just loop in int, trying to find it, and if not found,
        // set it to new here?
        // or have them set the startingposition manually if limelight can't
        // find it
        // (and also let them disable limit switches, etc.)
        // TODO: prev.
        follower.setStartingPose(new Pose());
        follower.update();
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
    }

    public void stop() {
        drive.stopRobot();
    }

    // TODO: WHAT'S THE DISTINCTION BETWEEN TELEOP AND AUTO FOR THIS CLASS
    // TODO: use opmode instead of linearopmode
    public void initTeleOp() {
        follower.startTeleopDrive();
    }

    public void update() {
        follower.update();
        telemetryM.update();

        // TODO: test robot vs field centric
        follower.setTeleOpDrive(layout.driveForwardAmount(), layout.driveStrafeAmount(), layout.driveYawAmount(), true);
        // follower.setTeleOpDrive(-gamepad1.left_stick_y,-gamepad1.left_stick_x,-gamepad1.right_stick_x,true);
        // drive.moveRobot(layout.driveForwardAmount(), layout.driveStrafeAmount(), layout.driveYawAmount());

        telemetryM.debug("position", follower.getPose());
        telemetryM.debug("velocity", follower.getVelocity());

        telemetryM.debug("right stick x amount", opMode.gamepad1.right_stick_x);
        telemetryM.debug("right stick y amount", opMode.gamepad1.right_stick_y);

        telemetryM.debug("driveForwardAmount", layout.driveForwardAmount());
        telemetryM.debug("driveStrafeAmount", layout.driveStrafeAmount());
        telemetryM.debug("driveYawAmount", layout.driveYawAmount());
    }
}
