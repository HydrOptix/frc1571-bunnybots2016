package org.usfirst.frc.team1571.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1571.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public Joystick gamepadDriver;
    	public double gamepadDriver_deadzoneRadiusLStick = 0.2;
    	public double gamepadDriver_deadzoneRadiusTriggers = 0.15;
    	
    	public JoystickButton driverButtonA;
        public JoystickButton driverButtonY;
        public JoystickButton shooterButtonTrigger;
        public JoystickButton shooterButtonSecondary;
            	
    public Joystick joystickShooter;
	    public double gamepadShooter_deadzoneRadiusTwist = 0.5;
				
    public OI() {

        gamepadDriver = new Joystick(0);
        joystickShooter = new Joystick(1);
        
        driverButtonY = new JoystickButton(gamepadDriver, 4);
        	driverButtonY.whenPressed(new SetGear2());
        	
        driverButtonA = new JoystickButton(gamepadDriver, 1);
        	driverButtonA.whenPressed(new SetGear1());
        
        shooterButtonTrigger = new JoystickButton(joystickShooter, 1);
        	shooterButtonTrigger.whenPressed(new ShootOnce());
        	
        shooterButtonSecondary = new JoystickButton(joystickShooter, 2);
        	shooterButtonSecondary.whenPressed(new ShootBurst());

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    }

    public Joystick getJoystick(int joystick) {
        if(joystick == 0) {
        	return gamepadDriver;
        } else if(joystick == 1) {
        	return gamepadDriver;
        } else {
        	return null;
        }
    }
}

