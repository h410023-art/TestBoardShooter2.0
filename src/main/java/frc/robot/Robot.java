package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.RPM;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.VoltageConfigs;

public class Robot extends TimedRobot {
  private final TalonFX motorLF = new TalonFX(23);
  private final TalonFX motorLB = new TalonFX(24);
  private final TalonFX motorRF = new TalonFX(21);
  private final TalonFX motorRB = new TalonFX(22);
  private final TalonFX motorLifting = new TalonFX(25);
  private final TalonFX motorConveyor = new TalonFX(26);

  private final VelocityVoltage velocityRequest = new VelocityVoltage(0.5).withSlot(0);

  private  static final AngularVelocity kFreeSpeed = RPM.of(6000);
  private final XboxController xbox = new XboxController(0);

  public Robot() {
    var motorLFconfig = motorLF.getConfigurator();
    var motorLBconfig = motorLB.getConfigurator();
    var motorRFconfig = motorRF.getConfigurator();
    var motorRBconfig = motorRB.getConfigurator();
    var motorLiftingconfig = motorLifting.getConfigurator();
    var motorConveyorconfig = motorConveyor.getConfigurator();

    motorLifting.getConfigurator().apply(new TalonFXConfiguration());

    motorLFconfig.apply(new FeedbackConfigs()
    .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor));
    
    motorLFconfig.apply(new MotorOutputConfigs()
    .withInverted(InvertedValue.CounterClockwise_Positive)
    .withNeutralMode(NeutralModeValue.Coast));

    motorLFconfig.apply(new VoltageConfigs()
    .withPeakReverseVoltage(Volts.of(0)));

    motorLFconfig.apply(new CurrentLimitsConfigs()
    .withStatorCurrentLimit(Amps.of(120))
    .withStatorCurrentLimitEnable(true)
    .withSupplyCurrentLimit(Amps.of(70))
    .withSupplyCurrentLimitEnable(true));

    motorLFconfig.apply(
      new Slot0Configs().withKP(0.1) 
                        .withKI(0)
                        .withKD(0)
                        .withKV(12.0/ kFreeSpeed.in(RotationsPerSecond))
    );

    motorLBconfig.apply(new FeedbackConfigs()
    .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor));
    
    motorLBconfig.apply(new MotorOutputConfigs()
    .withNeutralMode(NeutralModeValue.Coast));

    motorLBconfig.apply(new VoltageConfigs()
    .withPeakReverseVoltage(Volts.of(0)));

    motorLBconfig.apply(new CurrentLimitsConfigs()
    .withStatorCurrentLimit(Amps.of(120))
    .withStatorCurrentLimitEnable(true)
    .withSupplyCurrentLimit(Amps.of(70))
    .withSupplyCurrentLimitEnable(true));

    motorLBconfig.apply(
      new Slot0Configs().withKP(0.1) 
                        .withKI(0)
                        .withKD(0)
                        .withKV(12.0/ kFreeSpeed.in(RotationsPerSecond))
    );

    motorRFconfig.apply(new FeedbackConfigs()
    .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor));
    
    motorRFconfig.apply(new MotorOutputConfigs()
    .withNeutralMode(NeutralModeValue.Coast));

    motorRFconfig.apply(new VoltageConfigs()
    .withPeakReverseVoltage(Volts.of(0)));

    motorRFconfig.apply(new CurrentLimitsConfigs()
    .withStatorCurrentLimit(Amps.of(120))
    .withStatorCurrentLimitEnable(true)
    .withSupplyCurrentLimit(Amps.of(70))
    .withSupplyCurrentLimitEnable(true));

    motorRFconfig.apply(
      new Slot0Configs().withKP(0.1) 
                        .withKI(0)
                        .withKD(0)
                        .withKV(12.0/ kFreeSpeed.in(RotationsPerSecond))
    );

    motorRBconfig.apply(new FeedbackConfigs()
    .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor));
    
    motorRBconfig.apply(new MotorOutputConfigs()
    .withNeutralMode(NeutralModeValue.Coast));

    motorRBconfig.apply(new VoltageConfigs()
    .withPeakReverseVoltage(Volts.of(0)));

    motorRBconfig.apply(new CurrentLimitsConfigs()
    .withStatorCurrentLimit(Amps.of(120))
    .withStatorCurrentLimitEnable(true)
    .withSupplyCurrentLimit(Amps.of(70))
    .withSupplyCurrentLimitEnable(true));

    motorRBconfig.apply(
      new Slot0Configs().withKP(0.1) 
                        .withKI(0)
                        .withKD(0)
                        .withKV(12.0/ kFreeSpeed.in(RotationsPerSecond))
    );

    motorLiftingconfig.apply(new FeedbackConfigs()
    .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor));
    
    motorLiftingconfig.apply(new MotorOutputConfigs()
    .withNeutralMode(NeutralModeValue.Brake));

    motorLiftingconfig.apply(new CurrentLimitsConfigs()
    .withStatorCurrentLimit(Amps.of(120))
    .withStatorCurrentLimitEnable(true)
    .withSupplyCurrentLimit(Amps.of(70))
    .withSupplyCurrentLimitEnable(true));

    motorLiftingconfig.apply(
      new Slot0Configs().withKP(0.1) 
                        .withKI(0)
                        .withKD(0)
                        .withKV(12.0/ kFreeSpeed.in(RotationsPerSecond))
    );

    motorConveyorconfig.apply(new FeedbackConfigs()
    .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor));
    
    motorConveyorconfig.apply(new MotorOutputConfigs()
    .withNeutralMode(NeutralModeValue.Coast));

    motorConveyorconfig.apply(new VoltageConfigs()
    .withPeakReverseVoltage(Volts.of(0)));

    motorConveyorconfig.apply(new CurrentLimitsConfigs()
    .withStatorCurrentLimit(Amps.of(120))
    .withStatorCurrentLimitEnable(true)
    .withSupplyCurrentLimit(Amps.of(70))
    .withSupplyCurrentLimitEnable(true));

    motorConveyorconfig.apply(
      new Slot0Configs().withKP(0.1) 
                        .withKI(0)
                        .withKD(0)
                        .withKV(12.0/ kFreeSpeed.in(RotationsPerSecond))
    );
    motorLB.setControl(new Follower(motorLF.getDeviceID(),MotorAlignmentValue.Aligned));
    motorRF.setControl(new Follower(motorLF.getDeviceID(),MotorAlignmentValue.Opposed));
    motorRB.setControl(new Follower(motorLF.getDeviceID(),MotorAlignmentValue.Opposed));
    motorConveyor.setControl(new Follower(motorLF.getDeviceID(),MotorAlignmentValue.Aligned));
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    motorLifting.setPosition(Rotations.of(0));
  }

  @Override
  public void teleopPeriodic() {
    double targetRPM = 300;
    double actualRPM = motorLF.getRotorVelocity().getValue().in(RPM);
    double maxRPM = 1000;
    double lifttargetRPM = -xbox.getLeftY()*maxRPM;
    double liftactualRPM = motorLifting.getRotorVelocity().getValue().in(RPM);
    double liftPosition = motorLifting.getRotorPosition().getValue().in(Rotations);
    double maxPosition = 2;
    double minPosition = 0;

    if(xbox.getAButton()){
      motorLF.setControl(velocityRequest.withVelocity(RPM.of(targetRPM)));
    }else{
      motorLF.stopMotor();
    }

    if(liftPosition > maxPosition && lifttargetRPM > 0){
      motorLifting.stopMotor();
    }else if(liftPosition < minPosition && lifttargetRPM<0){
      motorLifting.stopMotor();
    }else{
      motorLifting.setControl(velocityRequest.withVelocity(RPM.of(lifttargetRPM)));
    }
    
    SmartDashboard.putNumber("target", targetRPM);
    SmartDashboard.putNumber("actual", actualRPM);
    SmartDashboard.putNumber("lifttarget", lifttargetRPM);
    SmartDashboard.putNumber("liftactual", liftactualRPM);
    SmartDashboard.putNumber("liftPosition", liftPosition);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}