// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  /** Creates a new Drive. */
  private PWMSparkMax leftMotor;
  private PWMSparkMax rightMotor;
  private DifferentialDrive driver;
  private double speed, radius, turnMult;
  private int mult;
  
  public Drive() {
    leftMotor = new PWMSparkMax(3);
    rightMotor = new PWMSparkMax(1);
    driver = new DifferentialDrive(leftMotor, rightMotor);
    mult = 1;
    turnMult = 1;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public void setMult(int mult) {
    this.mult = mult;
  }

  public void setTurnMult(double turnMult) {
    this.turnMult = turnMult;
  }
  
  public int getMult() {
    return mult;
  }

  public double getTurnMult() {
    return turnMult;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    driver.curvatureDrive(speed*mult, radius*turnMult, false);
  }
}
