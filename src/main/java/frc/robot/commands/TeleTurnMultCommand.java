// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class TeleTurnMultCommand extends CommandBase {
  /** Creates a new TeleTurnMultCommand. */

  private Drive driveSub;
  private double turnMult;

  public TeleTurnMultCommand(Drive driveSub, double turnMult) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveSub = driveSub;
    this.turnMult = turnMult;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSub.setTurnMult(turnMult);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return driveSub.getTurnMult() == turnMult;
  }
}
