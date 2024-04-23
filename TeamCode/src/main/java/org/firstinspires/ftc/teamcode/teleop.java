package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class teleop extends LinearOpMode {
    public HW hw = new HW(this.hardwareMap);

    public double Speed = 1;
    public double RotateSp = 1;

    public void runOpMode() {
        hw.init_prev_gamepad(gamepad1, gamepad2);

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Run

            //movement controls
            // forward controls (check if user is pushing forward)
            if(gamepad1.left_stick_y > 0.1) {
                //check if person is trying to turn left at the same time
                //prioritize turning left
                if (gamepad1.left_stick_x < -0.1) {
                    hw.frontRightM.setPower(RotateSp);
                    hw.frontLeftM.setPower(-RotateSp);
                    hw.backRightM.setPower(RotateSp);
                    hw.backLeftM.setPower(-RotateSp);
                }
                //next, check for the right
                else if (gamepad1.left_stick_x > 0.1) {
                    hw.frontRightM.setPower(-RotateSp);
                    hw.frontLeftM.setPower(RotateSp);
                    hw.backRightM.setPower(-RotateSp);
                    hw.backLeftM.setPower(RotateSp);
                }
                else {
                    //finally, if the person is only trying to go forward, go forward
                    hw.frontRightM.setPower(Speed);
                    hw.frontLeftM.setPower(Speed);
                    hw.backRightM.setPower(Speed);
                    hw.backLeftM.setPower(Speed);
                }
            }

            // backwards controls (if he isn't pushing forwards, is he pulling backwards?
            else if (gamepad1.left_stick_y < -0.1) {
                if (gamepad1.left_stick_x < -0.1) {
                    hw.frontRightM.setPower(RotateSp);
                    hw.frontLeftM.setPower(-RotateSp);
                    hw.backRightM.setPower(RotateSp);
                    hw.backLeftM.setPower(-RotateSp);
                }
                //next, check for the right
                else if (gamepad1.left_stick_x > 0.1) {
                    hw.frontRightM.setPower(-RotateSp);
                    hw.frontLeftM.setPower(RotateSp);
                    hw.backRightM.setPower(-RotateSp);
                    hw.backLeftM.setPower(RotateSp);
                }
                else {
                    hw.frontRightM.setPower(-Speed);
                    hw.frontLeftM.setPower(-Speed);
                    hw.backRightM.setPower(-Speed);
                    hw.backLeftM.setPower(-Speed);
                }
            }
            //turn when not moving
            else if (gamepad1.left_stick_x < -0.1) {
                hw.frontRightM.setPower(RotateSp);
                hw.frontLeftM.setPower(-RotateSp);
                hw.backRightM.setPower(RotateSp);
                hw.backLeftM.setPower(-RotateSp);
            }
            //turn when not moving
            else if (gamepad1.left_stick_x > 0.1) {
                hw.frontRightM.setPower(-RotateSp);
                hw.frontLeftM.setPower(RotateSp);
                hw.backRightM.setPower(-RotateSp);
                hw.backLeftM.setPower(RotateSp);
            }
            //if the joystick is not being touched then stop
            else if ((Math.abs(gamepad1.left_stick_x) < 0.1) && (Math.abs(gamepad1.left_stick_y) < 0.1)) {
                hw.frontRightM.setPower(0);
                hw.frontLeftM.setPower(0);
                hw.backRightM.setPower(0);
                hw.backLeftM.setPower(0);
            }
        }
    }
}
