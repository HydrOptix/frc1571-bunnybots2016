package org.usfirst.frc.team1571.subsystems;

import org.usfirst.frc.team1571.commands.*;
import org.usfirst.frc.team1571.robot.RobotMap;

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

