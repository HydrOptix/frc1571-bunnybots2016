package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBurst extends Command {
	
	Timer shootTimer = new Timer();
	boolean firing;
	boolean retracting;
	boolean isFinished;
	int dartsShot;

    public ShootBurst() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	if(Robot.flywheels.getSpeed() == 0) {
    		System.out.println("Warning - Firing with stationary flywheels prevented");
    		isFinished = true;
    		this.end();
    	} else {
        	firing = false;
        	retracting = false;
        	isFinished = false;
        	dartsShot = 0;
    	}
    }

    protected void execute() {

    	boolean triggerPressed = Robot.oi.shooterButtonTrigger.get();
    	
    	if(triggerPressed && !firing && !retracting) {
    		firing = true;
    		Robot.shooter.setExtended(true);
    		shootTimer.reset();
    		shootTimer.start();
    		Robot.shooter.setDarts(Robot.shooter.getDarts()-1);
    		dartsShot++;
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
    		if(dartsShot >= RobotMap.burstLength) {
    			isFinished = true;
    			this.end();
    		}
    	}
    	
    }
    	

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    }

    protected void interrupted() {
    	System.out.println("Warning - Dart burst interrupted");
    	Robot.shooter.setExtended(false);
    }
}
