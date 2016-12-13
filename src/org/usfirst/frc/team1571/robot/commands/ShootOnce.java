package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShootOnce extends Command {
	
	Timer shootTimer = new Timer();
	boolean firing;
	boolean retracting;
	boolean isFinished;

    public ShootOnce() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	firing = false;
    	retracting = false;
    	isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(!firing && !retracting) {
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
    		isFinished = false;
    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
