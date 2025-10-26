package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

// code for controlling limelight
public class Limelight {
    private OpMode opMode;
    private Hardware hardware;
    private LLResult validResult;
    private boolean resultValid;


    public Limelight(OpMode opMode, Hardware hardware) {
        this.opMode = opMode;
        this.hardware = hardware;

        hardware.limelight.pipelineSwitch(0);
        limelight.start();

        // TODO: attempt to find pose during init

        // hardware.limeLight.pipelineSwitch(0);
    }

    public void update() {
        LLResult result = hardware.limelight.getLatestResult();
        if (result != null && result.isValid()) {
            // TODO

            // probably save result in field
        }
    }

    /// CAN return null if result not valid
    public Pose3D pose() {
        if (resultValid) {
            return validResult.getBotpose();
        } else {
            return null;
        }
        // TODO! return pedropathing pose or null
    }
}
