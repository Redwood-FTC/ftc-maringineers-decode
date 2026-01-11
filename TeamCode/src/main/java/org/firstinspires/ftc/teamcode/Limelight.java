package org.firstinspires.ftc.teamcode;

import com.bylazar.telemetry.TelemetryManager;
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
    private TelemetryManager telemetryM;


    /**
     * Sets the limelight pipeline and turns it on.
     *
     * @param opMode   the OpMode object
     * @param hardware the Hardware object
     */
    public Limelight(OpMode opMode, Hardware hardware, TelemetryManager telemetryM) {
        this.opMode = opMode;
        this.hardware = hardware;
        this.telemetryM = telemetryM;

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

        // telemetryM.debug(
        if (result.isValid()) {
            // telemetryM.debug("pose", result.getBotpose().toString());
            telemetryM.debug("pose", result.getBotpose().toString());
            telemetryM.debug("tx", result.getTx());
            telemetryM.debug("txnc", result.getTxNC());
            telemetryM.debug("ty", result.getTy());
            telemetryM.debug("tync", result.getTyNC());

            Pose3D pose = result.getBotpose();
            // telemetryM.debug("", result.getBotpose().getOrientation().yaw);
            telemetryM.debug("yaw", pose.getOrientation().getYaw());
            telemetryM.debug("pitch", pose.getOrientation().getPitch());
            telemetryM.debug("roll", pose.getOrientation().getRoll());

            if (Math.abs(result.getTx()) < 4) {
                telemetryM.debug("can shoot");
                hardware.canShootLedServo.setPosition(1.0);
            } else {
                telemetryM.debug("can't shoot");
                hardware.canShootLedServo.setPosition(0.5);
            }
        } else {
            opMode.telemetry.addLine("no pose");
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

    public LLResult result() {
        return validResult;
    }

    // /**
    //  * Returns a pose or null.
    //  *
    //  * @return a BotPose, or null is result isn't valid
    //  */
    // public Pose3D pose() {
    //     if (resultValid) {
    //         return validResult.getBotpose();
    //     } else {
    //         return null;
    //     }
    //     // TODO! return pedropathing pose or null
    // }
}
