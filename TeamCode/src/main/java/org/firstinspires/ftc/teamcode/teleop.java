package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp
public class teleop extends LinearOpMode {
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

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Run
        }

    }
}
