package frc.robot.Subsystems.Driving;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.io.File;

import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
import edu.wpi.first.wpilibj.Filesystem;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;

public class SwerveSubsystem extends SubsystemBase{
  double maximumSpeed = Units.feetToMeters(4.5);
  File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
  SwerveDrive swerveDrive;

  public SwerveSubsystem(File directory){
    try
    {
      swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed);
    // Alternative method if you don't want to supply the conversion factor via JSON files.
    // swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed, angleConversionFactor, driveConversionFactor);
    } catch (Exception e)
    {
        throw new RuntimeException(e);
    }
    SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
    swerveDrive.setHeadingCorrection(false); // Heading correction should only be used while controlling the robot via angle.
    swerveDrive.setCosineCompensator(false); // Disables cosine compensation for simulations since it causes discrepancies not seen in real life.
  }

  public void driveRobotRelative(ChassisSpeeds speeds){
    swerveDrive.drive(speeds);
    
  }
  public void driveFieldRelative(ChassisSpeeds speeds){
    swerveDrive.driveFieldOriented(speeds);
  }

  public Pose2d getPose(){
    return swerveDrive.getPose();
  }

  public void resetPose(Pose2d pose){
    swerveDrive.resetOdometry(pose);
  }

  public SwerveDrive getSwerve(){
    return swerveDrive;
  }

  public ChassisSpeeds getRobotRelativeSpeeds(){
    return swerveDrive.getRobotVelocity();
  }

  public void SetWheelDirection(double angle){
    swerveDrive.getModules()[0].setAngle(angle);
    swerveDrive.getModules()[1].setAngle(angle);
    swerveDrive.getModules()[2].setAngle(angle);
    swerveDrive.getModules()[3].setAngle(angle);
  }
}
