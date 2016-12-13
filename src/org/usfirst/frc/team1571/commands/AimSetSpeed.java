package org.usfirst.frc.team1571.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AimSetSpeed extends Command {

	private double targetSpeed;
	private boolean isFinished;
	
    public AimSetSpeed(double speed) {
    	requires(Robot.aimSystem);
    	targetSpeed = speed;
    }

    protected void initialize() {
    	isFinished = false;
    }

    protected void execute() {
    	Robot.aimSystem.setSpeed(targetSpeed);
    	isFinished = true;
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	Robot.aimSystem.setSpeed(0);
    }

    protected void interrupted() {
    	this.end();
    }
}
