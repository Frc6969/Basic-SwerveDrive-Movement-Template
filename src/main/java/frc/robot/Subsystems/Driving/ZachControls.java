package frc.robot.Subsystems.Driving;

import frc.robot.Constants;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;


public class ZachControls {
    private SwerveSubsystem driveBase;
    private XboxController controller;

    public ZachControls(SwerveSubsystem driveSubsystem, XboxController cont){
        driveBase=driveSubsystem;
        controller=cont;
    }

    public void run(){
        //Left Stick
        double xSpeedDriveL = controller.getLeftX();
        double ySpeedDriveL = controller.getLeftY();

        //Right Stick
        double xSpeedDriveR = controller.getRightX();
        double ySpeedDriveR = -controller.getRightY();

        //Using triggers right is positive left is negative
        double turnRate = controller.getLeftTriggerAxis() - controller.getRightTriggerAxis();

        //Drive perfectly forward when pressing Y
        if(controller.getYButton()){
        ySpeedDriveR = .85;
        }
        //Drive perfectly backwards when pressing A
        else if(controller.getAButton()){
        ySpeedDriveR = -.85;
        }

        //Set wheels straight
        if(controller.getBButtonPressed()){
        driveBase.SetWheelDirection(0);
        }
        //set wheels sideways
        if(controller.getXButton()){
        driveBase.SetWheelDirection(90);
        }
    
        //Drive the robot with priority on robot relative
        if(Math.abs(ySpeedDriveR) > Constants.controllerDeadzone || Math.abs(xSpeedDriveR) > Constants.controllerDeadzone)
            driveBase.driveRobotRelative(new ChassisSpeeds(ySpeedDriveR,xSpeedDriveR,turnRate));
        else if(Math.abs(ySpeedDriveL) > Constants.controllerDeadzone || Math.abs(xSpeedDriveL) > Constants.controllerDeadzone)
            driveBase.driveFieldRelative(new ChassisSpeeds(ySpeedDriveL,-xSpeedDriveL,turnRate));
    }
}
