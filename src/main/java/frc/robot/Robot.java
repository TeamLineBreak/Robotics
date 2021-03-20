// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final PWMSparkMax rightMotor1 = new PWMSparkMax(1);
  private final PWMSparkMax rightMotor2 = new PWMSparkMax(2);
  private final PWMSparkMax leftMotor1 = new PWMSparkMax(3);
  private final PWMSparkMax leftMotor2 = new PWMSparkMax(4);
  private final Joystick joy = new Joystick(0);
  //private final DifferentialDrive robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  //private final Joystick m_stick = new Joystick(0);

  @Override
  public void robotInit() {
    //joy.setXChannel(5);
  }
  
  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    //m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
    leftMotor1.set(joy.getY() + joy.getX());
    leftMotor2.set(joy.getY() + joy.getX());
    rightMotor1.set(joy.getY()  - joy.getX());
    rightMotor2.set(joy.getY()  - joy.getX());
  }
}
