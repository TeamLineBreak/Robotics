package frc.robot;

import java.util.ArrayList;

public class AutoDriveUtil {
    
    private ArrayList<MotorGroup> MotorArrayArray;

    public AutoDriveUtil(MotorGroup m1, MotorGroup ... mN) {
        MotorArrayArray.add(m1);
        for(MotorGroup m : mN) {
            MotorArrayArray.add(m);
        }
    }

    public void accelerateTo() {

    }

    public void deccelerateTo() {

    }

}
