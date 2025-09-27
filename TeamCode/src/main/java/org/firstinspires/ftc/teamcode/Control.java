package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.layout.Layout;

// class to provide overall controll of the robot, during teleop
// specifically wrt the intake and launch modes, and directing *how*
// the robot should be controlled --- though other classes primarily handle the
// specifics of that, with methods we will probably use in here
// as much as possible, delegate specific stuff to other classes, and try
// to keep this class pretty encapsulated, lots of functions as much as possible
public class Control {
    private Layout layout;
    private Hardware hardware;
    private Drive drive;
    private Tel tel;
    private LinearOpMode opMode;

    // TODO: probably have something like last year, with a Pickup mode,
    // for intaking, and a Launch mode, for which the front of the robot
    // is reversed

    public Control(LinearOpMode opMode) {
        layout = new Layout(opMode);
        hardware = new Hardware(opMode);
        drive = new Drive(hardware);
        tel = new Tel(opMode, hardware);

        tel.update();
    }

    public void stop() {
        drive.stopRobot();
    }

    public void update() {}
}
