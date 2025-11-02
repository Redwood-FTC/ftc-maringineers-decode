package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.DriveEncoderConstants;
import com.pedropathing.ftc.localization.constants.ThreeWheelIMUConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
    // this mass is a GUESS
    // TODO: properly measure it (once we actually have the robot somewhat,
    // probably)
                .mass(1.5);

    public static MecanumConstants driveConstants = new MecanumConstants()
        .maxPower(1)
        .leftFrontMotorName("leftFrontDriveMotor")
        .leftRearMotorName("leftRearDriveMotor")
        .rightFrontMotorName("rightFrontDriveMotor")
        .rightRearMotorName("rightRearDriveMotor")
        .useBrakeModeInTeleOp(true);
        // possibly redundant?
        //.leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
        // .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
        // .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
        // .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)

    // TODO: get real value
    private static double ForwardTicksToInchesMultiplier = .026927;
    // TODO: get real value
    private static double StrafeTicksToInchesMultiplier = .034992;
    // TODO: get real value
    private static double TurnTicksToInchesMultiplier = .010444;

    // TODO: once we have deadwheels
    public static ThreeWheelIMUConstants localizerConstants = new ThreeWheelIMUConstants()
            .forwardTicksToInches(ForwardTicksToInchesMultiplier)
            .strafeTicksToInches(StrafeTicksToInchesMultiplier)
            .turnTicksToInches(TurnTicksToInchesMultiplier)
            .leftPodY(1)
            .rightPodY(-1)
            .strafePodX(-2.5)
    // TODO: left/right pods should be in motor encoder ports 0 and 3
    // 1 or 2 for the strafe encoder
            // 0
            .leftEncoder_HardwareMapName("leftFrontDriveMotor")
            // 3
            .rightEncoder_HardwareMapName("rightRearDriveMotor")
            // 1
            .strafeEncoder_HardwareMapName("leftRearDriveMotor")
            .leftEncoderDirection(Encoder.FORWARD)
            .rightEncoderDirection(Encoder.FORWARD)
            .strafeEncoderDirection(Encoder.FORWARD)
            .IMU_HardwareMapName("imu")
            .IMU_Orientation(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
    
    // TODO: only use until we have deadwheels
    // public static DriveEncoderConstants localizerConstants = new DriveEncoderConstants()
    //     .leftFrontMotorName("leftFrontDriveMotor")
    //     .leftRearMotorName("leftRearDriveMotor")
    //     .rightFrontMotorName("rightFrontDriveMotor")
    //     .rightRearMotorName("rightRearDriveMotor")
    //     // .leftFrontEncoderDirection(Encoder.FORWARD)
    //     // .leftRearEncoderDirection(Encoder.FORWARD)
    //     // .rightFrontEncoderDirection(Encoder.FORWARD)
    //     // .rightRearEncoderDirection(Encoder.FORWARD)
    //     // TODO: get proper values, once we have a real robot
    //     .robotWidth(18)
    //     .robotLength(18)
    //     .forwardTicksToInches(ForwardTicksToInchesMultiplier)
    //     .strafeTicksToInches(StrafeTicksToInchesMultiplier)
    //     .turnTicksToInches(TurnTicksToInchesMultiplier);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                // .driveEncoderLocalizer(localizerConstants)
                .threeWheelIMULocalizer(localizerConstants)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}
