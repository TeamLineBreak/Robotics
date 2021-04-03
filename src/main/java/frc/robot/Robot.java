package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is the main class of the robot code, most of the code should be written
 * or called here.
 */
public class Robot extends TimedRobot {
  // private final PWMSparkMax rightMotor1 = new PWMSparkMax(1);
  // private final PWMSparkMax rightMotor2 = new PWMSparkMax(2);
  // private final PWMSparkMax leftMotor1 = new PWMSparkMax(3);
  // private final PWMSparkMax leftMotor2 = new PWMSparkMax(4);
  //private final MotorGroup leftMotorArray = new MotorGroup(new PWMSparkMax(3), new PWMSparkMax(0));
  //private final MotorGroup rightMotorArray = new MotorGroup(new PWMSparkMax(1), new PWMSparkMax(2));
  private final DifferentialDrive drive = new DifferentialDrive(new PWMSparkMax(3), new PWMSparkMax(1));
  private final PWMVictorSPX guideMotor = new PWMVictorSPX(5);
  private final PWMVictorSPX FlyWheel = new PWMVictorSPX(6);
  private int autoPhase, mult;
  private Joystick joy, joy2;
  private double turnSpeed, testSpeed, spinSpeed;
  private boolean acc, spin, turnQ;

  @Override
  public void robotInit() {
    // initalize the joystick
    joy = new Joystick(0);
    joy2 = new Joystick(0);
    // set the robot heading to backwards, which is the battery side
    mult = -1;
    // reduce the turn speed by 43 percent to increace control
    turnSpeed = .57;
    spinSpeed = 1;
    turnQ = false;
  }

  @Override
  public void teleopInit() {
    // sets z axis to the rt for the tank drve.
    joy.setZChannel(3);
    joy2.setXChannel(4);
    joy2.setYChannel(5);
    spin = false;
  }

  @Override
  public void teleopPeriodic() {
    // if r1 is pressed then switch the robot forward direction
    if (joy.getRawButtonPressed(6)) {
      mult *= -1;
    }
    // if l1 is pressed change increas the turn speed to make sharper turns while
    // moving and rereduce the turnspeed on release.
    if (joy.getRawButtonPressed(5)) {
      turnSpeed = .85;
      //turnQ = true;
    }
    if (joy.getRawButtonReleased(5)) {
      turnSpeed = .57;
      //turnQ = false;
    }
    if (joy.getRawButtonPressed(1)) {
      spin = !spin;
    }
    if (joy.getRawButtonPressed(2)) {
      spinSpeed = 1;
    }
    if (joy.getRawButtonPressed(3)) {
      spinSpeed = .8;
    }
    if (joy.getRawButtonPressed(4)) {
      spinSpeed = .6;
    }
    if (spin) {
      guideMotor.set(-.8);
      FlyWheel.set(-spinSpeed);
    } else {
      guideMotor.set(0);
      FlyWheel.set(0);
    }
    if(joy.getX() == 0 && joy.getRawAxis(2) == 0){
      turnQ = true;
    }
    // move the robot
    drive.curvatureDrive(((joy.getZ() - joy.getRawAxis(2))*mult), Math.pow(joy.getX(), 2) * turnSpeed * (Math.signum(joy.getX()) * -1), turnQ);
    //tankDrive(joy, mult, turnSpeed);
  }

  @Override
  public void autonomousInit() {
    autoPhase = 2;
    testSpeed = .01;
    acc = true;
  }

  @Override
  public void autonomousPeriodic() {
    switch (autoPhase) {
      case 0:
        if (testSpeed == -.1)
          break;
        if (acc) {
          autoDrive(testSpeed, testSpeed);
          testSpeed += .05;
        } else {
          autoDrive(testSpeed, testSpeed);
          testSpeed -= .1;
        }
        if (testSpeed == .3) {
          acc = !acc;
        }
        break;
      case 1:
        autoDrive(.2, .2);
        break;
      case 2:
        autoDrive(.3, 0);
        // guideMotor.set(-.6);
        // FlyWheel.set(-1);
    }

  }

  @Override
  public void disabledInit() {
    autoDrive(0, 0);
  }

  /**
   * Method to drive the robot, uses one controler axis as the turning input and
   * another to control the robot speed. The control z axis must be set to the
   * axis needed to control the speed, and the controler x axis controls the
   * rotation.
   * 
   * @param mult      is the multiplier for the entire robot speed, mainly used to
   *                  change the robots forward direction, can also be used for
   *                  precice movment.
   * @param turnSpeed the multiplier for the robots turn speed, cannot excied 1,
   *                  used to slow the turning of the robot.
   * @param joy       the controler to be used as the control to control the
   *                  robot.
   */
  public void tankDrive(Joystick joy, int mult, double turnSpeed) {
    // driving code for going forward.
    // axis 2 is the axis for lt. So when lt is pressed the robot will go in
    // reverse.
    // driving algorithim is handled as 
    //((Speed axis - reverse axis) +- turnaxis*turnSpeed) * multiplier
    drive(((joy.getZ() - joy.getRawAxis(2)) - Math.pow(joy.getX(), 2) * turnSpeed * (Math.signum(joy.getX()))) * mult,
        ((joy.getZ() - joy.getRawAxis(2)) + Math.pow(joy.getX(), 2) * turnSpeed * (Math.signum(joy.getX()))) * mult);
  }

  /**
   * used to set the motor speed, no acc built in
   * 
   * @param leftSpeed  speed of left motor, is inverted in function
   * @param rightSpeed spped of right motor, is not inverted
   */
  public void drive(double leftSpeed, double rightSpeed) {
    // leftMotorArray.setSpeed(-leftSpeed);
    // rightMotorArray.setSpeed(rightSpeed);
    // System.out.println("Left Speed: " + leftSpeed + ", Right Speed: " +
    // rightSpeed);
  }

  /**
   * use this over dirve for autonomus drive, has extra steps
   * 
   * @param leftSpeed  speed of left motor, is inverted in function
   * @param rightSpeed spped of right motor, is not inverted
   */
  public void autoDrive(double leftSpeed, double rightSpeed) {
    // leftMotorArray.setSpeed(-leftSpeed);
    // rightMotorArray.setSpeed(rightSpeed);
  }
}
