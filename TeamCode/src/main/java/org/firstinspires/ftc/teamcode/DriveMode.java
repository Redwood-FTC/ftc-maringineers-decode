package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.layout.Default;
import org.firstinspires.ftc.teamcode.layout.Layout;

// the primary drive mode file
// ideally, we have separate files for pretty much everything,
// thus enabling us to not only easily reuse code between drive and autonomous,
@TeleOp(name = "Drive Mode", group = "Drive")
public class DriveMode extends LinearOpMode {
    Hardware hardware;
    Tel tel;
    Drive drive;
//    Hang hang;
//    Score score;
    Layout layout;
//    Pickup pickup;
    Control control;

    private int frame = 0;

    // rename to run
    public void runOpMode() throws InterruptedException {
        layout = new Default(this);
        hardware = new Hardware(this);
        drive = new Drive(hardware);
//        hang = new Hang(hardware);
//        score = new Score(hardware);
//        pickup = new Pickup(hardware, telemetry);
        control = new Control(layout, hardware, telemetry, gamepad1);
        tel = new Tel(telemetry)
                .servos(hardware)
                .gamepad(this)
                .motors(hardware)
                // .hang(control.hang())
                .limitSwitches(hardware)
                .counter()
                .score(control.score());

        tel.update();

        // use default layout here for NOW! THIS IS A TEMPORARY SOLUTION AND WILL NOT STAY HERE
        // UNTIL THE END OF TIME
        // TODO: instead of implementing linearopmode here, just have a run function, and then let other
        // files, for different layouts etc mainly, do that
//        control = new Control(new Default(this));
        // the layout would have been passed into the constructor

        // no more init to do, block until start
        runInit();

        runActive();

        // for driving, how to structure it, threads or async-esque

        // constructor to initialize struct, then
    }

    private void runInit() {
        while (this.opModeInInit()) {
            // nothing to do
            waitForStart();
        }
    }

    private void runActive() {
        // for pickup, set it to idle
        // then, once we have all the pickup and score functions working, set up the control
        // construct
//        pickup.moveArm(Pickup.Command.Pickup, layout.scoreArmClaw());

       // TODO NEXT TIME:
        // when picking up, we can Hover, and Pickup, because we currently don't have much clearance
        // how to reach specimens at the back?

        // TODO: find values for pickup again, get a control scheme working, just in drivemode

        // TODO: THiS IS ALL VERY TEMP CODE, TO BE ABSTRACTED INTO A FILE WITH PROPER PRACTICES
        while (this.opModeIsActive()) {
            // use b to toggle between modes, in hang mode, a does what it currently does,
            // in pickup mode, there are several stages:
            // when the pickup arm is not extended, when we extend it, and can toggle between
            // hovering and not with right back shoulder
            // then, right back shoulder to go through stages, after the pickup, it does the transition,
            // and does not allow controlling it until after t1 ms, at which point the pixel is in the bucket
            // then, a will toggle the arm like with other mode

            // first: the two overarching modes


            // use left shoulder, right shoulder, to go between stages

            frame++;
            if ((true || (frame % 2) == 0)) {
                control.update();

                telemetry.addData("reverseDrive: ", control.reverseDrive());
                if (control.subMode()== 1 && control.reverseDrive()){
                    drive.moveRobot(-layout.driveForwardAmount()/5, -layout.driveStrafeAmount()/5, layout.driveYawAmount()/5);
                }
                else if (control.subMode()== 1){
                    drive.moveRobot(layout.driveForwardAmount()/5, layout.driveStrafeAmount()/5, layout.driveYawAmount()/5);
                }
                else if (control.reverseDrive()) {
                    drive.moveRobot(-layout.driveForwardAmount(), -layout.driveStrafeAmount(), layout.driveYawAmount());
//                }
//                else if (control.yPressed()) {
//                    drive.moveRobot(layout.driveForwardAmount()/5, layout.driveStrafeAmount()/5, layout.driveYawAmount()/5);
                } else {
                    drive.moveRobot(layout.driveForwardAmount(), layout.driveStrafeAmount(), layout.driveYawAmount());
                }

                telemetry.addData("mode: ", control.mode());
                telemetry.addData("subMode: ", control.subMode());

                tel.update();

                // TODO NEXT TIME: set it so that a or whatever sets the score arm low, and b
                // or whatever sets it high
            }
        }
        drive.stopRobot();
    }


}
