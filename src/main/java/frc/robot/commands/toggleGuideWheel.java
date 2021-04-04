// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shoot;

public class toggleGuideWheel extends CommandBase {
  /** Creates a new setGuideWheel. */

  private double targetSpeed;
  private Shoot shootSub;

  public toggleGuideWheel(Shoot shootSub, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shootSub = shootSub;
    if(shootSub.getGuideWheelSpeed() == 0) {
      targetSpeed = speed;
    }
    else {
      targetSpeed = 0;
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shootSub.setGuideWheelSpeed(targetSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shootSub.getGuideWheelSpeed() == targetSpeed;
  }
}
