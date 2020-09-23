package de.fhg.iais.roberta.worker.validate;

import com.google.common.collect.ClassToInstanceMap;

import de.fhg.iais.roberta.bean.IProjectBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.validate.AbstractBrickValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.DinobotBrickValidatorVisitor;
import de.fhg.iais.roberta.worker.AbstractValidatorWorker;

public class DinobotConfigurationValidatorWorker extends AbstractValidatorWorker {
    
    @Override
    protected AbstractBrickValidatorVisitor getVisitor(Project project, ClassToInstanceMap<IProjectBean.IBuilder<?>> beanBuilders) {
        return new DinobotBrickValidatorVisitor(project.getConfigurationAst(), beanBuilders);
    }
}