package org.usfirst.frc.team1571.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class SetGear extends Command {
	
	private int targetGear;
	private int gearsNumber;
	private boolean isFinished;
	private Timer gearTimer = new Timer();
	private double gearDelay = RobotMap.gearChangeDelaySeconds;

    public SetGear(int gear) {
    	
    	requires(Robot.gearbox);
    	
    	targetGear = gear;
    }

    protected void initialize() {
    	isFinished = false;
    	
    	gearsNumber = RobotMap.gearboxSolenoidGears.length;
    	
    	if(targetGear != Robot.gearbox.getCurrentGear()) {
    		for(int i = 0; i < gearsNumber; i++) { //Disengage all gears that are not the target gear
    			if(i != targetGear - 1) {
    				Robot.gearbox.setGear(i + 1, false);
    			}
    		}
    		gearTimer.start(); //Start timing the delay for the currently extended gears to retract
    		Robot.oi.gamepadDriver.setRumble(Joystick.RumbleType.kRightRumble, 1);
    	} else {
    		isFinished = true;
    	}
    }

    protected void execute() {
    	if(gearTimer.get() >= gearDelay) { //If the gears are all retracted
    		gearTimer.stop();
    		Robot.gearbox.setGear(targetGear, true); //Extend the target gear
    		Robot.oi.gamepadDriver.setRumble(Joystick.RumbleType.kRightRumble, 0);
    		isFinished = true;
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
