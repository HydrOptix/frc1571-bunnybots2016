package org.usfirst.frc1571.team1571bot.commands;

import org.usfirst.frc1571.team1571bot.Robot;
import org.usfirst.frc1571.team1571bot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AimGamepad extends Command {

    public AimGamepad() {
    	requires(Robot.aimSystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	
    	double leftStickX = Robot.oi.gamepadShooter.getRawAxis(0);
    	
    	if(Math.abs(leftStickX) > Robot.oi.gamepadShooter_deadzoneRadiusLStick) {
    		Robot.aimSystem.setSpeed(leftStickX * RobotMap.aimSpeed);
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
