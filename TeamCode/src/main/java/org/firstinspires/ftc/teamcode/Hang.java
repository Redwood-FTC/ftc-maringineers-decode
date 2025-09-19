package org.firstinspires.ftc.teamcode;

// lift code
public class Hang {
    // arms upright for most of match
    // then lean forward or something?
    // use right stick for controlling hanging arms?
    // so we give options to control the arm, which would be called when lift mode is activated,
    // done by pressing b
    // while a for controlling delivery arm
    // another button for intake?
    // then each have methods for activate/deactivate, just moving from resting to active position
    // called when switching via a/b, but in the classes providing those methods, the use of the
    // methods isn't specified

    // when intake is active, when we switch to drop arm, it puts pixel in that?
    // or there's just a button for that?
    // design it so that as soon as it has the pixel, the driver's intended to switch to the
    // scoring arm, so that it gets into position asap, and while poodledoodledaisy arm is selected,
    // scoring arm is in position to receive pixel and get into position asap
    // how to select between two methods of picking up and delivering pixel?
    // select one when selecting poodledoodledaisy arm, then just a single button for intake arm,
    // which is selected automatically

    // name it scoring arm or bucket arm? assuming it does both methods of delivery
    // poodledoodledaisy arm to be named poodledoodledaisy arm for historical reasons

    public static final boolean ENABLED = true;

    // ~3000 / 2 (seconds for full extend) / 500 (times called)
    public static final int EXTEND_RATE = 3100 / 2 / 50;
    // ~.12 between min and max, run 500 times a second
    public static final double ANGLE_RATE = .12 / 50;

    Hardware hardware;

    private int extendAmount;
    private double angleAmount;

    Hang(Hardware hardware) {
        this.hardware = hardware;
        extendAmount = 0;
        // TODO: what was the next todo for
        // TODO
        // ^^^^ this one
        angleAmount = 0;
        // TODO: update this to assume it's being called 100 times a second
        // TODO: use constant, provided in constructor? to determine how many times this is being
        // called each second

        if (!hardware.leftHangArmLimitSensor.isPressed()) { // don't think this is working,
            // troubleshoot this so that we can actually use this arm
//            throw new IllegalArgumentException("Left hang arm must start fully retracted");
        }
        if (!hardware.rightHangArmLimitSensor.isPressed()) {
//            throw new IllegalArgumentException("Right hang arm must start fully retracted");
        }

            hardware.servos.leftHangServo.setPosition(Hardware.Servos.HANG_SERVO_STARTING_POSITION + Hardware.Servos.LEFT_HANG_SERVO_OFFSET);
            hardware.servos.rightHangServo.setPosition(Hardware.Servos.HANG_SERVO_STARTING_POSITION + Hardware.Servos.RIGHT_HANG_SERVO_OFFSET);
            // set both servos to some position
            // check limit switches

            // motors

    }

    // inputs provide a rant of change, rather than the new value
    // record time since last called, and use that to figure out what the new value is
    // due to the way this works, there won't actually be any change
    // assume that the arguments are what the value has been for the entire time since last call
    // and if it would try to go past it's limits, just stop it
    // extend: [-1, 1], pos. is out; angle: [-1, 1], pos. is forward
    // semantically, these arguments represent the rate that they move, as a value within those
    // intervals; while the logic that affects what effect those actually have on the servos
    // is internal, the arguments just represent a fraction of the maximum or minimum movement rate
    public void moveArms(double extend, double angle) {
        // determine what the new values are, and set the absolute positions of them
        // TODO: throw exception instead
//        if (!((extend <= 1) && (extend >= -1) && (angle <= 1) && (angle >= -1))) {
//            throw new IllegalArgumentException("arguments to hang arms exceed limits");
//        }

        extendAmount += (int) (extend * EXTEND_RATE);
        angleAmount += (angle * ANGLE_RATE);

        // use this instead of angleAmount for the servos
//        double leftStickAngleAmount = angleAmount + Hardware.Servos.LEFT_HANG_SERVO_OFFSET;

        if (extendAmount > Hardware.Motors.HANG_MOTOR_MAX_POSITION) {
            extendAmount = Hardware.Motors.HANG_MOTOR_MAX_POSITION;
        } else if (extendAmount < Hardware.Motors.HANG_MOTOR_MIN_POSITION) {
            extendAmount = Hardware.Motors.HANG_MOTOR_MIN_POSITION;
        }
        if (angleAmount > Hardware.Servos.HANG_SERVO_MAX_POSITION) {
            angleAmount = Hardware.Servos.HANG_SERVO_MAX_POSITION;
        } else if (angleAmount < Hardware.Servos.HANG_SERVO_MIN_POSITION) {
            angleAmount = Hardware.Servos.HANG_SERVO_MIN_POSITION;
        }

        if (ENABLED) {
            // TODO: add bools to disable/enable each class, configured from the controlling mode, and
            // defaulting to disabled to force them to be enabled
            hardware.servos.leftHangServo.setPosition(angleAmount + Hardware.Servos.LEFT_HANG_SERVO_OFFSET);
            hardware.servos.rightHangServo.setPosition(angleAmount + Hardware.Servos.RIGHT_HANG_SERVO_OFFSET);

            // get motors set up!!!!

        }

        // TODO NEXT TIME:
        // find constants for the servos
        // find constants for the motors
        // both using telemetry
        // set up this to control the arms
        // then the whole fancy multi-step routine can happen once we have the actual hanging arms
    }
    // so this is probably just gonna be the first step; after getting hooked on,
    // the rest of it can probably be automated, and so I can probably just make a routine that
    // will do the same thing every time using predetermined constants

    public int extendAmount() {
        return extendAmount;
    }
    
    public double angleAmount() {
        return angleAmount;
    }
}
