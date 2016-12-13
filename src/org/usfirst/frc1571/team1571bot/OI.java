package org.usfirst.frc1571.team1571bot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1571.team1571bot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public Joystick gamepadDriver;
    	public double gamepadDriver_deadzoneRadiusLStick = Robot.prefs.getDouble("gamepadDriver_deadzoneRadiusLStick", 0.2);
    	public double gamepadDriver_deadzoneRadiusTriggers = Robot.prefs.getDouble("gamepadDriver_deadzoneRadiusTriggers", 0.15);
    	
    	public JoystickButton driverButtonA;
        public JoystickButton driverButtonY;
        public JoystickButton shooterButtonTrigger;
        public JoystickButton shooterButtonSecondary;
            	
    public Joystick joystickShooter;
	    public double gamepadShooter_deadzoneRadiusTwist = Robot.prefs.getDouble("gamepadShooter_deadzoneRadiusTwist", 0.26);
				
    public OI() {

        gamepadDriver = new Joystick(0);
        joystickShooter = new Joystick(1);
        
        driverButtonY = new JoystickButton(gamepadDriver, 4);
        	driverButtonY.whenPressed(new SetGear(2));
        driverButtonA = new JoystickButton(gamepadDriver, 1);
        	driverButtonA.whenPressed(new SetGear(1));
        
        shooterButtonTrigger = new JoystickButton(joystickShooter, 1);
        	shooterButtonTrigger.whenPressed(new ShootTrigger());
        shooterButtonSecondary = new JoystickButton(joystickShooter, 2);
        	shooterButtonSecondary.whenPressed(new ShootOnce());

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        
        SmartDashboard.putData("Gear 1", new SetGear(1));
        SmartDashboard.putData("Gear 2", new SetGear(2));
        
        SmartDashboard.putData("Rotate Gun Counterclockwise", new AimSetSpeed(-1));
        SmartDashboard.putData("Stop Gun", new AimSetSpeed(0));
        SmartDashboard.putData("Rotate Gun Clockwise", new AimSetSpeed(1));
        
        SmartDashboard.putData("Spin Flywheels", new FlywheelsSetSpeed(1));
        SmartDashboard.putData("Stop Flywheels", new FlywheelsSetSpeed(0));
        
        SmartDashboard.putData("Shoot Once", new ShootOnce());
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

