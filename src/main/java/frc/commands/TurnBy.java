// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.util.Angle;

public class TurnBy extends SequentialCommandGroup {
    Timer timer;
    double timeout;
    Angle angle, targetAngle;
    PIDController pid;

    public TurnBy(double timeout, Angle angle) {
        // Add your commands in the addCommands() call, e.g.
        addCommands(new TurnToAngle(timeout, angle, true));
    }
    @Override
    public void initialize() {
        super.initialize();

    }


}
