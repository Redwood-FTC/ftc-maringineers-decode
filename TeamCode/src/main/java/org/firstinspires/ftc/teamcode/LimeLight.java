package org.firstinspires.ftc.teamcode;

// code for controlling limelight
public class LimeLight {
    private LinearOpMode opMode;
    private Hardware hardware;


    public LimeLight(LinearOpMode opMode, Hardware hardware) {
        this.opMode = opMode;
        this.hardware = hardware;

        hardware.limelight.pipelineSwitch(0);
    }

    public void update() {
        LLResult result = hardware.limelight.getLatestResult();
        if (result != null && result.isValid()) {
            // TODO
        }
    }
}
