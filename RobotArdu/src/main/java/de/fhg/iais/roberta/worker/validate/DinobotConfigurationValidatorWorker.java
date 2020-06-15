package de.fhg.iais.roberta.worker.validate;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DinobotConfigurationValidatorWorker extends ArduinoConfigurationValidatorWorker {
    public DinobotConfigurationValidatorWorker() {
        super(
            Stream
                .of("LED_BUILTIN")
                .collect(Collectors.toList()));
    }
}
