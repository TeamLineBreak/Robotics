package frc.robot;

import java.util.ArrayList;

public class AutoDriveUtil {
    
    private ArrayList<MotorArray> MotorArrayArray;

    public AutoDriveUtil(MotorArray m1, MotorArray ... mN) {
        MotorArrayArray.add(m1);
        for(MotorArray m : mN) {
            MotorArrayArray.add(m);
        }
    }

    public void accelerateTo() {

    }

    public void deccelerateTo() {

    }

}
