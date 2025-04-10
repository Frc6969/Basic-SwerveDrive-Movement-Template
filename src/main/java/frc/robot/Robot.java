// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Subsystems.Driving.SwerveSubsystem;
import frc.robot.Subsystems.Driving.ZachControls;


public class Robot extends TimedRobot {
  
  private final XboxController DriveController = new XboxController(0);
  private static Robot instance;
  private final SwerveSubsystem driveBase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
  "swerve"));
  private final ZachControls controls = new ZachControls(driveBase, DriveController);
  public Robot()
  {
    instance = this;
  }

  public static Robot getInstance()
  {
    
    return instance;
  }
  /*
   * This function is run when the robot is first started up and should be used for any initialization code.
   */
  @Override
  public void robotInit()
  {
    if (isSimulation())
    {
      DriverStation.silenceJoystickConnectionWarning(true);
      Translation2d spot = new Translation2d(Constants.simulationStartX,Constants.simulationStartY);
      Pose2d pose = new Pose2d(spot,new Rotation2d(0));
      driveBase.resetPose(pose);
    }
  }

  //runs every 20ms / 50 times per second
  @Override
  public void robotPeriodic()
  {

  }

  //runs when teleop is enabled
  @Override
  public void teleopInit()
  {

  }

  //runs every 20ms = 50 times per second
  @Override
  public void teleopPeriodic()
  { 
    controls.run();
  }
  //LIMELIGHT-----------------------------------------------------------
  
  //runs every 20ms / 50 times per second
  @Override
  public void autonomousPeriodic(){

  }

  //runs when auto is enabled
  @Override
  public void autonomousInit(){
    
  }
}