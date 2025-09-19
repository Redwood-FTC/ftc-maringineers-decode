package org.firstinspires.ftc.teamcode.layout;

import org.firstinspires.ftc.teamcode.Pickup;

public interface Layout {
    // constants will be annoying with this

    double driveForwardAmount();
    double driveStrafeAmount();
    double driveYawAmount();
    // add whatever control options are needed here

    double hangAngleAmount();
    double hangExtendAmount();

    boolean scoreArmClaw();

    boolean intakeIn();
    boolean intakeOut();
    boolean pickupArmExtendToggle();
    boolean HangTurnToggle();

    Pickup.Command pickupArm();
}
