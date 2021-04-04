// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drive;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CourseOneGoup extends SequentialCommandGroup {
  /** Creates a new DriveGoup. */
  public CourseOneGoup(Drive driveSub) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutoDriveCommand(driveSub, .8).withTimeout(1.23), 
      new DualTurnCommand(driveSub, .8, -.6).withTimeout(1.65),
      new AutoDriveCommand(driveSub, .8).withTimeout(.25),
      new DualTurnCommand(driveSub, .8, -.8).withTimeout(.39),
      new AutoDriveCommand(driveSub, .8).withTimeout(.85),
      new DualTurnCommand(driveSub, .8, .6).withTimeout(1.7),
      new AutoDriveCommand(driveSub, .8).withTimeout(.1),
      new DualTurnCommand(driveSub, .8, .8).withTimeout(.3),
      new AutoDriveCommand(driveSub, .8).withTimeout(.5)
    );
  }
}
