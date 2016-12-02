package org.usfirst.frc1571.team1571bot.subsystems;

import org.usfirst.frc1571.team1571bot.RobotMap;
import org.usfirst.frc1571.team1571bot.commands.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class AimSystem extends Subsystem {

	private final CANTalon aimTalon = RobotMap.aimTalon;
	private double aimSpeed = RobotMap.aimSpeed;

    public void initDefaultCommand() {
    	setDefaultCommand(new AimGamepad());
    }
    
    public double getSpeed() {
    	return aimTalon.get();
    }
    
    public void setSpeed(double speed) {
    	aimTalon.set(speed * aimSpeed);
    }
}

