package org.usfirst.frc.team1571.subsystems;

import org.usfirst.frc.team1571.commands.*;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveSystem extends Subsystem {

    private final CANTalon steeringTalon = RobotMap.steeringTalon;
    private final CANTalon driveTalons = RobotMap.driveTalonLeftMaster;

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveGamepad());
    }
    
    public void steer(double position) {//position is a number between -1 and 1, 0 being far left and 1 being far right
    	double targetPosition = (position+1)/2 // convert to a 0-1 range
    							* RobotMap.steeringCountsRange	//multiply by steering range
    							+ RobotMap.steeringMinCounts;	//add the minimum of steering range
    	steeringTalon.set(targetPosition);
    }
    
    public void setDriveSpeed(double speed) {
    	driveTalons.set(speed * RobotMap.driveSpeed);
    }
    
}

