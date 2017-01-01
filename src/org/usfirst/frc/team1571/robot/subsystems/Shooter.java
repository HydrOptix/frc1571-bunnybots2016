package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;
import org.usfirst.frc.team1571.robot.commands.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter extends Subsystem {

    private final DoubleSolenoid solenoid = RobotMap.shooterSolenoid;
    private int dartCount = RobotMap.dartCount;

    public void initDefaultCommand() {
    	setDefaultCommand(new ShootTrigger());
    }
    
    public void setExtended(boolean extended) {
    	
    	
    	if (extended) solenoid.set(DoubleSolenoid.Value.kForward);
    	else solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public int getDarts() {
    	return dartCount;
    }
    
    public void setDarts(int darts) {
    	dartCount = darts;
    	SmartDashboard.putNumber("Darts Left", darts);
    }
}

