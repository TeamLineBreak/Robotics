// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DualTurnCommand extends CommandBase {
  /** Creates a new TurnCommand. */

  private Drive driveSub;
  private double speed, radius;

  public DualTurnCommand(Drive driveSub, double speed, double radius) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveSub = driveSub;
    this.speed = speed;
    this.radius = radius;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSub.setSpeed(speed);
    driveSub.setRadius(radius);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSub.setSpeed(0);
    driveSub.setRadius(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
