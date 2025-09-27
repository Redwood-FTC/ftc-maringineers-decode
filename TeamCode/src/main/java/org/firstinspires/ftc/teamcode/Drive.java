package org.firstinspires.ftc.teamcode;

import java.util.Arrays;

// import common.Logger;

// File containing _public_ interface for driving,
// both teleop and autonomous
// further separate if needed, but should be fine for now
// to contain anything related to causing the robot to move
// will likely need to separate autonomous code somehow at some point,
// so this class doesn't become too cluttered.
public class Drive {
    Hardware hardware;
    public Drive(Hardware hardware) {
        this.hardware = hardware;
    }

    // TODO: helix: way to arrange methods, types, blocks, etc, as units, that condenses everything
    //  else, to make it easy to order stuff

    // TODO: name this method relative, and make absolute method

    // x: positive is forward
    // y: positive is strafe left
    // yaw: positive is clockwise
    public void moveRobot(double x, double y, double yaw) {
        // we seem to be having an issue with drifting, so add power parameter?
        // zero power behaviour is brake, which should help
        // when strafing, the back end seems to like coming out more than the front end
        // could decrease power to rear wheels? could use encoders
        // to decrease power to rear wheels when they come out of alignment

        // when yaw is 0, left and right encoder should be equal
        // when yaw != 0, x, y = 0, then left encoder should equal -right encoder
        // figure out what they should be for any yaw that isn't 0 or 1
        // but correction could also be sufficient for when there's no yaw

        // keep track of encoders to make it so that instead of controls being relative to robot,
        // are absolute
        // use apriltags to find initial position; then use encoders to keep track or rotation
        // https://youtu.be/ewlDPvRK4U4
        // start facing right or left apriltag
        // for testing, just need to face any apriltag
        //

        double leftFrontPower  = x - y + yaw;
        double leftRearPower   = x + y + yaw;
        double rightFrontPower = x + y - yaw;
        double rightRearPower  = x - y - yaw;

        // Normalize wheel powers to be less than 1.0
        // abs and max the previous 4 values
        double max = Arrays.stream(
                new double[]{leftFrontPower, leftRearPower, rightFrontPower, rightRearPower})
                .map(Math::abs)
                .max()
                .getAsDouble();

        if (max > 1.0) {
            Arrays.stream(new Double[]{leftFrontPower, leftRearPower, rightFrontPower, rightRearPower}).map(n -> n /= max);
//            leftFrontPower /= max;
//            rightFrontPower /= max;
//            leftRearPower /= max;
//            rightRearPower /= max;
        }

        setPower(leftFrontPower, leftRearPower, rightFrontPower, rightRearPower);
    }

    public void stopRobot() {
        setPower(0, 0, 0, 0);
    }

    public void setPower(double leftFront, double leftRear, double rightFront, double rightRear) {
        hardware.motors.leftFrontDriveMotor.setPower(leftFront);
        hardware.motors.leftRearDriveMotor.setPower(leftRear);
        hardware.motors.rightFrontDriveMotor.setPower(rightFront);
        hardware.motors.rightRearDriveMotor.setPower(rightRear);
    }
}
