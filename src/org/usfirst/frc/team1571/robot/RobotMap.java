package org.usfirst.frc.team1571.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class RobotMap {	
	//Declare all components on the robot. RobotBuilder should have done this for me, but it didn't work. Don't try to use RobotBuilder to dynamically alter code, because the old code won't get removed.

	//aimSystem components and variables
		public static CANTalon aimTalon;
			public static double aimSpeed = Robot.prefs.getDouble("aimSpeed", 1);
	
    //driveSystem components and variables
		public static CANTalon steeringTalon;
			public static int steeringMinCounts = Robot.prefs.getInt("minSteeringCounts", 0);
			public static int steeringMaxCounts = Robot.prefs.getInt("maxSteeringCounts", 31);
			public static int steeringCountsRange = steeringMaxCounts - steeringMinCounts;
		
		public static CANTalon driveTalonLeftMaster;
			public static double driveSpeed = Robot.prefs.getDouble("driveSpeed", 1);
		
		public static CANTalon driveTalonLeftSlave; //All slave talons will later be set to the follower control mode
		public static CANTalon driveTalonRightSlave_1; //right talons will be set to reverse output later
		public static CANTalon driveTalonRightSlave_2;
		
	//flywheels components and variables
		public static CANTalon flywheelTalonMaster;
			public static double flywheelSpeed = Robot.prefs.getDouble("flywheelSpeed", 1);
		
		public static CANTalon flywheelTalonSlave;
		
	//gearbox components and variables
		public static Solenoid gearboxSolenoid_Gear1;
		public static Solenoid gearboxSolenoid_Gear2;
			public static double gearChangeDelaySeconds = Robot.prefs.getDouble("Gear Change Delay (Seconds)", 0);
			
		public static Solenoid gearboxSolenoidGears[] = {gearboxSolenoid_Gear1,gearboxSolenoid_Gear2};
		
	//shooter components and variables
		public static Solenoid shooterSolenoid;
			public static double extendTime = Robot.prefs.getDouble("Fire Extend Time (Seconds)", .2);
			public static double fireDelay = Robot.prefs.getDouble("Firing Delay (Seconds)", .2);
    

    public static void init() {
    	
    	//instantiate all components to pass to Robot.java and add them to LiveWindow
        
    	//aimSystem components
    	aimTalon = new CANTalon(3);
    		LiveWindow.addActuator("aimSystem", "Aiming Talon", aimTalon);
    		aimTalon.reverseOutput(false);
    		aimTalon.setSafetyEnabled(true);
    		aimTalon.setExpiration(.1);
        
        //driveSystem components
        steeringTalon = new CANTalon(4);
			LiveWindow.addActuator("driveSystem", "Steering Talon", steeringTalon);
			steeringTalon.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
			steeringTalon.changeControlMode(CANTalon.TalonControlMode.Position);
			steeringTalon.configPotentiometerTurns(10);
			steeringTalon.setSafetyEnabled(true);
			steeringTalon.setExpiration(.1);
			steeringTalon.setAllowableClosedLoopErr(0);
			
		driveTalonLeftMaster = new CANTalon(6);
			LiveWindow.addActuator("driveSystem", "Left Master Drive Talon", driveTalonLeftMaster);
			driveTalonLeftMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			driveTalonLeftMaster.reverseOutput(false);
			
		driveTalonLeftSlave = new CANTalon(7);
			LiveWindow.addActuator("driveSystem", "Left Slave Drive Talon", driveTalonLeftSlave);
			driveTalonLeftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
			driveTalonLeftSlave.set(driveTalonLeftMaster.getDeviceID());
			driveTalonLeftSlave.reverseOutput(false);
			
		driveTalonRightSlave_1 = new CANTalon(0);
			LiveWindow.addActuator("driveSystem", "Right Slave Drive Talon 1", driveTalonRightSlave_1);
			driveTalonRightSlave_1.changeControlMode(CANTalon.TalonControlMode.Follower);
			driveTalonRightSlave_1.set(driveTalonLeftMaster.getDeviceID());
			driveTalonRightSlave_1.reverseOutput(true);
			
		driveTalonRightSlave_2 = new CANTalon(1);
			LiveWindow.addActuator("driveSystem", "Right Slave Drive Talon 2", driveTalonRightSlave_2);
			driveTalonRightSlave_2.changeControlMode(CANTalon.TalonControlMode.Follower);
			driveTalonRightSlave_2.set(driveTalonLeftMaster.getDeviceID());
			driveTalonRightSlave_2.reverseOutput(true);
        	
        //flywheels componenets
		flywheelTalonMaster = new CANTalon(2);
			LiveWindow.addActuator("flywheels", "Master flywheel (Right)", flywheelTalonMaster);
			flywheelTalonMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			flywheelTalonMaster.reverseOutput(false);
			
		flywheelTalonSlave = new CANTalon(5);
			LiveWindow.addActuator("flywheels", "Slave flywheel (Left)", flywheelTalonSlave);
			flywheelTalonSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
			flywheelTalonSlave.set(flywheelTalonMaster.getDeviceID());
			flywheelTalonSlave.reverseOutput(true);
        	
        //gearbox components
		gearboxSolenoid_Gear1 = new Solenoid(0);
			LiveWindow.addActuator("gearbox", "Gear 1 Solenoid", gearboxSolenoid_Gear1);
		gearboxSolenoid_Gear2 = new Solenoid(1);
			LiveWindow.addActuator("gearbox", "Gear 2 Solenoid", gearboxSolenoid_Gear2);
		
        //shooter components
		shooterSolenoid = new Solenoid(2);
			LiveWindow.addActuator("shooter", "Shooter Solenoid", shooterSolenoid);
    }
}
