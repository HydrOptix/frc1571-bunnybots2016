package org.usfirst.frc.team1571.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShootTrigger extends Command {
	
	Timer shootTimer = new Timer();
	boolean firing;
	boolean retracting;
	boolean isFinished;

    public ShootTrigger() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	firing = false;
    	retracting = false;
    	isFinished = false;
    }

    protected void execute() {

    	boolean triggerPressed = Robot.oi.shooterButtonTrigger.get();
    	
    	if(triggerPressed && !firing && !retracting) {
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
    		if(!triggerPressed) {
    			isFinished = false;
    		}
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
