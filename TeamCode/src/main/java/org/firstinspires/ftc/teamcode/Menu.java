package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.layout.Layout;

import java.util.Arrays;

/**
 * Manages the position of the robot
 */
public class Menu {
    public enum Position {
        RED_FRONT,
        RED_REAR,
        BLUE_FRONT,
        BLUE_REAR,
    }

    private Layout layout;
    private OpMode opMode;
    private Telemetry telemetry;

    private int menuPosition = 0;
    // intentionally left initially null
    private Position selected;
    private boolean pressed = false;

    /**
     * Sets the layout to the layout object
     *
     * @param layout    the layout object
     * @param opMode    the opMode object
     * @param telemetry the telemetry object
     */
    public Menu(Layout layout, OpMode opMode, Telemetry telemetry) {
        this.layout = layout;
    }

    /**
     * Update position
     */
    public void update() {
        if (layout.menuSelect()) {
            switch (menuPosition) {
                case 0:
                    selected = Position.RED_FRONT;
                    break;
                case 1:
                    selected = Position.RED_REAR;
                    break;
                case 2:
                    selected = Position.BLUE_FRONT;
                    break;
                case 3:
                    selected = Position.BLUE_REAR;
                    break;
            }
        }

        if (layout.menuUp() && !pressed) {
            menuPosition -= 1;
            if (menuPosition < 0) {
                menuPosition = 3;
            }
            pressed = true;
        } else if (layout.menuDown() && !pressed) {
            menuPosition += 1;
            if (menuPosition > 3) {
                menuPosition = 0;
            }
            pressed = true;
        } else {
            pressed = false;
        }
    }

    /**
     * Update telemetry with position
     */
    private void updateTelemetry() {
        if (selected == null) {
            telemetry.addLine("dpad up and down to navigate, a to select.");
            telemetry.addLine((menuPosition == 0 ? "->" : "") + "RED_FRONT");
            telemetry.addLine((menuPosition == 1 ? "->" : "") + "RED_REAR");
            telemetry.addLine((menuPosition == 2 ? "->" : "") + "BLUE_FRONT");
            telemetry.addLine((menuPosition == 3 ? "->" : "") + "BLUE_REAR");
        } else {
            switch (selected) {
                case RED_FRONT:
                    telemetry.addLine(" Selected RED_FRONT");
                    break;
                case RED_REAR:
                    telemetry.addLine(" Selected RED_REAR");
                    break;
                case BLUE_FRONT:
                    telemetry.addLine(" Selected BLUE_FRONT");
                    break;
                case BLUE_REAR:
                    telemetry.addLine(" Selected BLUE_REAR");
                    break;
            }
        }
        telemetry.update();
    }

    /**
     * return Position if Position selected, else return null
     */
    public Position selected() {
        return selected;
    }
}
