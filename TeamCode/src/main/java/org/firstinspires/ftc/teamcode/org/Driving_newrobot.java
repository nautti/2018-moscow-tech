package org.firstinspires.ftc.teamcode;/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Driving_newrobot", group="Linear Opmode")

public class Driving_newrobot extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftDrive_front;
    DcMotor rightDrive_front;
    DcMotor leftDrive_back;
    DcMotor rightDrive_back;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive_front  = hardwareMap.get(DcMotor.class, "leftf_drive");
        rightDrive_front = hardwareMap.get(DcMotor.class, "rightf_drive");
        leftDrive_back  = hardwareMap.get(DcMotor.class, "leftb_drive");
        rightDrive_back = hardwareMap.get(DcMotor.class, "rightb_drive");

        leftDrive_front.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive_front.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDrive_back.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive_back.setDirection(DcMotorSimple.Direction.REVERSE);



        String previous_state="down";
        boolean move_mode=false;
        long move_len=0;
        base base1 = new base(leftDrive_front, rightDrive_front, leftDrive_back, rightDrive_back);

        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            //extend_arm.setPower((gamepad1.dpad_up ? 1 : 0)-(gamepad1.dpad_down ? 1 : 0));
            //if pressed, change direction
            /*
            double power_y = -gamepad1.left_stick_y;
            double power_x = gamepad1.left_stick_x;

            double power_left=Range.clip(power_y+power_x, -1.0, 1.0);
            double power_right=Range.clip(power_y-power_x, -1.0, 1.0);

            leftDrive_front.setPower(power_left);
            rightDrive_front.setPower(power_right);
            leftDrive_back.setPower(power_left);
            rightDrive_back.setPower(power_right);
            */

            base1.mecaDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}