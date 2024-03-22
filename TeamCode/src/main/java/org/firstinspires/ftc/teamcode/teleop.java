package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class teleop extends LinearOpMode {
    // Arm Motors and Servos
    public DcMotor turntableM;
    public DcMotor armM;
    public Servo armS;
    public Servo wristS;
    public Servo clawS;
  
    // Wheel (Movement) Motors
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

        turntableM = hardwareMap.get(DcMotor.class,"turntable");
        armM = hardwareMap.get(DcMotor.class,"armmotor");
        armS = hardwareMap.get(Servo.class,"armservo");
        wristS = hardwareMap.get(Servo.class,"wrist");
        clawS = hardwareMap.get(Servo.class,"claw");

        turntableM.setDirection(DcMotor.Direction.FORWARD);
        armM.setDirection(DcMotor.Direction.FORWARD);
        armS.setDirection(Servo.Direction.FORWARD);
        wristS.setDirection(Servo.Direction.FORWARD);
        clawS.setDirection(Servo.Direction.FORWARD);

        turntableM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turntableM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armS.setPosition(0.0);
        wristS.setPosition(0.0);
        clawS.setPosition(0.0);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Run
        }
    }
}
