package org.usfirst.frc1571.team1571bot.subsystems;

import org.usfirst.frc1571.team1571bot.RobotMap;
import org.usfirst.frc1571.team1571bot.commands.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gearbox extends Subsystem {


	private final SolenoidBase solenoidGearArray[] = RobotMap.gearboxSolenoidGears;
    private int currentGear;

    public void initDefaultCommand() {
    	setDefaultCommand(new SetGear(1));
    }
    
    public void setGear(int gearNumber, boolean extended) {
    	
    	if(solenoidGearArray[gearNumber - 1] instanceof Solenoid) { //When I coded this, I didn't know if we were going to be using solenoids or double solenoids, so I check that here and run the appropriate code
    		Solenoid solenoid = (Solenoid)solenoidGearArray[gearNumber - 1];
    		solenoid.set(extended);
    		
    	} else if(solenoidGearArray[gearNumber - 1] instanceof DoubleSolenoid) { //The double solenoid code is slightly different from the solenoid code
    		DoubleSolenoid doubleSolenoid = (DoubleSolenoid)solenoidGearArray[gearNumber - 1];
    		if(extended) {
    			doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    		} else {
    			doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    		}
    	}
    	
    	if(extended) {
    		currentGear = gearNumber; //Save the current gear for later so if the driver tries to switch to the gear already selected time isn't lost
    	} else if(gearNumber == this.getCurrentGear()) {
    		System.out.println("Notice - Retracting currently engaged gear");
    		currentGear = -1;
    	}
    }
    
    public int getCurrentGear() {
    	return currentGear;
    }
}

