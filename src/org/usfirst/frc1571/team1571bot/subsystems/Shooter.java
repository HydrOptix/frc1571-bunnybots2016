package org.usfirst.frc1571.team1571bot.subsystems;

import org.usfirst.frc1571.team1571bot.RobotMap;
import org.usfirst.frc1571.team1571bot.commands.*;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Shooter extends Subsystem {

    private final Solenoid solenoid = RobotMap.shooterSolenoid;

    public void initDefaultCommand() {
    	setDefaultCommand(new ShootTrigger());
    }
    
    public void setExtended(boolean extended) {
    	solenoid.set(extended);
    }
}

