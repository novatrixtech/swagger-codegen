package io.swagger.codegen.languages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MercuriusGoServerCodegen extends GoServerCodegen {
    private static final Logger LOGGER = LoggerFactory.getLogger(MercuriusGoServerCodegen.class);

    public MercuriusGoServerCodegen() {
        super();
        outputFolder = "generated-code/mercurius-go";
        embeddedTemplateDir = templateDir = "mercurius-go-server";
        LOGGER.info("overriding folders");
    }

    
}