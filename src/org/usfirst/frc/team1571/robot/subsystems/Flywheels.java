package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;
import org.usfirst.frc.team1571.robot.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Flywheels extends Subsystem {

private final CANTalon flywheels = RobotMap.flywheelTalonMaster;


    public void initDefaultCommand() {
        setDefaultCommand(new FlywheelsThrottle());
    }
    
    public void setSpeed(double speed) {
    	flywheels.set(speed);
    	SmartDashboard.putNumber("Flywheel Speed", speed);
    }
    
    public double getSpeed() {
    	return flywheels.get();
    }
}

