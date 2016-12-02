package org.usfirst.frc1571.team1571bot.commands;

import org.usfirst.frc1571.team1571bot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousCommand extends Command {

    public AutonomousCommand() {
    }

    protected void initialize() {
    	Robot.driveSystem.getClass();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
