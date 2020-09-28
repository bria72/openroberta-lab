package de.fhg.iais.roberta.visitor.codegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ClassToInstanceMap;

import de.fhg.iais.roberta.bean.IProjectBean;
import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.components.Category;
import de.fhg.iais.roberta.components.ConfigurationAst;
import de.fhg.iais.roberta.components.UsedActor;
import de.fhg.iais.roberta.components.UsedSensor;
import de.fhg.iais.roberta.mode.action.DriveDirection;
import de.fhg.iais.roberta.mode.action.TurnDirection;
import de.fhg.iais.roberta.syntax.MotorDuration;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.SC;

import de.fhg.iais.roberta.syntax.action.motor.differential.CurveAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.DriveAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.MotorDriveStopAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.TurnAction;
import de.fhg.iais.roberta.syntax.action.serial.SerialWriteAction;
import de.fhg.iais.roberta.syntax.action.sound.VolumeAction;
import de.fhg.iais.roberta.syntax.actors.arduino.dinobot.MoveNeckAction;
import de.fhg.iais.roberta.syntax.lang.blocksequence.MainTask;
import de.fhg.iais.roberta.syntax.lang.expr.ColorConst;
import de.fhg.iais.roberta.syntax.lang.expr.EmptyExpr;
import de.fhg.iais.roberta.syntax.lang.expr.Expr;
import de.fhg.iais.roberta.syntax.lang.expr.RgbColor;
import de.fhg.iais.roberta.syntax.lang.expr.Var;
import de.fhg.iais.roberta.syntax.sensor.generic.InfraredSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.KeysSensor;
import de.fhg.iais.roberta.typecheck.BlocklyType;
import de.fhg.iais.roberta.util.dbc.DbcException;
import de.fhg.iais.roberta.visitor.IVisitor;
import de.fhg.iais.roberta.visitor.hardware.IDinobotVisitor;

/**
 * This class is implementing {@link IVisitor}. All methods are implemented and they append a hussentation of a phrase to a StringBuilder. <b>This
 * representation is correct C code for Arduino.</b> <br>
 */
public final class DinobotCppVisitor extends AbstractCommonArduinoCppVisitor implements IDinobotVisitor<Void> {

    private final HashMap<String, Integer> imageList = new HashMap<String, Integer>();

    /**
     * Initialize the C++ code generator visitor.
     *
     * @param brickConfiguration hardware configuration of the brick
     * @param phrases to generate the code from
     */
    public DinobotCppVisitor(List<List<Phrase<Void>>> phrases, ConfigurationAst brickConfiguration, ClassToInstanceMap<IProjectBean> beans) {
        super(phrases, brickConfiguration, beans);
    }



    @Override
    public Void visitEmptyExpr(EmptyExpr<Void> emptyExpr) {
        switch ( emptyExpr.getDefVal() ) {
            case STRING:
                this.sb.append("\"\"");
                break;
            case BOOLEAN:
                this.sb.append("true");
                break;
            case NUMBER:
            case NUMBER_INT:
                this.sb.append("0");
                break;
            case ARRAY:
                break;
            case NULL:
                break;
            case COLOR:
                this.sb.append("RGB(255, 255, 255)");
                break;
            case IMAGE:
                this.sb.append("{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}");
                break;
            default:
                this.sb.append("NULL");
                break;
        }
        return null;
    }

    @Override
    protected String getLanguageVarTypeFromBlocklyType(BlocklyType type) {
        switch ( type ) {
            case ANY:
            case COMPARABLE:
            case ADDABLE:
            case NULL:
            case REF:
            case PRIM:
            case NOTHING:
            case CAPTURED_TYPE:
            case R:
            case S:
            case T:
                return "";
            case ARRAY:
                return "std::list<double>";
            case ARRAY_NUMBER:
                return "std::list<double>";
            case ARRAY_STRING:
                return "std::list<String>";
            case ARRAY_BOOLEAN:
                return "std::list<bool>";
            case ARRAY_COLOUR:
                return "std::list<unsigned int>";
            case ARRAY_IMAGE:
                return "std::list<std::vector<uint8_t>>";
            case BOOLEAN:
                return "bool";
            case NUMBER:
                return "double";
            case NUMBER_INT:
                return "int";
            case STRING:
                return "String";
            case VOID:
                return "void";
            case COLOR:
                return "unsigned int";
            case CONNECTION:
                return "int";
            case IMAGE:
                return "std::vector<uint8_t>";
            default:
                throw new IllegalArgumentException("unhandled type");
        }
    }

    @Override
    public Void visitMoveNeckAction(MoveNeckAction<Void> moveNeckAction){
        this.sb.append("dummy_code(");
        this.sb.append(moveNeckAction.getAngle());
        this.sb.append(");");
        return null;
    }

    @Override
    public Void visitDriveAction(DriveAction<Void> driveAction) {
        final MotorDuration<Void> duration = driveAction.getParam().getDuration();
        this.sb.append("_dBot.drive(");
        driveAction.getParam().getSpeed().accept(this);
        this.sb.append(", ");
        if (driveAction.getDirection() == DriveDirection.FOREWARD) { // where do we get these methods from, i.e which file has the info that for eg driveAction has methods getDirection,etc as I am unsure if the method for getAngle is correct
            this.sb.append("1"); 
        } else {
            this.sb.append("0"); 
        }
        
        if ( duration != null ) {
            this.sb.append(", ");
            duration.getValue().accept(this);
        }
        // if (driveAction.getParam.getSpeed() != null) { // Im a bit confused here as to how to get the speed and angle values hence I just copied the code and assumed that getParam has a getAngle method
        //     // I tried looking at other files for this info but couldnt find it (the driveAction class/type)
        //     this.sb.append(driveAction.getParam.getSpeed());
        //     // should i add th angle param and set it to 0?
        // }
            
        this.sb.append(");");
          /**
        driveAction.getParam().getSpeed().accept(this);
        if ( duration != null ) {
            this.sb.append(", ");
            duration.getValue().accept(this);
        }
        this.sb.append(");");
            */
        return null;
    }

    @Override
    public Void visitCurveAction(CurveAction<Void> curveAction) {
        final MotorDuration<Void> duration = curveAction.getParamLeft().getDuration();
        this.sb.append("_dbot.steer(");
        curveAction.getParamLeft().getSpeed().accept(this);
        this.sb.append(", ");
        curveAction.getParamRight().getSpeed().accept(this);
        this.sb.append(", ").append(curveAction.getDirection() == DriveDirection.FOREWARD ? 1 : 0);
        if ( duration != null ) {
            this.sb.append(", ");
            duration.getValue().accept(this);
        }
        this.sb.append(");");
        return null;
    }
    
    //updated neckAction Method
    @Override
    public Void visitNeckAction(NeckAction<Void> neckAction) {
        this.sb.append("_dbot.moveNeck(");
        neckAction.getAngle().accept(this); // assuming neckAction obj and has method getAngle
        this.sb.append(");");
        return null
    }
    @Override
    public Void visitTurnAction(TurnAction<Void> turnAction) {
        final MotorDuration<Void> duration = turnAction.getParam().getDuration();

        if(turnAction.getDirection() == TurnDirection.LEFT ){
        this.sb.append("_dbot.Turn_Left(");
        } else{
            this.sb.append("_dbot.Turn_Right(");
        }

        turnAction.getParam().getSpeed().accept(this);
        if ( duration != null ) {
            this.sb.append(", ");
            duration.getValue().accept(this);
        }
        this.sb.append(");");
        return null;
    }

    @Override
    public Void visitMotorDriveStopAction(MotorDriveStopAction<Void> stopAction) {
        for ( final UsedActor actor : this.getBean(UsedHardwareBean.class).getUsedActors() ) {
            if ( actor.getType().equals(SC.DIFFERENTIAL_DRIVE) ) {
                this.sb.append("_dBot.Stop();");
                break;
            }
        }
        return null;
    }
    

    @Override
    public Void visitInfraredSensor(InfraredSensor<Void> infraredSensor) {
        this.sb.append("_dBot.checkObstacle()");
        return null;
    }
 
  

    @Override
    public Void visitMainTask(MainTask<Void> mainTask) {
        predefineImages();
        mainTask.getVariables().accept(this);
        if ( !mainTask.getVariables().toString().equals("") ) {
            nlIndent();
        }
        nlIndent();
        //generateConfigurationVariables();
        if ( this.getBean(UsedHardwareBean.class).isSensorUsed(SC.TIMER) ) {
            this.sb.append("unsigned long __time = millis();");
            nlIndent();
        }
        long numberConf =
            this.programPhrases
                .stream()
                .filter(phrase -> phrase.getKind().getCategory() == Category.METHOD && !phrase.getKind().hasName("METHOD_CALL"))
                .count();
        if ( (this.configuration.getConfigurationComponents().isEmpty() || this.getBean(UsedHardwareBean.class).isSensorUsed(SC.TIMER)) && numberConf == 0 ) {
            nlIndent();
        }
        generateUserDefinedMethods();
        if ( numberConf != 0 ) {
            nlIndent();
        }
        this.sb.append("void setup()");
        nlIndent();
        this.sb.append("{");
        incrIndentation();
        nlIndent();
        this.sb.append("Serial.begin(9600); ");
  
        nlIndent();
        generateUsedVars();
       
        this.sb.delete(this.sb.lastIndexOf("\n"), this.sb.length());
        decrIndentation();
        nlIndent();
        this.sb.append("}");
        nlIndent();
        return null;
    }

    private void predefineImages() {
        Map<String, String[][]> usedIDImages = this.getBean(UsedHardwareBean.class).getUsedIDImages();
        int i = 0;
        for ( Map.Entry<String, String[][]> entry : usedIDImages.entrySet() ) {
            this.sb.append("const std::vector<uint8_t> __ledMatrix").append(i).append(" = ");
            writeImage(entry.getValue());
            this.sb.append(";");
            nlIndent();
            this.imageList.put(entry.getKey(), i);
            i++;
        }
    }

    @Override
    protected void generateProgramPrefix(boolean withWrapping) {
        if ( !withWrapping ) {
            return;
        } else {
            decrIndentation();
        }
        this.sb.append("// This file is automatically generated by the Open Roberta Lab.");
        nlIndent();
        nlIndent();
        this.sb.append("#define ANALOG2PERCENT 0.0978");
        nlIndent();
        nlIndent();
        this.sb.append("#include <MeMCore.h>");
        nlIndent();
        this.sb.append("#include <DinoDrive.h>");
        nlIndent();
        this.sb.append("#include <NEPODefs.h>");
        nlIndent();
        generateSensors();
        generateActors();
        nlIndent();
        nlIndent();
        super.generateProgramPrefix(withWrapping);
    }

    private void generateSensors() {
        LinkedHashSet<Integer> usedInfraredPort = new LinkedHashSet<>();
        for ( final UsedSensor usedSensor : this.getBean(UsedHardwareBean.class).getUsedSensors() ) {
            switch ( usedSensor.getType() ) {
                case SC.INFRARED:
                    int port = Integer.parseInt(usedSensor.getPort());
                    if ( !usedInfraredPort.contains(port) ) {
                        nlIndent();
                        this.sb.append("MeDrive __meDrive" + usedSensor.getPort() + "(PORT_" + usedSensor.getPort() + ");");
                    }
                    usedInfraredPort.add(port);
                    break;
                case SC.TIMER:
                    break;
                default:
                    throw new DbcException("Sensor is not supported! " + usedSensor.getType());
            }
        }
    }

    private void generateActors() {
        for ( final UsedActor usedActor : this.getBean(UsedHardwareBean.class).getUsedActors() ) {
            switch ( usedActor.getType() ) {
                case SC.DIFFERENTIAL_DRIVE:
                    nlIndent();
                    this.sb
                        .append(
                            "MeDrive _dBot();");
                                // + this.configuration.getFirstMotorPort(SC.LEFT)
                                // + ", M"
                                // + this.configuration.getFirstMotorPort(SC.RIGHT)
                                // + ");");
                    break;
                default:
                    throw new DbcException("Actor is not supported! " + usedActor.getType());
            }
        }
    }

    @Override
    public Void visitSerialWriteAction(SerialWriteAction<Void> serialWriteAction) {
        this.sb.append("Serial.println(");
        serialWriteAction.getValue().accept(this);
        this.sb.append(");");
        return null;
    }    

    private void writeImage(String[][] image) {
        this.sb.append("{");
        for ( int i = 0; i < 16; i++ ) {
            this.sb.append("0x");
            int hex = 0;
            for ( int j = 0; j < 8; j++ ) {
                String pixel = image[i][j].trim();
                if ( pixel.equals("#") ) {
                    hex += Math.pow(2, j);
                }
            }
            this.sb.append(Integer.toHexString(hex));
            if ( i < 15 ) {
                this.sb.append(", ");
            }
        }
        this.sb.append("}");
    }

    private String capitalizeFirstLetter(String original) {
        if ( original == null || original.length() == 0 ) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
    }
}
