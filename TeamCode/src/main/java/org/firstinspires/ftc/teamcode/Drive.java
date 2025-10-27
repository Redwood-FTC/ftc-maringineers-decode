package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.layout.Layout;

import java.util.Arrays;

// file containing public interface for driving, primarily controls pedropathing
// etc.
public class Drive {
    private Hardware hardware;
    private OpMode opMode;
    private Layout layout;

    private Follower follower;

    public Drive(OpMode opMode, Hardware hardware, Layout layout) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.layout = layout;

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
        follower.startTeleopDrive();
    }

    public void update() {
        follower.update();
    }

    public void stopRobot() {
        // setPower(0, 0, 0, 0);
        follower.setTeleOpDrive(0, 0, 0, true);
    }

    public void gamepadDrive() {
        // TODO: test robot vs field centric
        follower.setTeleOpDrive(layout.driveForwardAmount(), layout.driveStrafeAmount(), layout.driveYawAmount(), true);
    }

    // x: positive is forward
    // y: positive is strafe left
    // yaw: positive is clockwise
    // DEPRECATED
    // keeping around so that if necessary, can easily use this method
    private void moveRobot(double x, double y, double yaw) {
        // add reverse parameter? and encapsulate that here?
        // or add another method, moveRobotDirectional, which also
        // takes a reverse parameter, and calls this

        double leftFrontPower = x - y + yaw;
        double leftRearPower = x + y + yaw;
        double rightFrontPower = x + y - yaw;
        double rightRearPower = x - y - yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Arrays.stream(
                        new double[]{leftFrontPower, leftRearPower, rightFrontPower, rightRearPower})
                .map(Math::abs)
                .max()
                .getAsDouble();

        if (max > 1.0) {
            Arrays.stream(new Double[]{leftFrontPower, leftRearPower, rightFrontPower, rightRearPower}).map(n -> n /= max);
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftRearPower /= max;
            rightRearPower /= max;
        }

        setPower(leftFrontPower, leftRearPower, rightFrontPower, rightRearPower);
    }

    // DEPRECATED
    private void setPower(double leftFront, double leftRear, double rightFront, double rightRear) {
        hardware.leftFrontDriveMotor.setPower(leftFront);
        hardware.leftRearDriveMotor.setPower(leftRear);
        hardware.rightFrontDriveMotor.setPower(rightFront);
        hardware.rightRearDriveMotor.setPower(rightRear);
    }
}
