package org.usfirst.frc.team1571.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AimJoystick extends Command {

    public AimJoystick() {
    	requires(Robot.aimSystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	
    	double stickTwist = Robot.oi.joystickShooter.getRawAxis(2);
    	
    	if(Math.abs(stickTwist) > Robot.oi.gamepadShooter_deadzoneRadiusTwist) {
    		Robot.aimSystem.setSpeed(stickTwist * RobotMap.aimSpeed);
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
