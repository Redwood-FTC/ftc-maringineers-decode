package org.firstinspires.ftc.teamcode;

import java.util.Arrays;

// import common.Logger;

// file containing public interface for driving wrt teleop

// TODO: what about pedropathing, and all the associated stuff?
// presumably it would be in it's own file
// so what would go here? possibly the code that pedropathing uses to
// physically control the wheel motors?
public class Drive {
    Hardware hardware;
    public Drive(Hardware hardware) {
        this.hardware = hardware;
    }

    // x: positive is forward
    // y: positive is strafe left
    // yaw: positive is clockwise
    public void moveRobot(double x, double y, double yaw) {
        // add reverse parameter? and encapsulate that here?
        // or add another method, moveRobotDirectional, which also
        // takes a reverse parameter, and calls this

        // TODO: do we want absolute controls?
        // focus on pedropathing first, and get limelight to be able to
        // update pedropathing, then possibly try to implement them
        // would be using lots of the same code as well

        double leftFrontPower = x - y + yaw;
        double leftRearPower = x + y + yaw;
        double rightFrontPower = x + y - yaw;
        double rightRearPower = x - y - yaw;

        // TODO: do we want to change how we do this?

        // Normalize wheel powers to be less than 1.0
        // abs and max the previous 4 values
        double max = Arrays.stream(
                new double[]{leftFrontPower, leftRearPower, rightFrontPower, rightRearPower})
                .map(Math::abs)
                .max()
                .getAsDouble();

        if (max > 1.0) {
            Arrays.stream(new Double[]{leftFrontPower, leftRearPower, rightFrontPower, rightRearPower}).map(n -> n /= max);
           // leftFrontPower /= max;
           // rightFrontPower /= max;
           // leftRearPower /= max;
           // rightRearPower /= max;
        }

        setPower(leftFrontPower, leftRearPower, rightFrontPower, rightRearPower);
    }

    public void stopRobot() {
        setPower(0, 0, 0, 0);
    }

    public void setPower(double leftFront, double leftRear, double rightFront, double rightRear) {
        hardware.leftFrontDriveMotor.setPower(leftFront);
        hardware.leftRearDriveMotor.setPower(leftRear);
        hardware.rightFrontDriveMotor.setPower(rightFront);
        hardware.rightRearDriveMotor.setPower(rightRear);
    }
}
