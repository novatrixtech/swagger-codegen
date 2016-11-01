# Swagger Code Generator Mercurius

# Getting Started

After clone codgen go to codegen folder and run
```
mvn clean package
```

# Generate Mercurius project
```
java -jar modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate -i <path to swagger file> -l mercurius-go-server -o <generated code output folder>
```