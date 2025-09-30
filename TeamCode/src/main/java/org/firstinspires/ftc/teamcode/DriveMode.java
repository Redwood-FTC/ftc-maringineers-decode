package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// the primary drive mode file
// basically everything encapsulated in other classes --- this class should be
// little other than the init
// TODO: do we want to instantiate the hardware, drive, control, etc here?
// or in Control?
// ideally Control I think, since auto will need to do the same thing
// TODO: what in the world does 'group' even do
@TeleOp(name = "Drive Mode", group = "Drive")
public class DriveMode extends LinearOpMode {
    private Control control;

    public void runOpMode() throws InterruptedException {
        initInit();

        while (this.opModeInInit()) {
        runInit();
        }

        while (this.opModeIsActive()) {
        control.initTeleOp();
        runActive();
        }

        runDeInit();
    }

    // if runInit is the run for init mode, and initrun is the init for
    // run mode, then initinit is the init for init mode
    private void initInit() {
        control = new Control(this);
    }

    // for code that runs CONTINUOUSLY during init --- NOT code that just
    // runs once
    private void runInit() {
            // nothing to do
            waitForStart();
    }

    private void runActive() {
        control.update();
    }

    private void runDeInit() {
        control.stop();
    }
}
