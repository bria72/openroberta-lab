package de.fhg.iais.roberta.visitor.validate;

import com.google.common.collect.ClassToInstanceMap;

import de.fhg.iais.roberta.bean.IProjectBean;
import de.fhg.iais.roberta.components.ConfigurationAst;
import de.fhg.iais.roberta.components.ConfigurationComponent;
import de.fhg.iais.roberta.syntax.SC;
import de.fhg.iais.roberta.syntax.action.generic.PinWriteValueAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorOnAction;
import de.fhg.iais.roberta.syntax.action.serial.SerialWriteAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayNoteAction;
import de.fhg.iais.roberta.syntax.action.sound.ToneAction;
import de.fhg.iais.roberta.syntax.actors.arduino.RelayAction;
import de.fhg.iais.roberta.syntax.actors.arduino.dinobot.MoveNeckAction;
import de.fhg.iais.roberta.syntax.lang.functions.GetSubFunct;
import de.fhg.iais.roberta.syntax.lang.functions.IndexOfFunct;
import de.fhg.iais.roberta.syntax.lang.functions.LengthOfIsEmptyFunct;
import de.fhg.iais.roberta.syntax.lang.functions.ListGetIndex;
import de.fhg.iais.roberta.syntax.lang.functions.ListSetIndex;
import de.fhg.iais.roberta.syntax.lang.functions.MathOnListFunct;
import de.fhg.iais.roberta.syntax.sensor.Sensor;
import de.fhg.iais.roberta.syntax.sensor.generic.GetSampleSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.InfraredSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.PinGetValueSensor;
import de.fhg.iais.roberta.typecheck.NepoInfo;
import de.fhg.iais.roberta.visitor.hardware.IDinobotVisitor;
import de.fhg.iais.roberta.visitor.hardware.sensor.ISensorVisitor;

public final class DinobotBrickValidatorVisitor extends AbstractBrickValidatorVisitor implements ISensorVisitor<Void>, IDinobotVisitor<Void> {

    public DinobotBrickValidatorVisitor(ConfigurationAst brickConfiguration, ClassToInstanceMap<IProjectBean.IBuilder<?>> beanBuilders) {
        super(brickConfiguration, beanBuilders);
    }

    @Override
    public Void visitMoveNeckAction(MoveNeckAction<Void> moveNeckAction){
        return null;
    }

    @Override
    public Void visitGetSampleSensor(GetSampleSensor<Void> sensorGetSample) {
        Sensor<Void> sensor = sensorGetSample.getSensor();
        // TODO remove once infrared & rfid libraries are supported for unowifirev2
        if (sensor.getKind().hasName("INFRARED_SENSING") || sensor.getKind().hasName("RFID_SENSING")) {
            sensorGetSample.addInfo(NepoInfo.warning("BLOCK_NOT_SUPPORTED"));
        }
        return super.visitGetSampleSensor(sensorGetSample);
    }

    @Override
    public Void visitInfraredSensor(InfraredSensor<Void> infraredSensor) {
        if (!robotConfiguration.getRobotName().equals("unowifirev2")) { // TODO remove once infrared library is supported for unowifirev2
            checkSensorPort(infraredSensor);
        } else {
            infraredSensor.addInfo(NepoInfo.warning("BLOCK_NOT_SUPPORTED"));
        }
        return null;
    }

    @Override
    public Void visitMotorOnAction(MotorOnAction<Void> motorOnAction) {
        if ( motorOnAction.getInfos().getErrorCount() == 0 ) {
            ConfigurationComponent usedConfigurationBlock = this.robotConfiguration.optConfigurationComponent(motorOnAction.getUserDefinedPort());
            boolean duration = motorOnAction.getParam().getDuration() != null;
            if ( usedConfigurationBlock == null ) {
                motorOnAction.addInfo(NepoInfo.error("CONFIGURATION_ERROR_ACTOR_MISSING"));
                this.errorCount++;
            } else if ( usedConfigurationBlock.getComponentType().equals(SC.OTHER) && duration ) {
                motorOnAction.addInfo(NepoInfo.error("CONFIGURATION_ERROR_OTHER_NOT_SUPPORTED"));
            }
        }
        return null;
    }

    @Override
    public Void visitPlayNoteAction(PlayNoteAction<Void> playNoteAction) {
        if ( playNoteAction.getInfos().getErrorCount() == 0 ) {
            ConfigurationComponent usedConfigurationBlock = this.robotConfiguration.optConfigurationComponent(playNoteAction.getPort());
            if ( usedConfigurationBlock == null ) {
                playNoteAction.addInfo(NepoInfo.error("CONFIGURATION_ERROR_ACTOR_MISSING"));
                this.errorCount++;
            }
        }
        return null;
    }

    @Override
    public Void visitToneAction(ToneAction<Void> toneAction) {
        if ( toneAction.getInfos().getErrorCount() == 0 ) {
            ConfigurationComponent usedConfigurationBlock = this.robotConfiguration.optConfigurationComponent(toneAction.getPort());
            if ( usedConfigurationBlock == null ) {
                toneAction.addInfo(NepoInfo.error("CONFIGURATION_ERROR_ACTOR_MISSING"));
                this.errorCount++;
            }
        }
        return null;
    }

    //@Override
    public Void visitRelayAction(RelayAction<Void> relayAction) {
        if ( relayAction.getInfos().getErrorCount() == 0 ) {
            ConfigurationComponent usedConfigurationBlock = this.robotConfiguration.optConfigurationComponent(relayAction.getPort());
            if ( usedConfigurationBlock == null ) {
                relayAction.addInfo(NepoInfo.error("CONFIGURATION_ERROR_ACTOR_MISSING"));
                this.errorCount++;
            }
        }
        return null;
    }

    @Override
    public Void visitPinGetValueSensor(PinGetValueSensor<Void> pinGetValueSensor) {
        checkSensorPort(pinGetValueSensor);
        return null;
    }

    @Override
    public Void visitPinWriteValueAction(PinWriteValueAction<Void> pinWriteValueAction) {
        if ( pinWriteValueAction.getInfos().getErrorCount() == 0 ) {
            ConfigurationComponent usedConfigurationBlock = this.robotConfiguration.optConfigurationComponent(pinWriteValueAction.getPort());
            if ( usedConfigurationBlock == null ) {
                pinWriteValueAction.addInfo(NepoInfo.error("CONFIGURATION_ERROR_ACTOR_MISSING"));
                this.errorCount++;
            }
        }
        return null;
    }

    @Override
    public Void visitSerialWriteAction(SerialWriteAction<Void> serialWriteAction) {
        serialWriteAction.getValue().accept(this);
        return null;
    }

    @Override
    public Void visitIndexOfFunct(IndexOfFunct<Void> indexOfFunct) {
        if ( indexOfFunct.getParam().get(0).toString().contains("ListCreate ") ) {
            indexOfFunct.addInfo(NepoInfo.error("BLOCK_USED_INCORRECTLY"));
            this.errorCount++;
        }
        return super.visitIndexOfFunct(indexOfFunct);
    }

    @Override
    public Void visitListGetIndex(ListGetIndex<Void> listGetIndex) {
        if ( listGetIndex.getParam().get(0).toString().contains("ListCreate ") ) {
            listGetIndex.addInfo(NepoInfo.error("BLOCK_USED_INCORRECTLY"));
            this.errorCount++;
        }
        return super.visitListGetIndex(listGetIndex);
    }

    @Override
    public Void visitListSetIndex(ListSetIndex<Void> listSetIndex) {
        if ( listSetIndex.getParam().get(0).toString().contains("ListCreate ") ) {
            listSetIndex.addInfo(NepoInfo.error("BLOCK_USED_INCORRECTLY"));
            this.errorCount++;
        }
        return super.visitListSetIndex(listSetIndex);
    }

    @Override
    public Void visitLengthOfIsEmptyFunct(LengthOfIsEmptyFunct<Void> lengthOfIsEmptyFunct) {
        if ( lengthOfIsEmptyFunct.getParam().get(0).toString().contains("ListCreate ") ) {
            lengthOfIsEmptyFunct.addInfo(NepoInfo.error("BLOCK_USED_INCORRECTLY"));
            this.errorCount++;
        }
        return super.visitLengthOfIsEmptyFunct(lengthOfIsEmptyFunct);
    }

    @Override
    public Void visitMathOnListFunct(MathOnListFunct<Void> mathOnListFunct) {
        if ( mathOnListFunct.getParam().get(0).toString().contains("ListCreate ") ) {
            mathOnListFunct.addInfo(NepoInfo.error("BLOCK_USED_INCORRECTLY"));
            this.errorCount++;
        }
        return super.visitMathOnListFunct(mathOnListFunct);
    }

    @Override
    public Void visitGetSubFunct(GetSubFunct<Void> getSubFunct) {
        if ( getSubFunct.getParam().get(0).toString().contains("ListCreate ") ) {
            getSubFunct.addInfo(NepoInfo.error("BLOCK_USED_INCORRECTLY"));
            this.errorCount++;
        }
        return super.visitGetSubFunct(getSubFunct);
    }
}
