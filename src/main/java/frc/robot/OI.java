package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.commands.OneWheelControl;
import frc.commands.TestAngles;

public class OI {


    public XboxController driveController, manipController;

    public OI() {
        initControllers();
        
        Robot.swerveWheel.setDefaultCommand(new TestAngles());
    }

    /**
     * Initialize JoystickButtons and Controllers
     */
    private void initControllers() {
        driveController = new XboxController(0);
        manipController = new XboxController(1);

    }

    public double getDriverLeftX() {
        return driveController.getLeftX();
    }
    public double getDriverLeftY() {
        return driveController.getLeftY();
    }
    public double getDriverRightX() {
        return driveController.getRightX();
    }


}