package de.fhg.iais.roberta.visitor.collect;

import java.util.List;

import com.google.common.collect.ClassToInstanceMap;

import de.fhg.iais.roberta.bean.IProjectBean;
import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.components.ConfigurationAst;
import de.fhg.iais.roberta.components.UsedActor;
import de.fhg.iais.roberta.components.UsedSensor;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.SC;
import de.fhg.iais.roberta.syntax.action.light.LightAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.CurveAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.DriveAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.MotorDriveStopAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.TurnAction;
import de.fhg.iais.roberta.syntax.action.serial.SerialWriteAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayNoteAction;
import de.fhg.iais.roberta.syntax.action.sound.ToneAction;
import de.fhg.iais.roberta.syntax.actors.arduino.dinobot.MoveNeckAction;
import de.fhg.iais.roberta.visitor.hardware.IDinobotVisitor;

/**
 * This visitor collects information for used actors and sensors in blockly program.
 *
 * @author kcvejoski
 */
public final class DinobotUsedHardwareCollectorVisitor extends AbstractUsedHardwareCollectorVisitor implements IDinobotVisitor<Void> {
    public DinobotUsedHardwareCollectorVisitor(List<List<Phrase<Void>>> phrasesSet, ConfigurationAst configuration, ClassToInstanceMap<IProjectBean.IBuilder<?>> beanBuilders) {
        super(configuration, beanBuilders);
    }

    @Override
    public Void visitToneAction(ToneAction<Void> toneAction) {
        super.visitToneAction(toneAction);
        this.getBuilder(UsedHardwareBean.Builder.class).addUsedActor(new UsedActor(toneAction.getPort(), SC.BUZZER));
        return null;
    }

    @Override
    public Void visitPlayNoteAction(PlayNoteAction<Void> playNoteAction) {
        super.visitPlayNoteAction(playNoteAction);
        this.getBuilder(UsedHardwareBean.Builder.class).addUsedActor(new UsedActor(playNoteAction.getPort(), SC.BUZZER));
        return null;
    }

    @Override
    public Void visitDriveAction(DriveAction<Void> driveAction) {
        driveAction.getParam().getSpeed().accept(this);
        if ( driveAction.getParam().getDuration() != null ) {
            driveAction.getParam().getDuration().getValue().accept(this);
        }
        this.getBuilder(UsedHardwareBean.Builder.class).addUsedActor(new UsedActor(this.robotConfiguration.getFirstMotorPort(SC.LEFT), SC.DIFFERENTIAL_DRIVE));
        return null;
    }

    @Override
    public Void visitCurveAction(CurveAction<Void> curveAction) {
        curveAction.getParamLeft().getSpeed().accept(this);
        curveAction.getParamRight().getSpeed().accept(this);
        if ( curveAction.getParamLeft().getDuration() != null ) {
            curveAction.getParamLeft().getDuration().getValue().accept(this);
        }
        this.getBuilder(UsedHardwareBean.Builder.class).addUsedActor(new UsedActor(this.robotConfiguration.getFirstMotorPort(SC.LEFT), SC.DIFFERENTIAL_DRIVE));
        return null;
    }

    @Override
    public Void visitTurnAction(TurnAction<Void> turnAction) {
        turnAction.getParam().getSpeed().accept(this);
        if ( turnAction.getParam().getDuration() != null ) {
            turnAction.getParam().getDuration().getValue().accept(this);
        }
        this.getBuilder(UsedHardwareBean.Builder.class).addUsedActor(new UsedActor(this.robotConfiguration.getFirstMotorPort(SC.LEFT), SC.DIFFERENTIAL_DRIVE));
        return null;
    }

    @Override
    public Void visitMotorDriveStopAction(MotorDriveStopAction<Void> stopAction) {
        if ( (this.robotConfiguration.getFirstMotorPort(SC.LEFT) != null) && (this.robotConfiguration.getFirstMotorPort(SC.RIGHT) != null) ) {
            this.getBuilder(UsedHardwareBean.Builder.class).addUsedActor(new UsedActor(this.robotConfiguration.getFirstMotorPort(SC.LEFT), SC.GEARED_MOTOR));
            this.getBuilder(UsedHardwareBean.Builder.class).addUsedActor(new UsedActor(this.robotConfiguration.getFirstMotorPort(SC.RIGHT), SC.GEARED_MOTOR));
        }
        return null;
    }

    @Override
    public Void visitSerialWriteAction(SerialWriteAction<Void> serialWriteAction) {
        serialWriteAction.getValue().accept(this);
        return null;
    }

    @Override
    public Void visitMoveNeckAction(MoveNeckAction <Void> moveNeckAction) {
        return null;
    }
}
