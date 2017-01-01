package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReportPDP extends Command {
	
    public ReportPDP() {
    	requires(Robot.powerDistribution);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.powerDistribution.reportValues();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
