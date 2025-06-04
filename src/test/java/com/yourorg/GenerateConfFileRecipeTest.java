package com.yourorg;

import org.junit.jupiter.api.Test;
import org.openrewrite.DocumentExample;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import java.nio.file.Path;

import static org.openrewrite.yaml.Assertions.yaml;
import static org.openrewrite.test.SourceSpecs.text;

class GenerateConfFileRecipeTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new GenerateConfFileRecipe());
    }

//    @DocumentExample
//    @Test
//    void generatesProjectgenConfFromMicronautCliYml() {
//        rewriteRun(
//                yaml(
//                        """
//                        sourceLanguage: java
//                        """,
//                        spec -> spec.path(Path.of("micronaut-cli.yml"))
//                ),
//                text(
//                        null,
//                        """
//                        Hello java
//                        """,
//                        spec -> spec.path(Path.of("projectgen.conf"))
//                )
//        );
//    }

    @Test
    void doesNotGenerateWhenSourceLanguageMissing() {
        rewriteRun(
                yaml(
                        //language=yaml
                        """
                                applicationType: cli
                                defaultPackage: io.micronaut.projectgen.demo.mavenhelloworld
                                testFramework: junit
                                buildTool: gradle_kotlin
                                features: [app-name, gradle, http-client-test, java, junit, logback, micronaut-build, picocli, picocli-java-application, picocli-junit, properties, readme, serialization-jackson, shade]
                                """,
                        spec -> spec.path(Path.of("micronaut-cli.yml"))
                )
        );
    }
}