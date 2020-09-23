package de.fhg.iais.roberta.syntax.actors.arduino.dinobot;

import java.util.List;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.blockly.generated.Field;
import de.fhg.iais.roberta.syntax.BlockTypeContainer;
import de.fhg.iais.roberta.syntax.BlocklyBlockProperties;
import de.fhg.iais.roberta.syntax.BlocklyComment;
import de.fhg.iais.roberta.syntax.BlocklyConstants;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.action.Action;
import de.fhg.iais.roberta.transformer.AbstractJaxb2Ast;
import de.fhg.iais.roberta.transformer.Ast2JaxbHelper;
import de.fhg.iais.roberta.visitor.IVisitor;
import de.fhg.iais.roberta.visitor.hardware.IDinobotVisitor;

/**
 * This class represents the <b>move_neck</b> blocks from Blockly into the AST (abstract syntax tree). Object from this class will generate
 * code for moving the dinobot neck to a desired angle.<br/>
 * <br>
 * To create an instance from this class use the method {@link #make(angle, BlocklyBlockProperties, BlocklyComment)}.<br>
 */
public class MoveNeckAction<V> extends Action<V> {

    private final String angle;

    private MoveNeckAction(String angle, BlocklyBlockProperties properties, BlocklyComment comment) {
        super(BlockTypeContainer.getByName("MOVE_NECK"), properties, comment);
        this.angle = angle;
        setReadOnly();
    }

    /**
     * Creates instance of {@link MoveNeckAction}. This instance is read only and can not be modified.
     *
     * @param angle {@link angle} angle to move neck; must <b>not</b> be null,
     * @param properties of the block (see {@link BlocklyBlockProperties}),
     * @param comment added from the user,
     * @return read only object of class {@link MoveNeckAction}
     */
    private static <V> MoveNeckAction<V> make(String angle, BlocklyBlockProperties properties, BlocklyComment comment) {
        return new MoveNeckAction<>(angle, properties, comment);
    }

    public String getAngle() {
        return this.angle;
    }

    @Override
    public String toString() {
        return "MoveNeckAction";
    }

    @Override
    protected V acceptImpl(IVisitor<V> visitor) {
        return ((IDinobotVisitor<V>) visitor).visitMoveNeckAction(this);
    }

        /**
     * Transformation from JAXB object to corresponding AST object.
     *
     * @param block for transformation
     * @param helper class for making the transformation
     * @return corresponding AST object
     */
    public static <V> Phrase<V> jaxbToAst(Block block, AbstractJaxb2Ast<V> helper) {
        List<Field> fields = helper.extractFields(block, (short) 1);
        final String angle = helper.extractField(fields, BlocklyConstants.ANGLE);

        return MoveNeckAction
            .make(angle, helper.extractBlockProperties(block), helper.extractComment(block));
    }

    @Override
    public Block astToBlock() {
        Block jaxbDestination = new Block();
        Ast2JaxbHelper.setBasicProperties(this, jaxbDestination);
        Ast2JaxbHelper.addField(jaxbDestination, BlocklyConstants.ANGLE, this.angle);

        return jaxbDestination;
    }
}
