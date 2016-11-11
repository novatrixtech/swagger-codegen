package io.swagger.codegen.languages;

import io.swagger.codegen.*;
import io.swagger.models.Swagger;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class MercuriusGoServerCodegen extends DefaultCodegen implements CodegenConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MercuriusGoServerCodegen.class);

    protected String apiVersion = "1.0.0";
    protected int serverPort = 8080;

    public MercuriusGoServerCodegen() {
        super();

        // set the output folder here
        outputFolder = "generated-code/mecurius-go";

        /*
         * Models.  You can write model files using the modelTemplateFiles map.
         * if you want to create one template for file, you can do so here.
         * for multiple files for model, just put another entry in the `modelTemplateFiles` with
         * a different extension
         */
        modelTemplateFiles.put("model/model.mustache", ".go");

        /*
         * Api classes.  You can write classes for each Api file with the apiTemplateFiles map.
         * as with models, add multiple entries with different extensions for multiple files per
         * class
         */
        apiTemplateFiles.put("handler/handler.mustache", ".go");

        /*
         * Template Location.  This is the location which templates will be read from.  The generator
         * will use the resource stream to attempt to read the templates.
         */
        embeddedTemplateDir = templateDir = "mercurius-go-server";

        /*
         * Reserved words.  Override this with reserved words specific to your language
         */
        setReservedWordsLowerCase(
                Arrays.asList(
                        "break", "default", "func", "interface", "select",
                        "case", "defer", "go", "map", "struct",
                        "chan", "else", "goto", "package", "switch",
                        "const", "fallthrough", "if", "range", "type",
                        "continue", "for", "import", "return", "var", "error", "ApiResponse")
                // Added "error" as it's used so frequently that it may as well be a keyword
        );

        defaultIncludes = new HashSet<String>(
                Arrays.asList(
                        "map",
                        "array")
        );

        languageSpecificPrimitives = new HashSet<String>(
                Arrays.asList(
                        "string",
                        "bool",
                        "uint",
                        "uint32",
                        "uint64",
                        "int",
                        "int32",
                        "int64",
                        "float32",
                        "float64",
                        "complex64",
                        "complex128",
                        "rune",
                        "byte")
        );

        instantiationTypes.clear();
        /*instantiationTypes.put("array", "GoArray");
        instantiationTypes.put("map", "GoMap");*/

        typeMapping.clear();
        typeMapping.put("integer", "int32");
        typeMapping.put("long", "int64");
        typeMapping.put("number", "float32");
        typeMapping.put("float", "float32");
        typeMapping.put("double", "float64");
        typeMapping.put("boolean", "bool");
        typeMapping.put("string", "string");
        typeMapping.put("date", "time.Time");
        typeMapping.put("DateTime", "time.Time");
        typeMapping.put("password", "string");
        typeMapping.put("File", "*os.File");
        typeMapping.put("file", "*os.File");
        // map binary to string as a workaround
        // the correct solution is to use []byte
        typeMapping.put("binary", "string");
        typeMapping.put("ByteArray", "string");

        importMapping = new HashMap<String, String>();
        importMapping.put("time.Time", "time");
        importMapping.put("*os.File", "os");
        importMapping.put("os", "io/ioutil");

        cliOptions.clear();
        cliOptions.add(new CliOption(CodegenConstants.PACKAGE_NAME, "Go package name (convention: lowercase).")
                .defaultValue("swagger"));
        /*
         * Additional Properties.  These values can be passed to the templates and
         * are available in models, apis, and supporting files
         */
        additionalProperties.put("apiVersion", apiVersion);
        additionalProperties.put("serverPort", serverPort);
        /*
         * Supporting Files.  You can write single files for the generator with the
         * entire object tree available.  If the input file has a suffix of `.mustache
         * it will be processed by the template engine.  Otherwise, it will be copied
         */

        // main files
        supportingFiles.add(new SupportingFile("swagger.mustache", "", "swagger.yaml"));
        supportingFiles.add(new SupportingFile("main.mustache", "", "main.go"));
        writeOptional(outputFolder, new SupportingFile("README.mustache", "", "README.md"));

        // conf files
        supportingFiles.add(new SupportingFile("conf/app/app.mustache", "conf/app", "app.go"));
        supportingFiles.add(new SupportingFile("conf/app.ini", "conf", "app.ini"));
        supportingFiles.add(new SupportingFile("conf/env.go", "conf", "env.go"));
        supportingFiles.add(new SupportingFile("conf/dbconfig.go", "conf", "dbconfig.go"));

        // locale files
        supportingFiles.add(new SupportingFile("locale/locale_en-US.ini", "locale", "locale_en-US.ini"));
        supportingFiles.add(new SupportingFile("locale/locale_pt-BR.ini", "locale", "locale_pt-BR.ini"));

        // public files
        supportingFiles.add(new SupportingFile("public/static", "public", "static"));
        supportingFiles.add(new SupportingFile("public/templates/jade", "public/templates", "jade"));

        // repository files
        supportingFiles.add(new SupportingFile("repository/repository", "repository", "repository"));

        // lib files
        supportingFiles.add(new SupportingFile("lib/auth/token.mustache", "lib/auth", "token.go"));
        supportingFiles.add(new SupportingFile("lib/auth/verifier.mustache", "lib/auth", "verifier.go"));
        supportingFiles.add(new SupportingFile("lib/template/map_funcs.mustache", "lib/template", "map_funcs.go"));
        supportingFiles.add(new SupportingFile("lib/cache/types.mustache", "lib/cache", "types.go"));
        supportingFiles.add(new SupportingFile("lib/cache/selector.go", "lib/cache", "selector.go"));
        supportingFiles.add(new SupportingFile("lib/context/context.go", "lib/context", "context.go"));
        supportingFiles.add(new SupportingFile("lib/context/form.go", "lib/context", "form.go"));
        supportingFiles.add(new SupportingFile("lib/context/login.go", "lib/context", "login.go"));
    }

    /**
     * Configures the type of generator.
     *
     * @return the CodegenType for this generator
     * @see io.swagger.codegen.CodegenType
     */
    @Override
    public CodegenType getTag() {
        return CodegenType.SERVER;
    }

    /**
     * Configures a friendly name for the generator.  This will be used by the generator
     * to select the library with the -l flag.
     *
     * @return the friendly name for the generator
     */
    @Override
    public String getName() {
        return "mercurius-go-server";
    }

    /**
     * Returns human-friendly help for the generator.  Provide the consumer with help
     * tips, parameters here
     *
     * @return A string value for the help message
     */
    @Override
    public String getHelp() {
        return "Generates a Go server library using the swagger-tools project.  By default, " +
                "it will also generate service classes--which you can disable with the `-Dnoservice` environment variable.";
    }

    @Override
    public String toApiName(String name) {
        if (name.length() == 0) {
            return "DefaultController";
        }
        return initialCaps(name);
    }

    /**
     * Escapes a reserved word as defined in the `reservedWords` array. Handle escaping
     * those terms here.  This logic is only called if a variable matches the reseved words
     *
     * @return the escaped term
     */
    @Override
    public String escapeReservedWord(String name) {
        return "_" + name;  // add an underscore to the name
    }

    /**
     * Location to write api files.  You can use the apiPackage() as defined when the class is
     * instantiated
     */
    @Override
    public String apiFileFolder() {
        return outputFolder + File.separator + "handler";
    }

    @Override
    public String modelFileFolder() {
        return outputFolder + File.separator + "model";
    }

    @Override
    public String toModelName(String name) {
        // camelize the model name
        // phone_number => PhoneNumber
        return camelize(toModelFilename(name));
    }

    @Override
    public String toOperationId(String operationId) {
        // method name cannot use reserved keyword, e.g. return
        if (isReservedWord(operationId)) {
            LOGGER.warn(operationId + " (reserved word) cannot be used as method name. Renamed to " + camelize(sanitizeName("call_" + operationId)));
            operationId = "call_" + operationId;
        }

        return camelize(operationId);
    }

    @Override
    public String toModelFilename(String name) {
        if (!StringUtils.isEmpty(modelNamePrefix)) {
            name = modelNamePrefix + "_" + name;
        }

        if (!StringUtils.isEmpty(modelNameSuffix)) {
            name = name + "_" + modelNameSuffix;
        }

        name = sanitizeName(name);

        // model name cannot use reserved keyword, e.g. return
        if (isReservedWord(name)) {
            LOGGER.warn(name + " (reserved word) cannot be used as model name. Renamed to " + camelize("model_" + name));
            name = "model_" + name; // e.g. return => ModelReturn (after camelize)
        }

        return underscore(name);
    }

    @Override
    public String toApiFilename(String name) {
        // replace - with _ e.g. created-at => created_at
        name = name.replaceAll("-", "_");

        // e.g. PetApi.go => pet_api.go
        return underscore(name);
    }

    @Override
    public String escapeQuotationMark(String input) {
        // remove " to avoid code injection
        return input.replace("\"", "");
    }

    @Override
    public String escapeUnsafeCharacters(String input) {
        return input.replace("*/", "*_/").replace("/*", "/_*");
    }

    @Override
    public String toVarName(String name) {
        return camelize(name);
    }

    @Override
    public void processSwagger(Swagger swagger) {
        // rewrite handlers file
        String url = swagger.getExternalDocs().getUrl();
        url = url + "/lib/context";
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            String sCurrentLine;

            br = new BufferedReader(new FileReader(outputFolder + "/handler/default.go"));
            bw = new BufferedWriter(new FileWriter(outputFolder + "/handler/api.go"));
            boolean changeTime = false;
            while ((sCurrentLine = br.readLine()) != null) {
                if (changeTime) {
                    sCurrentLine = "\"" + url + "\"";
                    changeTime = false;
                }
                bw.write(sCurrentLine + "\n");
                if (sCurrentLine.contains("import (")) {
                    changeTime = true;
                }
            }
            File file = new File(outputFolder + "/handler/default.go");
            boolean delete = file.delete();
            if (delete) {
                System.out.println("delete old handler file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
                if (bw != null)bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        super.processSwagger(swagger);
    }
}