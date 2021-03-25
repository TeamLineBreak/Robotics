package frc.robot;

import java.util.ArrayList;
import edu.wpi.first.wpilibj.PWMSparkMax;

/**
 * A class for creating and managing an aray of motors
 */
public class MotorArray {

    private ArrayList<PWMSparkMax> motors;

    /**
     * constructer, initiat the motor array
     * 
     * @param m1 first motor *rquired*
     * @param mN other motors, optional
     */
    public MotorArray(PWMSparkMax m1, PWMSparkMax... mN) {
        motors.add(m1);
        for (PWMSparkMax m : mN) {
            motors.add(m);
        }
    }

    /**
     * sets the speed of every motor in the array
     * 
     * @param speed target speed for all motors
     */
    public void setSpeed(double speed) {
        for (PWMSparkMax m : motors) {
            m.set(speed);
        }
    }

}
