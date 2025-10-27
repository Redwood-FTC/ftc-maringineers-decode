package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

/**
 * Controls the limelight.
 */
public class Limelight {
    private OpMode opMode;
    private Hardware hardware;
    private LLResult validResult;
    private boolean resultValid;


    /**
     * Sets the limelight pipeline and turns it on.
     *
     * @param opMode   the OpMode object
     * @param hardware the Hardware object
     */
    public Limelight(OpMode opMode, Hardware hardware) {
        this.opMode = opMode;
        this.hardware = hardware;

        hardware.limelight.pipelineSwitch(0);
        hardware.limelight.start();

        // TODO: attempt to find pose during init
    }

    /**
     * Updates the valid result based on what the limelight last saw.
     */
    public void update() {
        LLResult result = hardware.limelight.getLatestResult();
        if (result != null && result.isValid()) {
            validResult = result;
        } else {
            validResult = null;
        }
    }

    /**
     * returns whether result is valid.
     *
     * @return true if result is valid, false otherwise
     */
    public boolean resultValid() {
        return resultValid;
    }

    /**
     * Returns a pose or null.
     *
     * @return a BotPose, or null is result isn't valid
     */
    public Pose3D pose() {
        if (resultValid) {
            return validResult.getBotpose();
        } else {
            return null;
        }
        // TODO! return pedropathing pose or null
    }
}
