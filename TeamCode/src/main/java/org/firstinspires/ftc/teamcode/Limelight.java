package org.firstinspires.ftc.teamcode;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

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

    LLResultTypes.FiducialResult target;
    /**
     * Updates the valid result based on what the limelight last saw. If it's valid, set the Pose3D
     * pose and debug telemetry. If it's not, send 'no pose' to telemetry.
     */
    public void update() {
        LLResult result = hardware.limelight.getLatestResult();
        if (result != null && result.isValid()) {
            validResult = result;
        } else {
            validResult = null;
        }
        validResult = result;

        // telemetryM.debug(
        if (result.isValid()) {
            // telemetryM.debug("pose", result.getBotpose().toString());
            Pose3D pose = result.getBotpose();
            Position pos = pose.getPosition();
            // telemetryM.debug("pose", pose.toString());
            // telemetryM.debug("theoretical distance from goal", Math.sqrt(Math.pow(pos.x, 2) + Math.pow(pos.y, 2)));
            // telemetryM.debug("tx", result.getTx());
            // telemetryM.debug("txnc", result.getTxNC());
            // telemetryM.debug("ty", result.getTy());
            // telemetryM.debug("tync", result.getTyNC());
            target = null;
            for (int i = 0; i < result.getFiducialResults().size(); ++i) {
                target = result.getFiducialResults().get(i);
                if (target.getFiducialId() != 24) {
                    continue;
                }
                telemetryM.debug("skew: ", target.getSkew());
                telemetryM.debug("cameraPoseTargetSpace: ", target.getCameraPoseTargetSpace());
                telemetryM.debug("robotPoseTargetSpace: ", target.getRobotPoseTargetSpace());
                telemetryM.debug("targetPoseCameraSpace: ", target.getTargetPoseCameraSpace());
                telemetryM.debug("targetPoseRobotSpace: ", target.getTargetPoseRobotSpace());
                telemetryM.debug("targetXDegrees: ", target.getTargetXDegrees());
                telemetryM.debug("targetYDegrees: ", target.getTargetYDegrees());
                telemetryM.debug("targetXDegreesNoCrosshair: ", target.getTargetXDegreesNoCrosshair());
                telemetryM.debug("targetYDegreesNoCrosshair: ", target.getTargetYDegreesNoCrosshair());
            }
            // telemetryM.debug("rz", result.getRz());

            // telemetryM.debug("", result.getBotpose().getOrientation().yaw);
            telemetryM.debug("yaw", pose.getOrientation().getYaw());
            telemetryM.debug("pitch", pose.getOrientation().getPitch());
            telemetryM.debug("roll", pose.getOrientation().getRoll());

            // camposetargspace z .75-2ish for shooting

            if (Math.abs(result.getTx()) < 4) {
                telemetryM.debug("can shoot");

                // if we're close, use one range
                // if we're far, use another

                // TODO: get the led working
                hardware.canShootLed.setState(false);
                // hardware.canShootLedServo.setPower(0);
                // hardware.canShootLedServo.setPosition(1.0);
            } else {
                telemetryM.debug("can't shoot");
                hardware.canShootLed.setState(true);
                // hardware.canShootLED.enable(false);
                // hardware.canShootLedServo.setPower(0);
                // hardware.canShootLedServo.setPosition(0.5);
            }
        } else {
            opMode.telemetry.addLine("no pose");
        }
    }

    // negative is right
    public double angle_from_target() {
        return target.getTargetXDegrees();
    }

    public boolean resultValid() {
        return validResult == null;
    }

    /**
     * Returns validResult.
     *
     * @return validResult
     */
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
