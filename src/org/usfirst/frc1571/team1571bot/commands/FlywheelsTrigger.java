package org.usfirst.frc1571.team1571bot.commands;

import org.usfirst.frc1571.team1571bot.Robot;
import org.usfirst.frc1571.team1571bot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class FlywheelsTrigger extends Command {
	
    public FlywheelsTrigger() {
    	requires(Robot.flywheels);
    }

    protected void initialize() {
    }

    protected void execute() {
    	double rightTrigger = Robot.oi.gamepadShooter.getRawAxis(3);
    	
    	if(Math.abs(rightTrigger) > Robot.oi.gamepadShooter_deadzoneRadiusTriggers) {
    		Robot.flywheels.setSpeed(rightTrigger * RobotMap.aimSpeed);
    		Robot.oi.gamepadShooter.setRumble(Joystick.RumbleType.kLeftRumble, (float)rightTrigger);
    	} else {
    		Robot.flywheels.setSpeed(0);
    		Robot.oi.gamepadShooter.setRumble(Joystick.RumbleType.kLeftRumble, 0);
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}