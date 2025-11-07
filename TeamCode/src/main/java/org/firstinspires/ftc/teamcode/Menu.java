package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.layout.Layout;

import java.util.Arrays;

public class Menu {
    public enum Option {
      RED_FRONT,
      RED_REAR,
      BLUE_FRONT,
      BLUE_REAR,
    }

    private Layout layout;
    private OpMode opMode;

    private int menuPosition = 0;
    // intentionally left initially null
    private Option selected;
    private boolean pressed = false;

    public Menu(Layout layout, OpMode opMode) {
        this.layout = layout;
    }

    public void update() {
        if (layout.menuSelect()) {
            selected = switch menuPosition {
              0:
              return Option.RED_FRONT;
              1:
              return Option.RED_REAR;
              2:
              return Option.BLUE_FRONT;
              3:
              return Option.BLUE_REAR;
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

    private void updateTelemetry() {
        if f
    }

    // return Option if Option selected, else return null
    public Option optionSelected() {
        return selected;
    }
}

