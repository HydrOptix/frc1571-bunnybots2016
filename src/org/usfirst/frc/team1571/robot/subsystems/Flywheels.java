package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;
import org.usfirst.frc.team1571.robot.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Flywheels extends Subsystem {

private final CANTalon flywheelTop = RobotMap.flywheelTalonBottom;
private final CANTalon flywheelBottom = RobotMap.flywheelTalonTop;


    public void initDefaultCommand() {
        setDefaultCommand(new FlywheelsThrottle());
    }
    
    public void setSpeed(double speed) {
    	flywheelBottom.set(speed * RobotMap.bottomSpeedMultiplier);
    	flywheelTop.set(speed * RobotMap.topSpeedMultiplier);
    	SmartDashboard.putNumber("Flywheel Speed", speed);
    }
    
    public double getSpeed() {
    	return flywheelTop.get();
    }
}

