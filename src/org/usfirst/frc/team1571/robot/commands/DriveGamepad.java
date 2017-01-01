package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveGamepad extends Command {

	private Timer resetTimer;
	private boolean calibrated;
	
    public DriveGamepad() {
        requires(Robot.driveSystem);
        
    }

    protected void initialize() {
    	calibrated = true;
		Robot.driveSystem.resetPosition();
    	resetTimer = new Timer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//get left stick x axis and the trigger axes
    	double leftStickX = Robot.oi.gamepadDriver.getRawAxis(0); 		//range from -1 to 1
    	double leftTrigger = Robot.oi.gamepadDriver.getRawAxis(2); 	//range from 0 to 1
    	double rightTrigger = Robot.oi.gamepadDriver.getRawAxis(3); 	//range from 0 to 1
    	double accelerationX = Robot.driveSystem.getXAcceleration();
    	double accelerationY = Robot.driveSystem.getYAcceleration();
    	double accelerationZ = Robot.driveSystem.getZAcceleration();
    	boolean xButton = Robot.oi.gamepadDriver.getRawButton(2);
    	
    	SmartDashboard.putBoolean("Limit Switch Activated", Robot.driveSystem.getLimitSwitch());
    	
    	if(!calibrated) {
    		System.out.println("Calibrating");
    		if(!Robot.driveSystem.getLimitSwitch()) {
    			Robot.driveSystem.steerSpeed(0);
    			Robot.driveSystem.resetPosition();
    			calibrated = true;
    		} else {
    			Robot.driveSystem.steerSpeed(-.75);
    		}
    		
    		
    	} else {
    		System.out.println("Calibrated");
    			resetTimer.stop();
		    	if(Math.abs(leftStickX) > Robot.oi.gamepadDriver_deadzoneRadiusLStick) { //Steer only if the joystick is outside the deadzone
		    		Robot.driveSystem.steer((float)leftStickX);
		    	} else {
		    		if(!RobotMap.accelerometerCenteringEnabled || Robot.driveSystem.getSpeed() == 0) {
		    			Robot.driveSystem.steer(RobotMap.steeringCenterPos);
		    		} else {
		    		}
		    	}
    	}
    	
    	
    	if(leftTrigger < Robot.oi.gamepadDriver_deadzoneRadiusTriggers) { //If the trigger is inside the deadzone, set it to 0
    		leftTrigger = 0;
    	}
    	if(rightTrigger < Robot.oi.gamepadDriver_deadzoneRadiusTriggers) { //If the trigger is inside the deadzone, set it to 0
    		rightTrigger = 0;
    	}
    	    	
    	Robot.driveSystem.setDriveSpeed(rightTrigger - leftTrigger); //Use the combined values of the triggers to drive the robot. Left is negative (reverse), and right is positive (forwards)
    	
    	SmartDashboard.putNumber("Steering Encoder Counts", Robot.driveSystem.getEncoderCounts());
    	SmartDashboard.putNumber("X Acceleration", accelerationX);
    	SmartDashboard.putNumber("Y Acceleration", accelerationY);
    	SmartDashboard.putNumber("Z Acceleration", accelerationZ);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
