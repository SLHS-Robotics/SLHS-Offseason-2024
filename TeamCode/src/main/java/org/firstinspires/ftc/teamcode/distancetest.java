package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class distancetest extends LinearOpMode {
    public DistanceSensor distance;

    @Override
    public void runOpMode() {
        distance = hardwareMap.get(DistanceSensor.class,"distance");



        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("distance = ",distance.getDistance(DistanceUnit.CM));


        }
    }
}



