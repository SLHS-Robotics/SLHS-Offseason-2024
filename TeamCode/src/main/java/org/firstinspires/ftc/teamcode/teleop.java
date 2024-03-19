package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class teleop extends LinearOpMode {
    public DcMotor turntablemotor;
    public DcMotor armmotor;
    public Servo armservo;
    public Servo wristservo;
    public Servo clawservo;

    public void runOpMode() {

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

    }
}
