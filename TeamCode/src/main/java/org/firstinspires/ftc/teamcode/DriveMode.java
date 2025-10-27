package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// TODO: do we want to instantiate the hardware, drive, control, etc here?
// or in Control?
// ideally Control I think, since auto will need to do the same thing
// TODO: what in the world does 'group' even do

/**
 * The primary drive mode class. Most of the code is encapsulated in other classes, this shoul just
 * initialise it.
 */
@TeleOp(name = "Drive Mode", group = "Drive")
public class DriveMode extends OpMode {
    private Control control;

    // if runInit is the run for init mode, and initrun is the init for
    // run mode, then initinit is the init for init mode
    public void init() {
        control = new Control(this);
    }

    /**
     * For code that runs CONTINUOUSLY during init.
     */
    public void init_loop() {
        control.update();
    }

    /**
     * Starts control.
     */
    public void start() {
        control.start();
    }

    /**
     * Updates control.
     */
    public void loop() {
        control.update();
    }

    /**
     * Stops control.
     */
    public void wstop() {
        control.stop();
    }
}
