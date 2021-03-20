// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

// for rounding
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static frc.robot.Constants.*;

public class DriveSub extends SubsystemBase {
  /** Creates a new DriveSub. */
  private final WPI_TalonSRX leftFrontDriveTalonSRX = new WPI_TalonSRX(k_leftFrontDriveTalon);
  private final WPI_TalonSRX leftRearDriveTalonSRX = new WPI_TalonSRX(k_leftRearDriveTalon);
  private final SpeedControllerGroup m_left = new SpeedControllerGroup(leftFrontDriveTalonSRX, leftRearDriveTalonSRX);
  private final WPI_TalonSRX rightFrontDriveTalonSRX = new WPI_TalonSRX(k_rightFrontDriveTalon);
  private final WPI_TalonSRX rightRearDriveTalonSRX = new WPI_TalonSRX(k_rightRearDriveTalon);
  private final SpeedControllerGroup m_right = new SpeedControllerGroup(rightFrontDriveTalonSRX, rightRearDriveTalonSRX);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right); 

  // new varibales for gyro
  private final ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();

  private double m_gyroReading = 0.0;


  // robot drive
  public DifferentialDrive robotDrive() {
    return m_robotDrive;
  }


  // method def w/ gyro
  public DriveSub() {
      m_gyro.calibrate();
  }

  // return gyro value
  public double getAngle() {
    return m_gyroReading;
  }

  // set to striaght
  public void setStraight() {
    m_gyro.reset();
  }

  // drive with two speeds
  public void drive(final double lSpeed, final double rSpeed) {
    m_robotDrive.tankDrive(lSpeed, rSpeed, true);
   }
   

  @Override
  public void periodic() {

    m_gyroReading = m_gyro.getAngle();

   /* // This method will be called once per scheduler run
    double gyroAng = m_gyro.getAngle();

    // get the recet rate
    double gyroRate = m_gyro.getRate();

    DecimalFormat df = new DecimalFormat("#.###");
    df.setRoundingMode(RoundingMode.CEILING);

    //System.out.println(df.format(gyroAng));

    System.out.println(df.format(gyroRate));
    */
  
  }

}
