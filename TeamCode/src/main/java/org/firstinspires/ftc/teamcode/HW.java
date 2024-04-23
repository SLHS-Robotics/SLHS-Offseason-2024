package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HW {
    // Function motors and servos
    public DcMotor turntablemotor;
    public DcMotor armmotor;
    public Servo armservo;
    public Servo wristservo;
    public Servo clawservo;

    // Movement motors
    public DcMotor frontRightM;
    public DcMotor frontLeftM;
    public DcMotor backRightM;
    public DcMotor backLeftM;

    public ElapsedTime time = new ElapsedTime();

    public Gamepad previous_gamepad1 = new Gamepad();
    public Gamepad previous_gamepad2 = new Gamepad();

    public HardwareMap hw_map;

    public HW(HardwareMap given) throws NullPointerException {
        hw_map = given;
        if (hw_map == null) {
            NullPointerException e = new NullPointerException("Null hardware map");
            throw e;
        }
        init();
    }

    public void init() {
        // Pull and set to hardware motors
        frontRightM = hw_map.get(DcMotor.class,"frontRightM");
        frontLeftM = hw_map.get(DcMotor.class,"frontLeftM");
        backRightM = hw_map.get(DcMotor.class,"backRightM");
        backLeftM = hw_map.get(DcMotor.class,"backLeftM");

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


        turntablemotor = hw_map.get(DcMotor.class,"turntable");
        armmotor = hw_map.get(DcMotor.class,"armmotor");

        armservo = hw_map.get(Servo.class,"armservo");
        wristservo = hw_map.get(Servo.class,"wrist");
        clawservo = hw_map.get(Servo.class,"claw");

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

    public void init_prev_gamepad(Gamepad g1, Gamepad g2) {
        previous_gamepad1 = g1;
        previous_gamepad2 = g2;
    }
}
