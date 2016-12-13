package org.usfirst.frc1571.team1571bot.subsystems;

import org.usfirst.frc1571.team1571bot.RobotMap;
import org.usfirst.frc1571.team1571bot.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Flywheels extends Subsystem {

private final CANTalon flywheels = RobotMap.flywheelTalonMaster;


    public void initDefaultCommand() {
        setDefaultCommand(new FlywheelsThrottle());
    }
    
    public void setSpeed(double speed) {
    	flywheels.set(speed);
    }
    
    public double getSpeed() {
    	return flywheels.get();
    }
}

