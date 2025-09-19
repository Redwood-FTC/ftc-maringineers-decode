package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pickup {
    public enum Position {
        Start,
        Idle,
        // has to be higher when extending the arm so it doesn't catch
        Extend,
        Retract,
        Pickup,
        Transfer,
    }

    public enum Command {
        Pickup,
        Transition,
        Idle,
        None,
    }

    public enum Intake {
        In,
        Out,
        Off,
    }

    // TODO NEXT TIME:
    // determine what values needed for Pickup command, then for transitioning pixel, then back
    // to idle, ie., times and stuff

    private Hardware hardware;
    private Position position;
    private Command command;
    private long commandTime;
    boolean commandPressed;
    // temp
    private Telemetry telemetry;

    Pickup(Hardware hardware, Telemetry telemetry) {
        this.hardware = hardware;
        this.telemetry = telemetry;

        if (!hardware.pickupArmLimitSensor.isPressed()) { //
            // test this
//            throw new IllegalArgumentException("Pickup arm must start fully retracted");
        }

        command = Command.None;
        commandPressed = false;
        commandTime = 0;

//        position = Position.Start;

        // .5 is perpendicular, .6 is like 60 degrees up from that
        // probably .5 or .49ish for extending? find a value *just* a bit lower for that downward
        // angle
        // TODO temp
        // TODO: determine idle position, determine starting position, determine extending
        // position, determine pickup position
        // starting: .55, .7
        // idle: have it same as idle? it shouldn't interfere with the bucket, it shouldn't
        // interfere with hanging, it should be fine fox extending, just peachy
        // extend: .55, .5
        // pickup: .48, .46? retune once we have actual torque on the base servo
//        hardware.servos.pickupBaseServo.setPosition(0.6);
//        hardware.servos.pickupBaseServo.setPosition(0.48);
        // .6 is like, 60 degrees
        // .5-something for extending?
        // .475 seems to be just under 90
        // intake arm is too heavy, this can't do anything
//        hardware.servos.pickupShoulderServo.setPosition(0.5);
//        hardware.servos.pickupShoulderServo.setPosition(0.46); //.57
        // set shoulder/base serv5

        position = Position.Idle;
        // TODO: find new values
//        position = Position.Transfer;
        // TODO: find values that don't break the robot
        updatePositions();

//        hardware.motors.pickupMotor.setPower(1);
        hardware.motors.pickupMotor.setPower(1);
    }

    public void moveArm(Command command, boolean intake) {
//        public void moveArm(Command command, boolean intake) {
        // TODO: take a boolean instead, which cycles through them?
        // or no, since we'll have the control construct, so just take a command for now

        // use intake to tell whether or not we should be hovering

//        if (intake) {
//            hardware.servos.intakeServo.setPower(-1);
//        } else {
//            hardware.servos.intakeServo.setPower(1);
//        }

        switch (command) {
            case Transition:
            case Pickup:
            case Idle:
            if (this.command != command) {
//                commandPressed = true;
                this.command = command;
                commandTime = System.currentTimeMillis();
            }
                break;
            case None:
//                commandPressed = false;
                break;
        }

        // shoulder servo is constant
        // extend arm, dextend arm
        // one button to run intake one way, another to run it other way

        switch (this.command) {
            case Transition:
                position = Position.Transfer;
                if ((System.currentTimeMillis() - commandTime) < 2500) {
                    telemetry.addLine("t1");
                    hardware.servos.intakeServo.setPower(-1);
                    // position = retract
                    position = Position.Retract;
                    // intake IN
                } else if ((System.currentTimeMillis() - commandTime) < 5500) {
                    telemetry.addLine("t2");
                    hardware.servos.intakeServo.setPower(-1);
                    // position = transition
                    position = Position.Transfer;
                    // intake OUT
                } else if ((System.currentTimeMillis() - commandTime) < 6000) {
                    hardware.servos.intakeServo.setPower(1);
                    telemetry.addLine("t3");
                } else {
                    position = Position.Retract;
                    hardware.servos.intakeServo.setPower(1);
                    telemetry.addLine("t4");
                    // go to idle once we finish
                    // TODO: should it happen here, or in the controlling code
//                    this.command = Command.Idle;
                }
                // assume transitioning from pickup
                // set base servo up, set shoulder servo way up, keep intake servo in,
                // set bucket a bit back
                break;
            case Idle:
                position = Position.Idle;
                hardware.servos.intakeServo.setPower(0);
                // assume transitioning from transition
                break;
            case Pickup:
                // TODO:
                // set all to extend position
                // set all to pickup position
                // assume transitioning from idle
                // unconditional stuff here

//                hardware.motors.pickupMotor.setTargetPosition(Hardware.Motors.PICKUP_MOTOR_MAX_POSITION);
                if (((System.currentTimeMillis() - commandTime) < 1000) || !intake) { // does this time need to be different?
                    // this is while the arm is extending, so it needs to be higher so as not
                    // to hit anything
                    position = Position.Extend;
                    hardware.servos.intakeServo.setPower(1);
//                    updatePositions();
//                    hardware.servos.pickupBaseServo.setPosition(getBaseServoPosition(Position.Extend));
                    // set base servo to first height
                    // set shoulder servo straight to avoid collision

                } else {
                    // the arm is far enough out that we can lower it for pickup
                    position = Position.Pickup;
                    hardware.servos.intakeServo.setPower(-1);
//                    updatePositions();
//                    hardware.servos.pickupBaseServo.setPosition(getBaseServoPosition(Position.Pickup));
                    // set base servo lower
                    // set shoulder servo low
                }
                break;
            case None:
            default:
                break;
        }

        telemetry.addData("commandtime: ", commandTime);
        telemetry.addData("command: ", command);
        telemetry.addData("thiscommand: ", this.command);
        updatePositions();

//        hardware.motors.pickupMotor.setTargetPosition(getMotorPosition(Position.Transfer));
    }

    public boolean transitionFinished() {
        return !((System.currentTimeMillis() - commandTime) < 6500);
    }

    private void updatePositions() {
        // TODO:
        hardware.motors.pickupMotor.setTargetPosition(getMotorPosition(position));
        hardware.servos.pickupBaseServo.setPosition(getBaseServoPosition(position));
        hardware.servos.pickupShoulderServo.setPosition(getShoulderServoPosition(position));
    }

    private int getMotorPosition(Position position) {
        switch (position) {
            // TODO: get values
            case Start:
            case Idle:
                // try to stop the motor from killing itself lol, so it isn't straining the motor
                // INSTANTLY on start
                // 10 should be a minimal effect, raise if it keeps slipping
                // TODO: if it still slips and strains itself, then
                return 10;
            case Retract:
                return 400;
            case Pickup:
                return 1550;
            case Extend:
                // this seems to be almost perfectly (less like half a cm) at it's max length
                // IF it's started all the way back
                return 1550;
            case Transfer:
                // we'll want it extended a little bit
                return 275; // 0?
            default:
                // should throw an exception here, not sure what to throw
                return 0;
        }
    }

    private double getBaseServoPosition(Position position) {
        switch (position) {
            // TODO: get values
            case Start:
            case Idle:
                return 0.65;
            case Pickup:
                return 0.49;
            case Extend:
                return 0.55;
            case Retract:
            case Transfer:
                // WHY won't it go above this????????
                return 0.925; //.65 or .7, then .999 is no change
            default:
                // should throw an exception here, not sure what to throw
                return 0.5;
        }
    }

    private double getShoulderServoPosition(Position position) {
        switch (position) {
            // TODO: get values
            case Start:
            case Idle:
                return 0.7;
            case Pickup:
                return 0.59;
            case Retract:
            case Extend:
                return 0.55;
            case Transfer:
                return 0.87; //.15
            default:
                // should throw an exception here, not sure what to throw
                return 0.5;
        }
    }

    public Position getPosition() {
        return position;
    }

    public long commandTime() {
        return commandTime;
    }
}
