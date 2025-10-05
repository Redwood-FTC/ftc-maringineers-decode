package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// the primary drive mode file
// basically everything encapsulated in other classes --- this class should be
// little other than the init
// TODO: do we want to instantiate the hardware, drive, control, etc here?
// or in Control?
// ideally Control I think, since auto will need to do the same thing
// TODO: what in the world does 'group' even do
@TeleOp(name = "Drive Mode", group = "Drive")
public class DriveMode extends OpMode {
    private Control control;

    // if runInit is the run for init mode, and initrun is the init for
    // run mode, then initinit is the init for init mode
    public void init() {
        control = new Control(this);
    }

    // for code that runs CONTINUOUSLY during init --- NOT code that just
    // runs once
    public void init_loop() {
        control.update();
    }

    public void start() {
        control.start();
    }

    public void loop() {
        control.update();
    }

    public void stop() {
        control.stop();
    }
}
