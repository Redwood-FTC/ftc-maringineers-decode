package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

// code for controlling limelight
public class LimeLight {
    private OpMode opMode;
    private Hardware hardware;


    public LimeLight(OpMode opMode, Hardware hardware) {
        this.opMode = opMode;
        this.hardware = hardware;

        // hardware.limeLight.pipelineSwitch(0);
    }

    public void update() {
        // LLResult result = hardware.limeLight.getLatestResult();
        // if (result != null && result.isValid()) {
        //     // TODO
        // }
    }
}
