package de.fhg.iais.roberta.worker.collect;
import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.visitor.collect.ICollectorVisitor;
import de.fhg.iais.roberta.visitor.collect.DinobotUsedMethodCollectorVisitor;
import de.fhg.iais.roberta.worker.AbstractUsedMethodCollectorWorker;

public class DinobotUsedMethodCollectorWorker extends AbstractUsedMethodCollectorWorker {
    @Override
    protected ICollectorVisitor getVisitor(UsedMethodBean.Builder builder) {
        return new DinobotUsedMethodCollectorVisitor(builder);
    }
}
