package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;
import org.usfirst.frc.team1571.robot.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class AimSystem extends Subsystem {

	private final CANTalon aimTalon = RobotMap.aimTalon;
	private double aimSpeed = RobotMap.aimSpeed;

    public void initDefaultCommand() {
    	setDefaultCommand(new AimJoystick());
    }
    
    public double getSpeed() {
    	return aimTalon.get();
    }
    
    public void setSpeed(double speed) {
    	aimTalon.set(speed * aimSpeed);
    }
}

