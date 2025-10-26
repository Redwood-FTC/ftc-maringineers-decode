package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.layout.Layout;

import java.util.Arrays;

// file containing public interface for driving, primarily controls pedropathing
// etc.
public class Launch {
    private Hardware hardware;
    private OpMode opMode;
    private Layout layout;

    private double LAUNCH_SPEED = 1.0;

    public Launch(OpMode opMode, Hardware hardware, Layout layout) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.layout = layout;
    }

    public void update() {
        if (layout.run_launch()) {
            spin();
        } else {
            stop();
        }
    }

    // TODO: warm_up mechanism

    public void spin() {
        hardware.leftLaunchMotor.setPower(LAUNCH_SPEED);
        hardware.rightLaunchMotor.setPower(LAUNCH_SPEED);
    }

    public void stop() {
        hardware.leftLaunchMotor.setPower(0.0);
        hardware.rightLaunchMotor.setPower(0.0);
    }
}

