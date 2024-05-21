package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

// testing for teleop_ik
@Autonomous
public class encoder_test extends LinearOpMode {
    // upper arm
    public DcMotor armmotorA;
    // forearm
    public DcMotor armmotorB;

    @Override
    public void runOpMode() {
        armmotorA = hardwareMap.get(DcMotor.class,"armmotor");
        armmotorB = hardwareMap.get(DcMotor.class,"armmotor2");

        armmotorA.setDirection(DcMotor.Direction.FORWARD);
        armmotorB.setDirection(DcMotor.Direction.FORWARD);

        armmotorA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armmotorB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armmotorA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armmotorB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("encoderA", armmotorA.getCurrentPosition());
            telemetry.addData("encoderB", armmotorB.getCurrentPosition());

            telemetry.update();
        }
    }
}
