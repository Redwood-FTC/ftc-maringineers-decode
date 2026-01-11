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
    private Limelight limelight;

    // private Follower follower;

    private int swapFront = -1;
    private double drivePower = 1;

    /**
     * Declares OpMode, hardware, and layout, and sets the starting pose, updates it, and starts
     * TeleOp.
     *
     * @param opMode   the OpMode object
     * @param hardware the hardware object
     * @param layout   the controller layout object
     */
    public Drive(OpMode opMode, Hardware hardware, Layout layout, Limelight limelight) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.layout = layout;
        this.limelight = limelight;

        // follower = Constants.createFollower(opMode.hardwareMap);
        // // TODO: set it with limelight if possible, else default)
        // // just loop in int, trying to find it, and if not found,
        // // set it to new here?
        // // or have them set the starting position manually if limelight can't
        // // find it
        // // (and also let them disable limit switches, etc.)
        // // TODO: prev.
        // follower.setStartingPose(new Pose());
        // follower.update();
        // follower.startTeleopDrive();
    }

    /**
     * Updates the follower
     */
    public void update() {
        // follower.update();
    }

    /**
     * Stops the robot
     */
    public void stopRobot() {
        setPower(0, 0, 0, 0);
        // follower.setTeleOpDrive(0, 0, 0, true);
    }

    // public void setDrive(double x, double y, double yaw) {
    //     // follower.setTeleOpDrive(x, y, yaw, true);
    // }

    private boolean aiming = false;

    public boolean aimTarget() {
        // TODO: store angle, and calculate power to go to that angle
        aiming = true;
        if (limelight.resultValid()) {
            // if tx > 0, turn left, if < 0, turn right
            // tx / 30
            // anything < 30 will be a corresponding fraction, > 30 will just be full power
            moveRobot(0, 0, limelight.result().getTx() / 30);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets TeleOP drive
     */
    public void gamepadDrive() {
        if (layout.frontFront()) {
            swapFront = 1;
        } else if (layout.frontBack()) {
            swapFront = -1;
        }

        // TODO: move drivepower and swapfront to moveRobot

        // TODO: test robot vs field centric
        // follower.setTeleOpDrive(layout.driveForwardAmount() * swapFront * drivePower,
        //   layout.driveStrafeAmount() * swapFront * drivePower, layout.driveYawAmount() * drivePower, true);
        moveRobot(layout.driveForwardAmount(),
                layout.driveStrafeAmount(),
                -layout.driveYawAmount());
    }

    /**
     * DEPRECATED -
     * Moves the robot based on controller input.
     *
     * @param x   x-value, positive is forward
     * @param y   y-value, positive is strafe left
     * @param yaw yaw, positive is clockwise
     */
    private void moveRobot(double x, double y, double yaw) {
        // add reverse parameter? and encapsulate that here?
        // or add another method, moveRobotDirectional, which also
        // takes a reverse parameter, and calls this

        if (aiming) {
            if (Math.abs(x + y + yaw) > .3) {
                opMode.telemetry.addLine("not aiming");
                aiming = false;
            } else {
                opMode.telemetry.addLine("aiming");
                return;
            }
        }

        x *= swapFront * drivePower;
        y *= swapFront * drivePower;
        yaw *= drivePower;

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

    /**
     * DEPRECATED -
     * Controls the power each wheel has.
     *
     * @param leftFront  the left front wheel DCMotor object
     * @param leftRear   the left rear wheel DCMotor object
     * @param rightFront the right front wheel DCMotor object
     * @param rightRear  the right rear wheel DCMotor object
     */
    private void setPower(double leftFront, double leftRear, double rightFront, double rightRear) {
        hardware.leftFrontDriveMotor.setPower(leftFront);
        hardware.leftRearDriveMotor.setPower(leftRear);
        hardware.rightFrontDriveMotor.setPower(rightFront);
        hardware.rightRearDriveMotor.setPower(rightRear);
    }
}
