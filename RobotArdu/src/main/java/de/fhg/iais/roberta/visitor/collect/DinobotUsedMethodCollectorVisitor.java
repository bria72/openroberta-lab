package de.fhg.iais.roberta.visitor.collect;
import de.fhg.iais.roberta.bean.UsedMethodBean;

public class DinobotUsedMethodCollectorVisitor extends AbstractUsedMethodCollectorVisitor implements IDinobotCollectorVisitor {
    public DinobotUsedMethodCollectorVisitor(UsedMethodBean.Builder builder) {
        super(builder);
    }
}
