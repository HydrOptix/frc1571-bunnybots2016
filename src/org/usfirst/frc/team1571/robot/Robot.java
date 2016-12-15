package org.usfirst.frc.team1571.robot;

import org.usfirst.frc.team1571.robot.commands.*;
import org.usfirst.frc.team1571.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

    Command autonomousCommand;
    
    public static AimSystem aimSystem;
    public static DriveSystem driveSystem;
    public static Flywheels flywheels;
    public static Gearbox gearbox;
    public static Shooter shooter;
    public static OI oi;



    public void robotInit() {
    RobotMap.init();
        aimSystem = new AimSystem();
        driveSystem = new DriveSystem();
        flywheels = new Flywheels();
        gearbox = new Gearbox();
        shooter = new Shooter();

        oi = new OI();

        // instantiate the command used for the autonomous period
//        autonomousCommand = new AutonomousCommand();
        
        SmartDashboard.putData(aimSystem);
        SmartDashboard.putData(driveSystem);
        SmartDashboard.putData(flywheels);
        SmartDashboard.putData(gearbox);
        SmartDashboard.putData(shooter);
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void log() {
    }
}
