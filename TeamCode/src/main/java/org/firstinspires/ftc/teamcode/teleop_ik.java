package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

@TeleOp
public class teleop_ik extends LinearOpMode {
    // 134.4 pulses per revolution from the motor's output shaft
    //   (for NeveRest Orbital 20)
    public final double ENCODER_PPR = 134.4; // = 2*pi radians
    public final double MOTOR_TO_JOINT_A_GEAR_RATIO = 0.5; // estimate
    public final double MOTOR_TO_JOINT_B_GEAR_RATIO = 0.5; // don't know the real values
    public final double A_FULL_REV_PULSE_COUNT = ENCODER_PPR / MOTOR_TO_JOINT_A_GEAR_RATIO;
    public final double B_FULL_REV_PULSE_COUNT = ENCODER_PPR / MOTOR_TO_JOINT_B_GEAR_RATIO;
    // upper arm
    public final double armlenA = 10.1;
    public DcMotor armmotorA;
    // forearm
    public final double armlenB = 12.2;
    public DcMotor armmotorB;

    public Servo clawservo;

    // current POSition (X, Y, Angle of A (upper arm), Angle of B (forearm))
    public double posX = armlenA + armlenB; // assume arms are initially parallel w/ horizontal
    public double posY = 0.0;
    // WISHed position (ditto)
    public double wishposX = armlenA + armlenB;
    public double wishposY = 0.0;
    //public double wishangA = 0.0;
    //public double wishangB = 0.0;

    public ElapsedTime time = new ElapsedTime();

    public final double wishspeed = 3.0; // 3 in. / 1 sec.

    public Gamepad previous_gamepad1 = new Gamepad();
    public Gamepad previous_gamepad2 = new Gamepad();

    public void runOpMode() {
        armmotorA = hardwareMap.get(DcMotor.class,"armmotor");
        armmotorB = hardwareMap.get(DcMotor.class,"armmotor2");

        // wristservo = hardwareMap.get(Servo.class,"wrist");      // TBD
        clawservo = hardwareMap.get(Servo.class,"claw");

        armmotorA.setDirection(DcMotor.Direction.FORWARD);
        armmotorB.setDirection(DcMotor.Direction.FORWARD);

        // wristservo.setDirection(Servo.Direction.FORWARD);
        clawservo.setDirection(Servo.Direction.FORWARD);

        armmotorA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armmotorB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armmotorA.setTargetPosition(0);
        armmotorB.setTargetPosition(0);

        armmotorA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armmotorB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

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
            // keep speed consistent regardless of computer speed
            long time_diff = Math.abs(time.time(TimeUnit.MILLISECONDS) - prev_time);
            double step = wishspeed * (double)(time_diff / 1000.0);

            wishposX += (step * -gamepad1.right_stick_x);
            wishposY += (step * -gamepad1.right_stick_y);
            // get some readings on these
            telemetry.addData("wishposX", wishposX);
            telemetry.addData("wishposY", wishposY);
            telemetry.update();

            // total distance of wished position (from base of the forearm)
            double wishdist = Math.sqrt((wishposX * wishposX) + (wishposY * wishposY));

            // angle of forearm relative to horizontal
            double angleA = Math.acos((armlenA * armlenA
                        + wishdist * wishdist
                        - armlenB * armlenB)
                        / (2.0 * armlenA * wishdist))
                          + Math.atan2(wishposY, wishposX);
            // angle of upper arm relative to forearm
            double angleB = Math.acos((armlenA * armlenA
                    + armlenB * armlenB
                    - wishdist * wishdist)
                    / (2.0 * armlenA * armlenB));
            double angleC = Math.PI - angleA - angleB; // corresponding angles --> vertical angles
            angleB -= angleC; // make angle of upper arm now relative to horizontal

            // make parameter for armmotorB negative as forearm goes down
            armmotorA.setTargetPosition( (int)((angleA / (2 * Math.PI)) * A_FULL_REV_PULSE_COUNT) );
            armmotorB.setTargetPosition( -(int)((angleB / (2 * Math.PI)) * A_FULL_REV_PULSE_COUNT) );

            prev_time = time.time(TimeUnit.MILLISECONDS);
        }
    }
}
