package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

@TeleOp
public class teleop extends LinearOpMode {
    public DcMotor turntablemotor;
    public DcMotor armmotor;
    public DcMotor armmotor2;
    // public Servo wristservo;
    public Servo clawservo;

    public double Speed = 0.3;
    public double RotateSp = 0.3;

    public final double arm_speed = 40.0;

  
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
        armmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION); // gonna want to use the encoder
        armmotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // gonna need these variables to more easily update the arm motors' target positions
        int armmotor_pos = 0;
        int armmotor2_pos = 0;
        armmotor.setTargetPosition(armmotor_pos);
        armmotor2.setTargetPosition(armmotor2_pos);
        // needs power to move to target positions
        // won't move initially because encoders already reset to zero
        armmotor.setPower(0.5);
        armmotor2.setPower(0.5);

        // wristservo.setPosition(0.0);
        clawservo.setPosition(0.0);

        // initialize so null values aren't used
        // ...assuming this copies the objects
        previous_gamepad1 = gamepad1;
        previous_gamepad2 = gamepad2;
      
        waitForStart();

        long prev_time = time.time(TimeUnit.MILLISECONDS);
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Run


            frontRightM.setPower(gamepad1.left_stick_y * Speed + gamepad1.right_stick_x * RotateSp);
            frontLeftM.setPower(gamepad1.left_stick_y * Speed - gamepad1.right_stick_x * RotateSp );
            backRightM.setPower(gamepad1.left_stick_y * Speed + gamepad1.right_stick_x * RotateSp);
            backLeftM.setPower(gamepad1.left_stick_y * Speed - gamepad1.right_stick_x * RotateSp );

            // Basic arm control
            // keep speed constant w/ respect to time
            long time_diff = Math.abs(time.time(TimeUnit.MILLISECONDS) - prev_time);
            double step = arm_speed * (double)time_diff;

            // slow down arm movement while right bumper is being held
            if (gamepad1.right_bumper)
                step *= 0.5;

            // upper arm movement
            if (gamepad1.dpad_up)
                armmotor_pos += (int)step;
            else if (gamepad1.dpad_down)
                armmotor_pos -= (int)step;

            // forearm movement
            if (gamepad1.dpad_left)
                armmotor2_pos += (int)step;
            else if (gamepad1.dpad_right)
                armmotor2_pos -= (int)step;

            // set target positions after determining dpad input
            armmotor.setTargetPosition(armmotor_pos);
            armmotor2.setTargetPosition(armmotor2_pos);

            // remember to update prev_time
            prev_time = time.time(TimeUnit.MILLISECONDS);
        }
    }
}
