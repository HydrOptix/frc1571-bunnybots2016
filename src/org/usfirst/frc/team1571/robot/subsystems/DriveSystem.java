package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;
import org.usfirst.frc.team1571.robot.commands.*;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveSystem extends Subsystem {

    private final CANTalon steeringTalon = RobotMap.steeringTalon;
    private final CANTalon slaveTalon1 = RobotMap.driveTalonLeftSlave;
    private final CANTalon slaveTalon2 = RobotMap.driveTalonRightSlave_1;
    private final CANTalon slaveTalon3 = RobotMap.driveTalonRightSlave_2;
    private final CANTalon driveTalons = RobotMap.driveTalonLeftMaster;
    private final DigitalInput limit = RobotMap.driveLimit;
    private final BuiltInAccelerometer accelerometer = RobotMap.steeringAccelerometer;
    private double currentSpeed;

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveGamepad());
    }
    
    public void steer(float position) {//position is a number between -1 and 1, 0 being far left and 1 being far right
    	float targetPosition = (position+1)/2 // convert to a 0-1 range
    							* RobotMap.steeringPosRange	//multiply by steering range
    							+ RobotMap.steeringMinPos;	//add the minimum of steering range
    	steeringTalon.changeControlMode(CANTalon.TalonControlMode.Position);
    	steeringTalon.set(targetPosition);
    }
    
    public void steerSpeed(double speed) {
    	steeringTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	steeringTalon.set(speed);
    }
    
    public void setDriveSpeed(double speed) {
    	driveTalons.set(speed * RobotMap.driveSpeed);
    	currentSpeed = speed;
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
    
    public double getXAcceleration() {
    	return accelerometer.getX();
    }
    
    public double getYAcceleration() {
    	return accelerometer.getY();
    }
    
    public double getZAcceleration() {
    	return accelerometer.getZ();
    }
    
    public double getSpeed() {
    	return currentSpeed;
    }
    
    public void setBraking(boolean enabled) {
    	steeringTalon.enableBrakeMode(enabled);
    	slaveTalon1.enableBrakeMode(enabled);
    	slaveTalon2.enableBrakeMode(enabled);
    	slaveTalon3.enableBrakeMode(enabled);
    }
}

