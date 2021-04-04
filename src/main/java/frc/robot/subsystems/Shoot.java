// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shoot extends SubsystemBase {
  /** Creates a new Shoot. */

  private PWMVictorSPX guideWheel;
  private PWMVictorSPX flyWheel;

  public Shoot() {
    guideWheel = new PWMVictorSPX(5);
    flyWheel = new PWMVictorSPX(6);
  }

  public void setGuideWheelSpeed(double speed) {
    guideWheel.set(-speed);
  }
  
  public void setFlyWheelSpeed(double speed) {
    flyWheel.set(-speed);
  }

  public double getGuideWheelSpeed() {
    return guideWheel.getSpeed();
  }
  
  public double getFlyWheelSpeed() {
    return flyWheel.getSpeed();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
