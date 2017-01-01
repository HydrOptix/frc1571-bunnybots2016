package org.usfirst.frc.team1571.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMap {	
	//Declare all components on the robot. RobotBuilder should have done this for me, but it didn't work. Don't try to use RobotBuilder to dynamically alter code, because the old code won't get removed.

	//aimSystem components and variables
		public static CANTalon aimTalon;
			public static double aimSpeed = -.75;
	
    //driveSystem components and variables
		public static CANTalon steeringTalon;
			public static float steeringMinPos = -.1f;
			public static float steeringMaxPos = .05f;
			public static float steeringCenterPos = -.03f;
			public static float steeringPosRange = steeringMaxPos - steeringMinPos;
		
		public static CANTalon driveTalonLeftMaster;
			public static double driveSpeed = 1;
		
		public static CANTalon driveTalonLeftSlave; //All slave talons will later be set to the follower control mode
		public static CANTalon driveTalonRightSlave_1; //right talons will be set to reverse output later
		public static CANTalon driveTalonRightSlave_2;
		
		public static DigitalInput driveLimit;
		
		public static BuiltInAccelerometer steeringAccelerometer;
			public static boolean accelerometerCenteringEnabled = false;
		
	//flywheels components and variables
		public static CANTalon flywheelTalonBottom;
			public static double bottomSpeedMultiplier = -1;

		
		public static CANTalon flywheelTalonTop;
			public static double topSpeedMultiplier = 1;


		
	//gearbox components and variables
		public static Solenoid gearboxSolenoid_Gear1;
		public static Solenoid gearboxSolenoid_Gear2;
			public static double gearChangeDelaySeconds = 0;
			
		public static Solenoid gearboxSolenoidGears[] = {gearboxSolenoid_Gear1,gearboxSolenoid_Gear2};
		
	//powerdistributionpanel components and variables
		public static PowerDistributionPanel pdp;
		
	//shooter components and variables
		public static DoubleSolenoid shooterSolenoid;
			public static double extendTime = .05;
			public static double fireDelay = .2;
			public static int dartCount = 18;
			public static int burstLength = 2;
    

    public static void init() {

    	//instantiate all components to pass to Robot.java and add them to LiveWindow
        
    	//aimSystem components
    	aimTalon = new CANTalon(3);
    		LiveWindow.addActuator("aimSystem", "Aiming Talon", aimTalon);
    		aimTalon.reverseOutput(false);
    		aimTalon.setExpiration(.1);
        
        //driveSystem components
        steeringTalon = new CANTalon(4);
			LiveWindow.addActuator("driveSystem", "Steering Talon", steeringTalon);
			steeringTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
			steeringTalon.configEncoderCodesPerRev(497);
			steeringTalon.configNominalOutputVoltage(0, -0);
			steeringTalon.configPeakOutputVoltage(12, -12);
			steeringTalon.setVoltageRampRate(1);
			steeringTalon.setAllowableClosedLoopErr(0);
			steeringTalon.setPID(96, 0, 0); //PID tuning guide: http://innovativecontrols.com/blog/basics-tuning-pid-loops
			
		driveTalonLeftMaster = new CANTalon(6);
			LiveWindow.addActuator("driveSystem", "Left Master Drive Talon", driveTalonLeftMaster);
			driveTalonLeftMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			driveTalonLeftMaster.reverseOutput(false);
			driveTalonLeftMaster.enableBrakeMode(true);
			
		driveTalonLeftSlave = new CANTalon(7);
			LiveWindow.addActuator("driveSystem", "Left Slave Drive Talon", driveTalonLeftSlave);
			driveTalonLeftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
			driveTalonLeftSlave.set(driveTalonLeftMaster.getDeviceID());
			driveTalonLeftSlave.reverseOutput(false);
			driveTalonLeftMaster.enableBrakeMode(true);

			
		driveTalonRightSlave_1 = new CANTalon(0);
			LiveWindow.addActuator("driveSystem", "Right Slave Drive Talon 1", driveTalonRightSlave_1);
			driveTalonRightSlave_1.changeControlMode(CANTalon.TalonControlMode.Follower);
			driveTalonRightSlave_1.set(driveTalonLeftMaster.getDeviceID());
			driveTalonRightSlave_1.reverseOutput(true);
			driveTalonLeftMaster.enableBrakeMode(true);

			
		driveTalonRightSlave_2 = new CANTalon(1);
			LiveWindow.addActuator("driveSystem", "Right Slave Drive Talon 2", driveTalonRightSlave_2);
			driveTalonRightSlave_2.changeControlMode(CANTalon.TalonControlMode.Follower);
			driveTalonRightSlave_2.set(driveTalonLeftMaster.getDeviceID());
			driveTalonRightSlave_2.reverseOutput(true);
			driveTalonLeftMaster.enableBrakeMode(true);

			
		driveLimit = new DigitalInput(0);
			LiveWindow.addSensor("driveSystem", "Steering Limit", driveLimit);
			
		steeringAccelerometer = new BuiltInAccelerometer();
			LiveWindow.addSensor("driveSystem", "Accelerometer", steeringAccelerometer);
        	
        //flywheels componenets
		flywheelTalonBottom = new CANTalon(2);
			LiveWindow.addActuator("flywheels", "Bottom Flywheel", flywheelTalonBottom);
			flywheelTalonBottom.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			flywheelTalonBottom.enableBrakeMode(false);
			
		flywheelTalonTop = new CANTalon(5);
			LiveWindow.addActuator("flywheels", "Top Flywheel", flywheelTalonTop);
			flywheelTalonTop.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			flywheelTalonTop.reverseOutput(true);
			flywheelTalonTop.enableBrakeMode(false);
        	
        //gearbox components
		gearboxSolenoid_Gear1 = new Solenoid(0);
			LiveWindow.addActuator("gearbox", "Gear 1 Solenoid", gearboxSolenoid_Gear1);
		gearboxSolenoid_Gear2 = new Solenoid(1);
			LiveWindow.addActuator("gearbox", "Gear 2 Solenoid", gearboxSolenoid_Gear2);
			
		//powerdistributionsystem components
		pdp = new PowerDistributionPanel();
			LiveWindow.addSensor("power distribution system", "pdp", pdp);
		
        //shooter components
		shooterSolenoid = new DoubleSolenoid(2,3);
			LiveWindow.addActuator("shooter", "Shooter Solenoid", shooterSolenoid);
    }
}
