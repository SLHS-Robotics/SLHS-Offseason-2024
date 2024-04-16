package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class hw {
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

    public HardwareMap hw_map;

    public hw(HardwareMap given) throws NullPointerException {
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
}
