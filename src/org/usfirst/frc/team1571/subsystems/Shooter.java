package org.usfirst.frc.team1571.subsystems;

import org.usfirst.frc.team1571.commands.*;
import org.usfirst.frc.team1571.robot.RobotMap;

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

