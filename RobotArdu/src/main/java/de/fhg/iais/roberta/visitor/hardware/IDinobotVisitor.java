package de.fhg.iais.roberta.visitor.hardware;

import de.fhg.iais.roberta.syntax.actors.arduino.dinobot.MoveNeckAction;
import de.fhg.iais.roberta.util.dbc.DbcException;
import de.fhg.iais.roberta.visitor.hardware.actor.IActors4AutonomousDriveRobots;
import de.fhg.iais.roberta.visitor.hardware.actor.ISerialVisitor;
import de.fhg.iais.roberta.visitor.hardware.sensor.ISensorVisitor;

public interface IDinobotVisitor<V> extends IActors4AutonomousDriveRobots<V>, ISensorVisitor<V>, ISerialVisitor<V> {

    default V visitMoveNeckAction(MoveNeckAction<V> moveNeckAction) {
        throw new DbcException("Block is not implemented!");
    }
}
