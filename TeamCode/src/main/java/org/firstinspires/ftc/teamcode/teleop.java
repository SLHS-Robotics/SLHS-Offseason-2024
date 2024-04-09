package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp
public class teleop extends LinearOpMode {
    public DcMotor turntablemotor;
    public DcMotor armmotor;
    public Servo armservo;
    public Servo wristservo;
    public Servo clawservo;
  
    //Define Motors
    public DcMotor frontRightM;
    public DcMotor frontLeftM;
    public DcMotor backRightM;
    public DcMotor backLeftM;

    public AprilTagProcessor at;
    public VisionPortal cam;

    public class AprilTagFuncs {
        void Follow() {
            // TODO: implement logic
            telemetry.addData("Follow", "Not coded in yet!");
        }
        void Print() {
            telemetry.addData("Print", "AprilTag #1 detected!");
        }

        AprilTagFuncs() {}
    }
    public final AprilTagFuncs at_funcs = new AprilTagFuncs();

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

        // init april tag stuff
        at = new AprilTagProcessor.Builder().build();
        VisionPortal.Builder cam_builder = new VisionPortal.Builder();

        WebcamName cam_ptr = hardwareMap.tryGet(WebcamName.class, "cam"); // placeholder name
        if (cam_ptr == null) // if we can't get the camera
            cam_builder.setCamera(BuiltinCameraDirection.BACK); // presumably the phone's camera
        else
            cam_builder.setCamera(cam_ptr);
        cam_builder.addProcessor(at);
        cam_builder.build();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // turn off camera stream at will
            if (gamepad1.dpad_down) {
                cam.stopStreaming();
            } else if (gamepad1.dpad_up) {
                cam.resumeStreaming();
            }

            // april tag
            List<AprilTagDetection> detections = at.getDetections();
            for (AprilTagDetection i : detections) { // TODO: iterate over de-/increasing id
                switch (i.id) { // not the most efficient
                    // this really isn't what i wanted to do but java SUCKS :<
                    case 0: at_funcs.Follow();
                    case 1: at_funcs.Print();
                    default: break;
                }
            }
        }
    }
}