package org.firstinspires.ftc.teamcode;

// this class will handle the logic regarding the current 'mode', that being lift mode,
// poodledoodledaisy mode, or scoring mode; each mode controlling the respective arm.
// could just be in drivemode.java, but abstracted here for concision
public class ControlMode {
    enum Mode {
        POODLE_DOODLE_DAISY,
        SCORE,
        HANG,
    }

    Mode mode;

    ControlMode() {

    }
}
