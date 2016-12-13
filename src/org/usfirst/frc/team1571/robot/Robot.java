package org.usfirst.frc.team1571.robot;

import org.usfirst.frc.team1571.commands.*;
import org.usfirst.frc.team1571.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

    Command autonomousCommand;
    
    public static Preferences prefs;

    public static OI oi;
    
    public static AimSystem aimSystem;
    public static DriveSystem driveSystem;
    public static Flywheels flywheels;
    public static Gearbox gearbox;
    public static Shooter shooter;


    public void robotInit() {
    RobotMap.init();
        aimSystem = new AimSystem();
        driveSystem = new DriveSystem();
        flywheels = new Flywheels();
        gearbox = new Gearbox();
        shooter = new Shooter();

        oi = new OI();

        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousCommand();
    }

    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
}
