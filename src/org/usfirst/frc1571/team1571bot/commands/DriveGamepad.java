package org.usfirst.frc1571.team1571bot.commands;

import org.usfirst.frc1571.team1571bot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveGamepad extends Command {

    public DriveGamepad() {
        requires(Robot.driveSystem);
    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//get left stick x axis and the trigger axes
    	double leftStickX = Robot.oi.gamepadDriver.getRawAxis(0); 		//range from -1 to 1
    	double leftTrigger = Robot.oi.gamepadDriver.getRawAxis(2); 	//range from 0 to 1
    	double rightTrigger = Robot.oi.gamepadDriver.getRawAxis(3); 	//range from 0 to 1
    	
    	
    	if(Math.abs(leftStickX) > Robot.oi.gamepadDriver_deadzoneRadiusLStick) { //Steer only if the joystick is outside the deadzone
    		Robot.driveSystem.steer(leftStickX);
    	} else {
    		Robot.driveSystem.steer(0);
    	}
    	if(leftTrigger < Robot.oi.gamepadDriver_deadzoneRadiusTriggers) { //If the trigger is inside the deadzone, set it to 0
    		leftTrigger = 0;
    	}
    	if(rightTrigger < Robot.oi.gamepadDriver_deadzoneRadiusTriggers) { //If the trigger is inside the deadzone, set it to 0
    		rightTrigger = 0;
    	}
    	
    	Robot.driveSystem.setDriveSpeed(rightTrigger - leftTrigger); //Use the combined values of the triggers to drive the robot. Left is negative (reverse), and right is positive (forwards)
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
