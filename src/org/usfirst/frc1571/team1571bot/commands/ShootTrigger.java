package org.usfirst.frc1571.team1571bot.commands;

import org.usfirst.frc1571.team1571bot.Robot;
import org.usfirst.frc1571.team1571bot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShootTrigger extends Command {
	
	Timer shootTimer = new Timer();
	boolean firing;
	boolean retracting;

    public ShootTrigger() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	firing = false;
    	retracting = false;
    }

    protected void execute() {
    	
    	double rightTrigger = Robot.oi.gamepadShooter.getRawAxis(3);
    	
    	if(rightTrigger > Robot.oi.gamepadShooter_deadzoneRadiusTriggers && !firing && !retracting) {
    		firing = true;
    		Robot.shooter.setExtended(true);
    		shootTimer.reset();
    		shootTimer.start();
    	}
    	
    	if(firing && shootTimer.get() >= RobotMap.extendTime) {
    		firing = false;
    		Robot.shooter.setExtended(false);
    		shootTimer.reset();
    		retracting = true;
    	}
    	
    	if(retracting && shootTimer.get() >= RobotMap.fireDelay) {
    		retracting = false;
    		shootTimer.stop();
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
