package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class SetGear1 extends Command {
	
	private boolean isFinished;
	private Timer gearTimer = new Timer();
	private double gearDelay = RobotMap.gearChangeDelaySeconds;
	private String currentPhase;

    public SetGear1() {
    	
    	requires(Robot.gearbox);
    	
    }

    protected void initialize() {
	    	isFinished = false;
	    	currentPhase = "retract";
    }

    protected void execute() {
    	
    	if(currentPhase == "retract") {
    		currentPhase = "extend";
    		gearTimer.start();
    	} else if(currentPhase == "extend" && gearTimer.hasPeriodPassed(gearDelay)) {
    		Robot.gearbox.setGear(1, true);
    		Robot.gearbox.setGear(2, true);
    		gearTimer.reset();
    		currentPhase = "delay";
    	} else if(currentPhase == "delay" && gearTimer.hasPeriodPassed(gearDelay)) {
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
    	System.out.println("Warning - Gear change interrupted");
    	Robot.oi.gamepadDriver.setRumble(Joystick.RumbleType.kRightRumble, 0);
    }
}
