package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class teleop extends LinearOpMode {
    public DcMotor turntablemotor;
    public DcMotor armmotor;
    public DcMotor armmotor2;
    // public Servo wristservo;
    public Servo clawservo;

    public double Speed = 1;
    public double RotateSp = 1;

    public double arm_mult = 0.2;

  
    //Define Motors
    public DcMotor frontRightM;
    public DcMotor frontLeftM;
    public DcMotor backRightM;
    public DcMotor backLeftM;

    public ElapsedTime time = new ElapsedTime();

    public Gamepad previous_gamepad1 = new Gamepad();
    public Gamepad previous_gamepad2 = new Gamepad();

    public void runOpMode() {
        // Pull and set to hardware motors
        frontRightM = hardwareMap.get(DcMotor.class,"frontRightM");
        frontLeftM = hardwareMap.get(DcMotor.class,"frontLeftM");
        backRightM = hardwareMap.get(DcMotor.class,"backRightM");
        backLeftM = hardwareMap.get(DcMotor.class,"backLeftM");

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
        armmotor2 = hardwareMap.get(DcMotor.class,"armmotor2");

        // wristservo = hardwareMap.get(Servo.class,"wrist");      // TBD
        clawservo = hardwareMap.get(Servo.class,"claw");

        turntablemotor.setDirection(DcMotor.Direction.FORWARD);
        armmotor.setDirection(DcMotor.Direction.FORWARD);
        armmotor2.setDirection(DcMotor.Direction.FORWARD);

        // wristservo.setDirection(Servo.Direction.FORWARD);
        clawservo.setDirection(Servo.Direction.FORWARD);

        turntablemotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armmotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        turntablemotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armmotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // wristservo.setPosition(0.0);
        clawservo.setPosition(0.0);

        // initialize so null values aren't used
        // ...assuming this copies the objects
        previous_gamepad1 = gamepad1;
        previous_gamepad2 = gamepad2;
      
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
            else if (gamepad1.left_stick_y < -0.1) {
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
            //turn when not moving
            else if (gamepad1.left_stick_x < -0.1) {
                frontRightM.setPower(RotateSp);
                frontLeftM.setPower(-RotateSp);
                backRightM.setPower(RotateSp);
                backLeftM.setPower(-RotateSp);
            }
            //turn when not moving
            else if (gamepad1.left_stick_x > 0.1) {
                frontRightM.setPower(-RotateSp);
                frontLeftM.setPower(RotateSp);
                backRightM.setPower(-RotateSp);
                backLeftM.setPower(RotateSp);
            }
            //if the joystick is not being touched then stop
            else if ((Math.abs(gamepad1.left_stick_x) < 0.1) && (Math.abs(gamepad1.left_stick_y) < 0.1)) {
                frontRightM.setPower(0);
                frontLeftM.setPower(0);
                backRightM.setPower(0);
                backLeftM.setPower(0);
            }

            // Basic arm control

            armmotor.setPower(gamepad1.right_stick_x * arm_mult);
            armmotor2.setPower(gamepad1.right_stick_y * arm_mult);
        }
    }
}
