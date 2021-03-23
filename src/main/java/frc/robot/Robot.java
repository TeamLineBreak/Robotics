package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This is the main class of the robot code, most of the code should be written or called here.
 */
public class Robot extends TimedRobot {
  private final PWMSparkMax rightMotor1 = new PWMSparkMax(1);
  private final PWMSparkMax rightMotor2 = new PWMSparkMax(2);
  private final PWMSparkMax leftMotor1 = new PWMSparkMax(3);
  private final PWMSparkMax leftMotor2 = new PWMSparkMax(4);
  private int autoPhase;
  private Joystick joy;
  private int mult;
  private double turnSpeed;
  private double testSpeed;
  private boolean acc;

  @Override
  public void robotInit() {
    //initalize the joystick
    joy = new Joystick(0);
    //set the robot heading to backwards, which is the battery side
    mult = -1;
    //reduce the turn speed by 43 percent to increace control
    turnSpeed = .57;
  }

  @Override
  public void teleopInit() {
    //sets z axis to the rt for the tank drve.
    joy.setZChannel(3);
  }
  
  @Override
  public void teleopPeriodic() {
    //if r1 is pressed then switch the robot forward direction
    if(joy.getRawButtonPressed(6)) {
      mult *= -1;
    }
    //if l1 is pressed change increas the turn speed to make sharper turns while moving and rereduce the turnspeed on release.
    if(joy.getRawButtonPressed(5)) {
      turnSpeed = .8;
    }
    if(joy.getRawButtonReleased(5)) {
      turnSpeed = .57;
    }
    //move the robot
    tankDrive(joy, mult, turnSpeed);
  }

  @Override
  public void autonomousInit() {
    autoPhase = 0;
    testSpeed = .01;
    acc = true;
  }

  @Override
  public void autonomousPeriodic() {
    switch (autoPhase){
      case 0 :
        if(testSpeed == -.1) break;
        if(acc) {
          autoDrive(testSpeed, testSpeed);
          testSpeed += .05;
        }
        else {
          autoDrive(testSpeed, testSpeed);
          testSpeed -= .1;
        }
        if(testSpeed == .3){
          acc = !acc;
        }
        break;
    
    }
    
  }

  @Override
  public void disabledInit() {
    autoDrive(0, 0);
  }

  /**
   * Method to drive the robot, uses one controler axis as the turning input and another to control the robot speed.
   * The control z axis must be set to the axis needed to control the speed, and the controler x axis controls the rotation.
   * @param mult is the multiplier for the entire robot speed, mainly used to change the robots forward direction, can also be used for precice movment.
   * @param turnSpeed the multiplier for the robots turn speed, cannot excied 1, used to slow the turning of the robot.
   * @param joy the controler to be used as the control to control the robot. 
   */
  public void tankDrive(Joystick joy, int mult, double turnSpeed) {
    //driving code for going forward.
    //axis 2 is the axis for lt. So when lt is pressed the robot will go in reverse.
    // driving algorithim is handled as ((Speed axis - reverse axis) +- turn axis*turnSpeed) * multiplier
    if(joy.getRawAxis(2) == 0 ^ mult == 1) {
      leftMotor1.set(((joy.getZ() - joy.getRawAxis(2)) + joy.getX()*turnSpeed) * mult);
      leftMotor2.set(((joy.getZ() - joy.getRawAxis(2)) + joy.getX()*turnSpeed) * mult);
      rightMotor1.set(((joy.getZ() - joy.getRawAxis(2))  - joy.getX()*turnSpeed) * -1 * mult);
      rightMotor2.set(((joy.getZ() - joy.getRawAxis(2))  - joy.getX()*turnSpeed) * -1 * mult);
    }
    //driving code for going backwards
    else {
      leftMotor1.set(((joy.getZ() - joy.getRawAxis(2)) - joy.getX()*turnSpeed) * mult);
      leftMotor2.set(((joy.getZ() - joy.getRawAxis(2)) - joy.getX()*turnSpeed) * mult);
      rightMotor1.set(((joy.getZ() - joy.getRawAxis(2))  + joy.getX()*turnSpeed) * -1 * mult);
      rightMotor2.set(((joy.getZ() - joy.getRawAxis(2))  + joy.getX()*turnSpeed) * -1 * mult);
    }
  }

  public void autoDrive(double leftSpeed, double rightSpeed) {
    leftMotor1.set(-leftSpeed);
    leftMotor2.set(-leftSpeed);
    rightMotor1.set(rightSpeed);
    rightMotor2.set(rightSpeed);
  } 
}
