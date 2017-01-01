package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShootOnce extends Command {
	
	Timer shootTimer = new Timer();
	boolean firing, retracting;
	boolean isFinished;
	boolean triggerWasPressed;

    public ShootOnce() {
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
        	triggerWasPressed = false;
    	}    	
    }

    protected void execute() {
    	
    	if(Robot.oi.gamepadDriver.getRawButton(2)) {
    		RobotMap.fireDelay = .05;
    	} else {
    		RobotMap.fireDelay = .2;
    	}
    	
    	if(!firing && !retracting) {
    		System.out.println("Firing Phase");
    		firing = true;
    		Robot.shooter.setExtended(true);
    		shootTimer.reset();
    		shootTimer.start();
    		Robot.shooter.setDarts(Robot.shooter.getDarts()-1);
    	}
    	
    	if(firing && !retracting && shootTimer.get() >= RobotMap.extendTime) {
    		System.out.println("Retracting phase");
    		retracting = true;
    		Robot.shooter.setExtended(false);
    		shootTimer.reset();
    	}
    	
    	if(firing && retracting && shootTimer.get() >= RobotMap.fireDelay) {
    		System.out.println("Finishing phase");
    		retracting = false;
    		shootTimer.stop();
    		isFinished = true;
    		this.end();
    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    }

    protected void interrupted() {
    	Robot.shooter.setExtended(false);
    }
}
