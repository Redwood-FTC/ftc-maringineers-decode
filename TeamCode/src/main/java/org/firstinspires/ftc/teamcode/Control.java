package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.layout.Layout;

// this may not even be necessary?

// class to handle any input from controller
public class Control {
    // class to control the exact way that the robot is controlled, because in order to make
    // it work with one controller, we'll be having a couple of 'modes' that change how
    // we control the robot

    public enum Mode {
        Hang,
        Bucket,
        Wall,
    }

    // use a class with getters
    private Layout layout;
    private Telemetry telemetry;
    private Hardware hardware;
    private Pickup pickup;
    private Score score;
    private Hang hang;
    private Gamepad gamepad;

    private Mode mode = Mode.Wall;
    private boolean hangPressed = false;
    private boolean wallPressed = false;
    private boolean bucketPressed = false;
    private int subMode = 0;
    private boolean subModePressed = false;
    private int deliverMode = 0;
    private boolean isHighBasket = true;
    private boolean deliverPressed = false;
    private boolean reverseDeliverPressed = false;
    private boolean reverseDrive = false;
//    private boolean yPressed = false;
    private boolean highBasketPressed = false;
    private boolean reverseSubModePressed = false;


    public Control(Layout layout, Hardware hardware, Telemetry telemetry, Gamepad gamepad) {
        this.layout = layout;
        this.hardware = hardware;
        this.telemetry = telemetry;
        this.gamepad = gamepad;
        hang = new Hang(hardware);
        score = new Score(hardware);
        pickup = new Pickup(hardware, telemetry);
    }

    public Hang hang() {
        return hang;
    }

    public Score score() {
        return score;
    }

    public Pickup pickup() {
        return pickup;
    }

    public int deliverMode(){return deliverMode;}

    public boolean reverseDrive() {
        return reverseDrive;
    }
//    public boolean yPressed(){ return yPressed;}

    public Mode mode() {
        return mode;
    }

    public int subMode() {
        return subMode;
    }
    public void update() {
        reverseDrive = false;

//        if (gamepad.y) {
//            if (yPressed)
//            {
//                yPressed = false;
//            }else{
//                yPressed = true;
//            }
//        }
//                hang.moveArms(layout.hangExtendAmount(), layout.hangAngleAmount());
        // TODO: swap forwards and backwards in the two modes and submodes depending on what's active
        telemetry.addData("controlgamepadb", gamepad.b);
        if (gamepad.b && !bucketPressed) {
            mode = Mode.Wall;
            bucketPressed = true;
        } else if (bucketPressed && !gamepad.b) {
            bucketPressed = false;
        }
        if (gamepad.x && !bucketPressed) {
                subMode = 0;
                deliverMode = 0;
                pickup.moveArm(Pickup.Command.Idle, false);
                score.setPosition(Score.Position.IdlePosition);

            mode = Mode.Bucket;
            bucketPressed = true;
        } else if (bucketPressed && !gamepad.x) {
            bucketPressed = false;
        }
        if (gamepad.y && !bucketPressed) {
            mode = Mode.Hang;
            bucketPressed = true;
        } else if (bucketPressed && !gamepad.y) {
            bucketPressed = false;
        }

        switch (mode) {
            case Wall:
                score.toggleDeliverPosition(gamepad.a, telemetry);
                reverseDrive = false;
                score.moveArm(gamepad.right_trigger > 0.2);
                score.moveArmReverse(gamepad.left_trigger > 0.2);
                score.ejectSpecimen(gamepad.a);
                // not ideal, but the easiest fix
                score.updateArm();
                break;
            case Bucket:

                score.ejectSpecimen(false);

            if (gamepad.right_bumper && !subModePressed) {
                ++subMode;
                if (subMode > 3) {
                    subMode = 0;
                }
                subModePressed = true;
            } else if (subModePressed && !gamepad.right_bumper) {
                subModePressed = false;
            }
            if (gamepad.left_bumper && !reverseSubModePressed) {
                --subMode;
                if (subMode < 0) {
                    subMode = 3;
                }
                reverseSubModePressed = true;
            } else if (reverseSubModePressed && !gamepad.left_bumper) {
                reverseSubModePressed = false;
            }
            // submodes
            // control the armn
//                    score.setPosition(Positi);
            switch (subMode) {
                case 0:
                    telemetry.addLine("d0");
                    // set bucket to idle here?
                    pickup.moveArm(Pickup.Command.Idle, false);
                    // setting it to idle position will dump the pixel IN THE ROBOT
                    // while this sets it to something different, so operator can see change, while
                    // not making it impossible for us to play
                    score.setPosition(Score.Position.ClawDeliverPhase1High);
                    deliverMode = 0;
                    reverseDrive = true;
                    // idle
                    // shouldn't need to set pickup arm here, pickup arm should be in idle
                    // after transition, bucket arm should be set down after 3
                    // so do nothing here?
                    break;
                case 1:
                    telemetry.addLine("d1");
                    pickup.moveArm(Pickup.Command.Pickup, (gamepad.left_trigger > 0.2));
                    reverseDrive = true;
//                          // shouldn't need to set score here
                    // pickup
                    break;
                case 2:
                    reverseDrive = false;
                    telemetry.addLine("d2");
                    pickup.moveArm(Pickup.Command.Transition, false);
                    score.setPosition(Score.Position.Transition);
                    // how to automatically transition?
//                            if (!pickup.transitionFinished()) {
//                                score.setPosition(Score.Position.Transition);
//                            }
                    if (pickup.transitionFinished()) {
                        subMode++;
                    }
                    // transition?
                    break;
                case 3:
                    reverseDrive = false;
                    telemetry.addLine("d3");
//                            pickup.moveArm(Pickup.Command.Idle, false);
                    // gamepad1.x to switch between low and high basket, default low basket
//                            score.setPosition(Position);
                    // delivering the bucket

                    // use triggers

                    pickup.moveArm(Pickup.Command.Idle, false);

                    if (gamepad.a && !highBasketPressed) {
                        isHighBasket = !isHighBasket;
                        highBasketPressed = true;
                    } else if (!gamepad.a && highBasketPressed) {
                        highBasketPressed = false;
                    }

                    if ((gamepad.right_trigger > 0.2) && !deliverPressed) {
                        ++deliverMode;
                        if (deliverMode > 2) {
                            deliverMode = 0;
                        }
                        deliverPressed = true;
                    } else if (!(gamepad.right_trigger > 0.2) && deliverPressed) {
                        deliverPressed = false;
                    }

                    if ((gamepad.left_trigger > 0.2) && !reverseDeliverPressed) {
                        --deliverMode;
                        if (deliverMode < 0) {
                            deliverMode = 2;
                        }
                        reverseDeliverPressed = true;
                    } else if (!(gamepad.left_trigger > 0.2) && reverseDeliverPressed) {
                        reverseDeliverPressed = false;
                    }

                    switch (deliverMode) {
                        case 0:
                            telemetry.addLine("x0");
                            score.setPosition(Score.Position.Transition);
                            // arm is down
                            break;
                        case 1:
                            telemetry.addLine("x1");
                            score.setPosition(isHighBasket ? Score.Position.DeliverHigh : Score.Position.DeliverLow);
                            // arm is raised
                            break;
                        case 2:
                            telemetry.addLine("x2");
                            score.setPosition(isHighBasket ? Score.Position.DropHigh : Score.Position.DropLow);
                            // arm is raised,
                            break;
                    }
                    break;
            }

                break;
            case Hang:
                score.ejectSpecimen(false);
                hang.moveArms(Math.pow(gamepad.right_trigger - gamepad.left_trigger, 3), Math.pow(gamepad.right_stick_y, 3));
                break;
        }
            telemetry.addData("pickup position: ", pickup.getPosition());
    }

    // we have two primary modes: pickup/score, and hang
    // which can be toggled between with the press of a button
    // within hang, we have stages, worry about those once the robots actually able to do it
    // for pickup/score, we have a several-stage system for getting pixels in the bucket,
    // and another system for hanging pixels

    // use shoulder buttons for stages? thus allowing us to go backwards as well?

    // up-dpad to toggle between score/hanging? toggling sets the non-active one to idle, in a way
    // that it won't interfere with the other
    // in pickup/score mode, down-dpad toggles between hanging pixels, and bucket pixels?
    // do we need a toggle?
    // for hanging, a 3-stage system as we currently have, and the pickup arm should
    // be idle while doing that
    // for pickup, the first stage is the actual intake, which, when next stage pressed,
    // extends the pickup arm in hover mode, and through the press of a button which toggles,
    // we can switch between hover, which spits out pixels, and intake, which takes in pixels,
    // has it actually down, while hover has it above, so we can actually move without hitting the wall
    // is that necessary? determine if so, once we fix the intake plate issue
    // then once we have the pixel, only if not in hover mode(?), we can go to next stage, which
    // transfers the pixel to the bucket, and only allows moving it once we've detected the
    // arms in the right place to deliver it, and then have moved the pickup arm out
    // of the way
    // we can then set the bucket to either of 3 positions, and through the use of a button,
    // dump it into the bucket
}
