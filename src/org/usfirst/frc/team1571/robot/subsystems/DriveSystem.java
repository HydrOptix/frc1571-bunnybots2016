package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;
import org.usfirst.frc.team1571.robot.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveSystem extends Subsystem {

    private final CANTalon steeringTalon = RobotMap.steeringTalon;
    private final CANTalon driveTalons = RobotMap.driveTalonLeftMaster;
    private final DigitalInput limit = RobotMap.driveLimit;

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveGamepad());
    }
    
    public void steer(double position) {//position is a number between -1 and 1, 0 being far left and 1 being far right
    	double targetPosition = (position+1)/2 // convert to a 0-1 range
    							* RobotMap.steeringCountsRange	//multiply by steering range
    							+ RobotMap.steeringMinCounts;	//add the minimum of steering range
    	steeringTalon.changeControlMode(CANTalon.TalonControlMode.Position);
    	steeringTalon.set(targetPosition);
    }
    
    public void steerSpeed(double speed) {
    	steeringTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	steeringTalon.set(speed);
    }
    
    public void setDriveSpeed(double speed) {
    	driveTalons.set(speed * RobotMap.driveSpeed);
    }
    
    public boolean getLimitSwitch() {
    	return limit.get();
    }
    
    public void resetPosition() {
    	driveTalons.setPosition(0);
    	driveTalons.setPosition(0);
    }
    
    public double getEncoderCounts() {
    	return steeringTalon.get();
    }
}

