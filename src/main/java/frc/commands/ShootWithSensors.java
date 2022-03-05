package frc.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;

public class ShootWithSensors extends ParallelCommandGroup {
    private double targetRPM;

    //TODO: Find this time
    // Time from the indexer starting to the last ball being shot
    private final double SHOOT_TIME = 2;

    private Timer timer;

    private double distanceToTarget;
    //private final double[] lookupTable = {0, 100, 200, 300};
    public ShootWithSensors() {
        addRequirements(Robot.shooter);
        
        timer = new Timer();
        
        //timer.start();

        //TODO: more reasonable default value than 0
        
        distanceToTarget = SmartDashboard.getNumber("Distance To Target", 0);
        // // TODO: lookup table for rpm
        // if (distanceToTarget > 40) {
        //     targetRPM = lookupTable[lookupTable.length-1];
        // } else {
        //     targetRPM = lookupTable[(int)(distanceToTarget/10)];
        // }
        targetRPM = 5640;
        
        // To shoot, first spin up the flywheel while turning to the target
        // When facing the target and at speed, run the indexer and hopper to feed balls to the flywheel and shoot
        addCommands(
            new SetShooterRPM(targetRPM),
            new SequentialCommandGroup(
                //new TurretTracking(),
                new WaitCommand(1),
                new InstantCommand(() -> timer.start()),
                
                //Run indexer and hopper:
                new ParallelCommandGroup(
                    new RunIndexer(), 
                    new RunHopper())
            )
        );

    }

    @Override
    public void initialize() {
        super.initialize();

        timer.reset();
        SmartDashboard.putString("Shooting", "yep");
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(SHOOT_TIME);
        //return false;
    }

    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putString("Shooting", "nope");
        Robot.shooter.stopShooter();
        Robot.shooter.stopIndexer();
        Robot.hopper.stopHopper();
    }
}
