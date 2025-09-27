package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.layout.Default;
import org.firstinspires.ftc.teamcode.layout.Layout;

// Autonomous Goals
// Move Robot in Auto to hang Preloaded Alliance specimen by moving forward
// Using april tags and Odometry to monitor movement and direction
// Move in front of 1 alliance sample and push into observation zone
// Repeat twice if time
// Park in Ascent Zone

@Autonomous(name = "Autonomous Mode", group = "Auto")
public class AutonomousMode extends LinearOpMode {
    HardwareToPosition hardware;
    Tel tel;
    Drive drive;
    // Hang hang;
    Score score;
    Layout layout;
    Pickup pickup;

    //  HardcodedAuto auto;
    public static double initialX= 14.282;
    public static double initialY= 62.842;
    public static double hangSpecimenX= 39;
    public static double hangSpecimenY= 62.842;

    public void runOpMode() throws InterruptedException {
        runInit();
        runActive();
    }

    private void runInit() {
        hardware = new HardwareToPosition(this);
        //doing nothing here, just go straight to runActive
        drive = new Drive(hardware);
        // hang = new Hang(hardware);
        score = new Score(hardware);
        pickup = new Pickup(hardware, telemetry);
        // tel = new Tel(telemetry).servos(hardware).gamepad(this).motors(hardware)
        //         .hang(hang).limitSwitches(hardware).counter().score(score);
        layout = new Default(this);

        waitForStart();
    }

    private void runActive() throws InterruptedException {
        boolean scoreLift = true;
           /* telemetry.addLine("Movement 1");
            telemetry.update();
            hardware.moveMotors(12, 0, 0);
            while(hardware.motors.isBusy());
            telemetry.addLine("Movement 2");
            telemetry.update();
            hardware.moveMotors(0, 0, 90);
            while(hardware.motors.isBusy()); */
        score.moveArm(true);
        score.moveArm(false);
    //    sleep(1000);
        hardware.moveMotors(-18,0,0,.4);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=2){
                break;
            };
        };
        score.moveArm(true);
        score.moveArm(false);
        sleep(800);
        hardware.moveMotors(4,0,0,.5);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=1){
                break;
            };
        };
        hardware.moveMotors(0,0,-90, .3);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=1.5
            ){
                break;
            };
        };

        score.moveArm(true);
        score.moveArm(false);
      //  sleep(1000);
      /*  resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=1){
                break;
            };
        };*/
        hardware.moveMotors(-27,0,0,.4);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=3){
                break;
            }
        };
        hardware.moveMotors(0,16,0,.3);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=3){
                break;
            };
        };
        hardware.moveMotors(-15,1,0,.4);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=1.5){
                break;
            };
        };

        score.moveArm(true);
        score.moveArm(false);
        sleep(700);

        hardware.moveMotors(10,0,0, .4);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=1){
                break;
            };
        };
        hardware.moveMotors(0,-7,0,.4);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=1){
                break;
            };
        };
        hardware.moveMotors(22,0,0, .4);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=3){
                break;
            };
        };
        hardware.moveMotors(0,0,90, .2);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=1){
                break;
            };
        };

        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=1){
                break;
            };
        };

        hardware.moveMotors(-15,0,0 , .4);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=2){
                break;
            };
        };
        score.moveArm(true);
        score.moveArm(false);
        sleep(800);
        hardware.moveMotors(4,0,0 , .4);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=1){
                break;
            };
        };
        score.moveArm(true);
        score.moveArm(false);
        hardware.moveMotors(10,33,0 , .7);
        resetRuntime();
        while(hardware.motors.isBusy()) {
            if (getRuntime()>=7)
            {
                break;
            };
        };
        sleep(5000);

//        PathChain initialPath = follower.pathBuilder().addBezierLine(initialPoint, hangPoint).build();
//        follower.followPath(initialPath);
//        while (this.opModeIsActive()) {

//            follower.update();
//
//
//            if (scoreLift){
//                score.moveArm(layout.scoreArmClaw());
//                scoreLift = false;
//            }

        //auto.run();
      /*      score.moveArm(true);
            score.moveArm(false);
            sleep(1000);
            drive.moveRobot(-.5,0,0);
            sleep(3000);
            score.moveArm(true);
            score.moveArm(false);
            sleep(500);

//            sleep(300);
        drive.moveRobot(.3,0,0);
        sleep(300); */
        // here on out is experimental
        /* drive.moveRobot(0,0,-.2);
        sleep(500);
        drive.moveRobot(0,.6,0); */
       // drive.moveRobot(0.1,1,0);
  /*      sleep(1000);

        drive.moveRobot(.5,0,0);
        sleep(3000);
        drive.moveRobot(.5,0,0);
        sleep(800);
        drive.moveRobot(0,.3,0);
        sleep(500);
        drive.moveRobot(0,.3,0);

        sleep(500);

        drive.moveRobot(0,0,.2);
        sleep(500);

        score.moveArm(true);
        score.moveArm(false);
        sleep(1000);

        drive.moveRobot(0,-.3,0);
        sleep(1000);

        drive.moveRobot(-.5,0,0);
        sleep(1000);
        sleep(500);

//            sleep(300);
        drive.moveRobot(.3,0,0);
        sleep(1000);
        drive.moveRobot(0.1,1,0);
        sleep(1000);
        drive.moveRobot(0,0,0);
//            sleep(5000);
*/
            tel.update();



//        }

    }


}
