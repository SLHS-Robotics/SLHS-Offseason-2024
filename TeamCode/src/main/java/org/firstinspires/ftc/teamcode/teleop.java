package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class teleop extends LinearOpMode {
    public DcMotor turntablemotor;
    public DcMotor armmotor;
    public Servo armservo;
    public Servo wristservo;
    public Servo clawservo;

    public double Speed = 1;
    public double RotateSp = 1;

  
    //Define Motors
    public DcMotor frontRightM;
    public DcMotor frontLeftM;
    public DcMotor backRightM;
    public DcMotor backLeftM;

    public void runOpMode() {
        // Pull and set to hardware motors
        frontRightM = hardwareMap.get(DcMotor.class,"frontRightM");
        frontLeftM = hardwareMap.get(DcMotor.class,"frontLeftM");
        backRightM = hardwareMap.get(DcMotor.class,"backRightM");
        backLeftM = hardwareMap.get(DcMotor.class,"backLeftM");

        waitForStart();
        // Set directions
        frontRightM.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftM.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightM.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftM.setDirection(DcMotorSimple.Direction.FORWARD);

        // Reset encoders
        frontRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set encoder mode
        frontRightM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        turntablemotor = hardwareMap.get(DcMotor.class,"turntable");
        armmotor= hardwareMap.get(DcMotor.class,"armmotor");

        armservo= hardwareMap.get(Servo.class,"armservo");
        wristservo = hardwareMap.get(Servo.class,"wrist");
        clawservo = hardwareMap.get(Servo.class,"claw");

        turntablemotor.setDirection(DcMotor.Direction.FORWARD);
        armmotor.setDirection(DcMotor.Direction.FORWARD);

        armservo.setDirection(Servo.Direction.FORWARD);
        wristservo.setDirection(Servo.Direction.FORWARD);
        clawservo.setDirection(Servo.Direction.FORWARD);

        turntablemotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        turntablemotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armservo.setPosition(0.0);
        wristservo.setPosition(0.0);
        clawservo.setPosition(0.0);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Run

            //movement controls
            // forward controls (check if user is pushing forward)
            if(gamepad1.left_stick_y >0) {
                //check if person is trying to turn left at the same time
                //prioritize turning left
                if (gamepad1.left_stick_x < -0.1) {
                    frontRightM.setPower(RotateSp);
                    frontLeftM.setPower(-RotateSp);
                    backRightM.setPower(RotateSp);
                    backLeftM.setPower(-RotateSp);
                }
                //next, check for the right
                else if (gamepad1.left_stick_x > 0.1) {
                    frontRightM.setPower(-RotateSp);
                    frontLeftM.setPower(RotateSp);
                    backRightM.setPower(-RotateSp);
                    backLeftM.setPower(RotateSp);
                }
                else {
                    //finally, if the person is only trying to go forward, go forward
                    frontRightM.setPower(Speed);
                    frontLeftM.setPower(Speed);
                    backRightM.setPower(Speed);
                    backLeftM.setPower(Speed);
                }
            }
            
            // backwards controls (if he isn't pushing forwards, is he pulling backwards?
            else if (gamepad1.left_stick_y < 0) {
                if (gamepad1.left_stick_x < -0.1) {
                    frontRightM.setPower(RotateSp);
                    frontLeftM.setPower(-RotateSp);
                    backRightM.setPower(RotateSp);
                    backLeftM.setPower(-RotateSp);
                }
                //next, check for the right
                else if (gamepad1.left_stick_x > 0.1) {
                    frontRightM.setPower(-RotateSp);
                    frontLeftM.setPower(RotateSp);
                    backRightM.setPower(-RotateSp);
                    backLeftM.setPower(RotateSp);
                }
                else {
                    frontRightM.setPower(-Speed);
                    frontLeftM.setPower(-Speed);
                    backRightM.setPower(-Speed);
                    backLeftM.setPower(-Speed);
                }
            }

            else if ((Math.abs(gamepad1.left_stick_x) < 0.1) && (Math.abs(gamepad1.left_stick_y) < 0.1)) {
                frontRightM.setPower(0);
                frontLeftM.setPower(0);
                backRightM.setPower(0);
                backLeftM.setPower(0);
            }
        }
    }
}
