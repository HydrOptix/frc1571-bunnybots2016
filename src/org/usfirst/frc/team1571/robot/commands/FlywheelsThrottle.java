package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class FlywheelsThrottle extends Command {
	
    public FlywheelsThrottle() {
    	requires(Robot.flywheels);
    }

    protected void initialize() {
    }

    protected void execute() {
    	
    	double throttle = Robot.oi.joystickShooter.getRawAxis(3);
    	Robot.flywheels.setSpeed( ((throttle-1)/-2) * RobotMap.aimSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}