package org.usfirst.frc.team1571.robot;

import org.usfirst.frc.team1571.robot.commands.*;
import org.usfirst.frc.team1571.robot.subsystems.*;

import edu.wpi.first.wpilibj.CameraServer;
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
    public static PowerDistributionSystem powerDistribution;
    public static Shooter shooter;
    
    public static OI oi;
    
    CameraServer camera;



    public void robotInit() {
    	
    	camera = CameraServer.getInstance();
    	camera.setQuality(50);
    	camera.startAutomaticCapture("cam0");
    	
    RobotMap.init();
        aimSystem = new AimSystem();
        driveSystem = new DriveSystem();
        flywheels = new Flywheels();
        gearbox = new Gearbox();
        powerDistribution = new PowerDistributionSystem();
        shooter = new Shooter();

        oi = new OI();

        autonomousCommand = new AutonomousCommand();
        
        SmartDashboard.putData(aimSystem);
        SmartDashboard.putData(driveSystem);
        SmartDashboard.putData(flywheels);
        SmartDashboard.putData(gearbox);
        SmartDashboard.putData(powerDistribution);
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
    	if (autonomousCommand != null) autonomousCommand.cancel();
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
