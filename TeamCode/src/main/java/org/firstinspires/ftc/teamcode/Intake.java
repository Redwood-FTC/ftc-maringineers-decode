package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.layout.Layout;

import java.util.Arrays;

// file containing public interface for driving, primarily controls pedropathing
// etc.
public class Intake {
    private Hardware hardware;
    private OpMode opMode;
    private Layout layout;

    private double INTAKE_SPEED = 1.0;

    public Intake(OpMode opMode, Hardware hardware, Layout layout) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.layout = layout;
    }

    public void update() {
        if (layout.run_intake()) {
            spin();
        } else {
            stop();
        }
    }

    // TODO: warm_up mechanism

    public void spin() {
      hardware.intakeServo.setPosition(1.0);
    }

    public void stop() {
      hardware.intakeServo.setPosition(0.0);
    }
}

