package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Manages the robot's autonomous.
 */
@Autonomous(name = "Autonomous Mode", group = "Auto")
public class AutonomousMode extends LinearOpMode {
    private Control control;

    public void runOpMode() throws InterruptedException {
        initInit();

        while (this.opModeInInit()) {
            runInit();
        }
        // while (!this.opModeIsActive()) {
        //     runInit();
        // }

        while (this.opModeIsActive()) {
            runActive();
        }

        runDeInit();
    }

    /**
     * Initialises the control object.
     */
    private void initInit() {
        // control = new Control(this);
        // control.runMenu();
    }

    /**
     * For code that runs CONTINUOUSLY during Init.
     */
    private void runInit() {
      control.update();
    }

    /**
     * No idea
     */
    private void runActive() {
      control.runAuto();
      control.update();
    }

    /**
     * No idea
     */
    private void runDeInit() {

    }
}
