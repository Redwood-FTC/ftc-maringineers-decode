package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Manages the robot's autonomous.
 */
@Autonomous(name = "Autonomous Mode", group = "Auto")
public class AutonomousMode extends OpMode {
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

    public void loop() {
      control.update();
    }

    /**
     * Does nothing yet.
     */
    public void stop() {

    }
}
