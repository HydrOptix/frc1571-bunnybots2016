package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveManual extends Command {
	
	private boolean isFinished;
	private float steerPosition, speed;

    public DriveManual(float steerPosition, float speed) {
        requires(Robot.driveSystem);
        
        this.steerPosition = steerPosition;
        this.speed = speed;
    }

    protected void initialize() {
    	isFinished = false;
    }

    protected void execute() {
    	
    	Robot.driveSystem.steer(steerPosition);
    	Robot.driveSystem.setDriveSpeed(speed);
    	isFinished = true;
    	
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
