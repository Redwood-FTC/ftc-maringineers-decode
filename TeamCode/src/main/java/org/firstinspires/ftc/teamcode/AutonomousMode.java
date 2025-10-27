package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "Autonomous Mode", group = "Auto")
public class AutonomousMode extends LinearOpMode {
    private Control control;

    public void runOpMode() throws InterruptedException {
        initInit();

        while (this.opModeInInit()) {
            runInit();
        }

        while (this.opModeIsActive()) {
            runActive();
        }

        runDeInit();
    }

    private void initInit() {
        control = new Control(this);
    }

    // for code that runs CONTINUOUSLY during init --- NOT code that just
    // runs once
    private void runInit() {
        while (this.opModeInInit()) {
            // nothing to do
            waitForStart();
        }
    }

    private void runActive() {
    }

    private void runDeInit() {

    }
}
