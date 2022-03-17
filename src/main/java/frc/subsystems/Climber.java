package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
    private WPI_TalonFX leftClimbMotor; // Falcon 500
    private WPI_TalonFX rightClimbMotor; // Falcon 500
    // private DoubleSolenoid leftExtender;
    // private DoubleSolenoid rightExtender;

    private boolean isRightExtended = false;
    private boolean isLeftExtended = false;

    private double climbSpeed = 0.6;

    public Climber() {
        // Instantiate climbers motors with ports from RobotMap
        leftClimbMotor = new WPI_TalonFX(RobotMap.LEFT_CLIMB_MOTOR);
        rightClimbMotor = new WPI_TalonFX(RobotMap.RIGHT_CLIMB_MOTOR);

        leftClimbMotor.setNeutralMode(NeutralMode.Brake);
        rightClimbMotor.setNeutralMode(NeutralMode.Brake);

        leftClimbMotor.setInverted(true);

        // Instantiate piston double solenoids with the Pneumatics Hub port and the
        // solenoid ports from RobotMap
        // leftExtender = new DoubleSolenoid(PneumaticsModuleType.REVPH,
        // RobotMap.LEFT_CLIMBER_FORWARD_SOLENOID,
        // RobotMap.LEFT_CLIMBER_REVERSE_SOLENOID);
        // rightExtender = new DoubleSolenoid(PneumaticsModuleType.REVPH,
        // RobotMap.RIGHT_CLIMBER_FORWARD_SOLENOID,
        // RobotMap.RIGHT_CLIMBER_REVERSE_SOLENOID);
        // retractRightPiston();
        // retractLeftPiston();

    }

    public void leftWinchExtend() {
        // Extends the climber upwards
        leftClimbMotor.set(ControlMode.PercentOutput, climbSpeed);
    }

    public void rightWinchExtend() {
        rightClimbMotor.set(ControlMode.PercentOutput, climbSpeed);
    }

    public void leftWinchRetract() {
        // Descends the climber
        leftClimbMotor.set(ControlMode.PercentOutput, -climbSpeed);
    }

    public void rightWinchRetract() {
        rightClimbMotor.set(ControlMode.PercentOutput, -climbSpeed);
    }

    public void stopLeftClimber() {
        // Stops the climber
        leftClimbMotor.set(ControlMode.PercentOutput, 0);
    }

    public void stopRightClimber() {
        rightClimbMotor.set(ControlMode.PercentOutput, 0);
    }

    // TODO: add getLeftEncoder and getRightEncoder once we figure out what kind of
    // encoders

    public double getLeftEncoderPosition() {
        return leftClimbMotor.getSelectedSensorPosition();
    }

    public void extendLeftPiston() {
        Robot.pneumaticHub.setSolenoids(
                1 << RobotMap.LEFT_CLIMBER_FORWARD_SOLENOID | 1 << RobotMap.LEFT_CLIMBER_REVERSE_SOLENOID,
                1 << RobotMap.LEFT_CLIMBER_FORWARD_SOLENOID);
        isLeftExtended = true;
    }

    public void retractLeftPiston() {
        Robot.pneumaticHub.setSolenoids(
                1 << RobotMap.LEFT_CLIMBER_FORWARD_SOLENOID | 1 << RobotMap.LEFT_CLIMBER_REVERSE_SOLENOID,
                1 << RobotMap.LEFT_CLIMBER_REVERSE_SOLENOID);
        isLeftExtended = false;
    }

    public void toggleLeftPiston() {
        if (isLeftExtended) {
            retractLeftPiston();
        } else {
            extendLeftPiston();
        }
    }

    public void extendRightPiston() {
        Robot.pneumaticHub.setSolenoids(
                1 << RobotMap.RIGHT_CLIMBER_FORWARD_SOLENOID | 1 << RobotMap.RIGHT_CLIMBER_REVERSE_SOLENOID,
                1 << RobotMap.RIGHT_CLIMBER_FORWARD_SOLENOID);
        isRightExtended = true;
    }

    public void retractRightPiston() {
        Robot.pneumaticHub.setSolenoids(
                1 << RobotMap.RIGHT_CLIMBER_FORWARD_SOLENOID | 1 << RobotMap.RIGHT_CLIMBER_REVERSE_SOLENOID,
                1 << RobotMap.RIGHT_CLIMBER_REVERSE_SOLENOID);
        isRightExtended = false;
    }

    public void toggleRightPiston() {
        if (isRightExtended) {
            retractRightPiston();
        } else {
            extendRightPiston();
        }
    }

}
