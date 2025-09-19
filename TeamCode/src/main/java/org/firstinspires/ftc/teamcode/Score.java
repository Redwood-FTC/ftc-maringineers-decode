package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Score {
    private Hardware hardware;

    private Position position;
    private boolean scoreClawArmPressed;
    private boolean reverseScoreArmClawPressed = false;

    public enum Position {
        StartingPosition,
        IdlePosition,
        ClawPickup,
        ClawDeliverPhase1High,
        ClawDeliverPhase1Low,
        ClawDeliverPhase2High,
        ClawDeliverPhase2Low,
        Transition,
        Hold,
        DeliverHigh,
        DropHigh,
        DeliverLow,
        DropLow,
        // bucket positions
    }

    private boolean deliverHigh = true;
    private boolean deliverPressed = false;

    Score(Hardware hardware) {
        this.hardware = hardware;

        if (!hardware.scoreArmLimitSensor.isPressed()) {
//            throw new IllegalArgumentException("Score arm must start fully retracted");
        }

        // TODO A
        hardware.motors.scoreMotor.setPower(Hardware.Motors.SCORE_MOTOR_POWER);

        scoreClawArmPressed = false;

        position = Position.IdlePosition;
//        position = Position.Transition;
        // TODO: use score hanging thing for switching pickup modes, in drive, so we can also
        // control the bucket
        // maybe theoretically already in this position to start with?
        hardware.motors.scoreMotor.setTargetPosition(getMotorPosition(position));
        hardware.servos.bucketServo.setPosition(getServoPosition(position));
    }

    public void ejectSpecimen(boolean eject) {
        if (eject) {
            hardware.servos.ejectActuator.setPosition(1);
        } else {
            hardware.servos.ejectActuator.setPosition(0);
        }
    }

    public void toggleDeliverPosition(boolean doToggle, Telemetry tel) {
        if (doToggle && !deliverPressed) {
            deliverHigh = !deliverHigh;
            deliverPressed = true;
        } else if (!doToggle && deliverPressed) {
            deliverPressed = false;
        }
    }

    public boolean deliverHigh() {
        return deliverHigh;
    }

    public void moveArm(boolean scoreArmClaw) {
        if (scoreArmClaw && !scoreClawArmPressed) {
            scoreClawArmPressed = true;
            switch (position) {
                case StartingPosition:
                case IdlePosition:
                case ClawPickup:
                    position = deliverHigh ? Position.ClawDeliverPhase1High : Position.ClawDeliverPhase1Low;
                    break;
                case ClawDeliverPhase1High:
                case ClawDeliverPhase1Low:
                    position = deliverHigh ? Position.ClawDeliverPhase2High : Position.ClawDeliverPhase2Low;
                    break;
                case ClawDeliverPhase2High:
                case ClawDeliverPhase2Low:
                    position = Position.ClawPickup;
                    break;
            }
        } else if (!scoreArmClaw) {
            scoreClawArmPressed = false;
        }
        updatePositions();
    }

    public void updateArm() {
        switch (position) {
            case ClawDeliverPhase1High:
            case ClawDeliverPhase1Low:
                position = deliverHigh ? Position.ClawDeliverPhase1High : Position.ClawDeliverPhase1Low;
                break;
            case ClawDeliverPhase2High:
            case ClawDeliverPhase2Low:
                position = deliverHigh ? Position.ClawDeliverPhase2High : Position.ClawDeliverPhase2Low;
                break;
            default:
                break;
        }
    }

    public void moveArmReverse(boolean scoreArmClaw) {
        if (scoreArmClaw && !reverseScoreArmClawPressed) {
            reverseScoreArmClawPressed = true;
            switch (position) {
                case StartingPosition:
                case IdlePosition:
                case ClawPickup:
                    position = deliverHigh ? Position.ClawDeliverPhase2High : Position.ClawDeliverPhase2Low;
                    break;
                case ClawDeliverPhase1High:
                case ClawDeliverPhase1Low:
                    position = Position.ClawPickup;
                    break;
                case ClawDeliverPhase2High:
                case ClawDeliverPhase2Low:
                    position = deliverHigh ? Position.ClawDeliverPhase1High : Position.ClawDeliverPhase1Low;
                    break;
            }
        } else if (!scoreArmClaw) {
            reverseScoreArmClawPressed = false;
        }
        updatePositions();
    }

    public void setPosition(Position position) {
        this.position = position;
        updatePositions();
    }

    private int getMotorPosition(Position position) {
        switch (position) {
            case IdlePosition:
            case ClawPickup:
            case Transition:
            case Hold:
                return 0;
            case ClawDeliverPhase1High:
                return 2000;
            case ClawDeliverPhase2High:
                // lowered from 1460, find a good value
                return 1400;
            case ClawDeliverPhase1Low:
                return 350;
            case ClawDeliverPhase2Low:
                return 0;
            case DeliverHigh: // TODO for motor positions
            case DropHigh:
                return 3750;
            case DeliverLow:
            case DropLow:
                return 1900;
            default:
                return 0;
        }
    }

    private double getServoPosition(Position position) {
        switch (position) {
            // TODO: find values
            // what do we want for when it's idle?
            // for the moment, find the position that'll get it to be at a T/4 rad angle, and have
            // everything set it to be that, since we don't need it to be anywhere else
            case IdlePosition:
                // .53 has it at a ~90 degree angle
                // .65 has it ~270, 180 offset from .53
                // .69 starts it 3/4 rad from the 90 deg from claw deliver,
                // so it doesn't impact the pickup arm
                // it also conveniently makes the googly eyes very visible
//                return 0.715; // determine what increase needed to get it to 90 deg
                return 0.69 + 0.13;
//            return 0.53 + 0.13;
            case ClawPickup:
                // determine what to put here to make it raise up
                return 0.6 + 0.13;
            case ClawDeliverPhase1High:
            case ClawDeliverPhase1Low:
            case ClawDeliverPhase2High:
            case ClawDeliverPhase2Low:
                // *seems* ok, may need adjustment for claw to work
                return 0.53 + 0.13;
            case DeliverHigh:
            case DeliverLow:
            case Hold:
            case Transition:
                // .57 or .56 probably
                return 0.542 + 0.13;
            case DropHigh:
            case DropLow:
                return 0.44; // ?????????
            default:
                return 0.5 + 0.13;
        }
    }

    // we'll want to have an enum for the different heights, and take that as the argument, and
    // use constants to determine where to go for each enumerator
    // but for now, just manually move up and down
    private void updatePositions() {
        hardware.servos.bucketServo.setPosition(getServoPosition(position));
        hardware.motors.scoreMotor.setTargetPosition(getMotorPosition(position));
    }

    public Position position() {
        return position;
    }

    public boolean scoreClawArmPressed() {
        return scoreClawArmPressed;
    }
}
