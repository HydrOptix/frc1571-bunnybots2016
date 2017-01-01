package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AimJoystick extends Command {

    public AimJoystick() {
    	requires(Robot.aimSystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	
    	double stickTwist = Robot.oi.joystickShooter.getRawAxis(2);
    	
    	SmartDashboard.putNumber("Joystick Value", stickTwist);
    	if(Math.abs(stickTwist) > Robot.oi.gamepadShooter_deadzoneRadiusTwist) {
    		Robot.aimSystem.setSpeed(stickTwist);
    	} else {
    		Robot.aimSystem.setSpeed(0);
    	}
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.aimSystem.setSpeed(0);
    }

    protected void interrupted() {
    	System.out.println("Notice - Gamepad aiming interrupted");
    	this.end();
    }
}
