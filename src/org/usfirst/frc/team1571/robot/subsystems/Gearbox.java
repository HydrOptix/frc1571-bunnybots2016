package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;
import org.usfirst.frc.team1571.robot.commands.*;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gearbox extends Subsystem {


	private final Solenoid gear1 = RobotMap.gearboxSolenoid_Gear1;
	private final Solenoid gear2 = RobotMap.gearboxSolenoid_Gear2;
	
	private int currentGear;

    public void initDefaultCommand() {
    	setDefaultCommand(new SetGear1());
    }
    
    public void setGear(int gearNumber, boolean extended) {
    	
    	if(gearNumber == 1) {
    		gear1.set(extended);
    	} else if(gearNumber == 2) {
    		gear2.set(extended);
    	}
    	
		if(!extended) currentGear = 1;
		else currentGear = 2;
		
		SmartDashboard.putNumber("Current Gear", currentGear);;
    	
    }
    
    public int getCurrentGear () {
    	return currentGear;
    }
}

