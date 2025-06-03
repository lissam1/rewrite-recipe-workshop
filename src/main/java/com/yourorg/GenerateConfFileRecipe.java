package com.yourorg;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jspecify.annotations.Nullable;
import org.openrewrite.*;
import org.openrewrite.marker.Markers;
import org.openrewrite.text.PlainText;
import org.openrewrite.yaml.YamlIsoVisitor;
import org.openrewrite.yaml.tree.Yaml;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;

@Value
@EqualsAndHashCode(callSuper = false)
public class GenerateConfFileRecipe extends ScanningRecipe<GenerateConfFileRecipe.Accumulator> {

    @Override
    public String getDisplayName() {
        return "Generate configuration file";
    }

    @Override
    public String getDescription() {
        return "Reads micronaut-cli.yml, extracts 'sourceLanguage', and writes 'Hello {value}' to projectgen.conf.";
    }

    public static class Accumulator {
        @Nullable String sourceLanguage = null;
    }

    @Override
    public Accumulator getInitialValue(ExecutionContext ctx) {
        return new Accumulator();
    }

    //get the yaml file
    @Override
    public TreeVisitor<?, ExecutionContext> getScanner(Accumulator acc) {
        return new YamlIsoVisitor<ExecutionContext>() {
            @Override
            public Yaml.Document visitDocument(Yaml.Document document, ExecutionContext ctx) {
                SourceFile sourceFile = getCursor().firstEnclosing(SourceFile.class);
                if (sourceFile != null) {
                    Path sourcePath = sourceFile.getSourcePath();
                    if (sourcePath != null && "micronaut-cli.yml".equals(sourcePath.getFileName().toString())) {
                        return super.visitDocument(document, ctx);
                    }
                }
                return document;
            }

            //extract the value from sourceLanguage
            @Override
            public Yaml.Mapping.Entry visitMappingEntry(Yaml.Mapping.Entry entry, ExecutionContext ctx) {
                if (entry.getKey() instanceof Yaml.Scalar) {
                    Yaml.Scalar key = (Yaml.Scalar) entry.getKey();
                    if ("sourceLanguage".equals(key.getValue())) {
                        if (entry.getValue() instanceof Yaml.Scalar) {
                            Yaml.Scalar value = (Yaml.Scalar) entry.getValue();
                            acc.sourceLanguage = value.getValue();
                        }
                    }
                }
                return super.visitMappingEntry(entry, ctx);
            }
        };
    }

    @Override
    public Collection<SourceFile> generate(Accumulator acc, ExecutionContext ctx) {
        if (acc.sourceLanguage == null) {
            return Collections.emptyList();
        }

        // Generate projectgen.conf with Hello {sourceLanguage value}
        String content = "Hello " + acc.sourceLanguage;

        PlainText plainText = PlainText.builder()
                .text(content)
                .sourcePath(Paths.get("projectgen.conf"))
                .markers(Markers.EMPTY)
                .build();

        return Collections.singletonList(plainText);
    }

}