package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

// Reed said launch speed should be .5, go to midfield and launch from there

/**
 * Manages the robot's autonomous.
 */
@Autonomous(name = "Far Autonomous", group = "Auto")
public class FarAutonomous extends OpMode {
    private Control control;

    /**
     * Initialises the control object.
     */
    public void init() {
        control = new Control(this);
        control.runMenu();
    }

    /**
     * For code that runs CONTINUOUSLY during Init.
     */
    public void init_loop() {
        control.update();
    }

    /**
     * Run autonomous.
     */
    public void start() {
        control.runAuto();
    }

    /**
     * Update control
     */
    public void loop() {
        control.update();

        control.movesAwa
    }

    /**
     * Does nothing yet.
     */
    public void stop() {

    }
}
