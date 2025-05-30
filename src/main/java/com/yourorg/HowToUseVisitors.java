package com.yourorg;

import org.openrewrite.ExecutionContext;
import org.openrewrite.NlsRewrite;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;

public class HowToUseVisitors extends Recipe {


    @Override
    public String getDisplayName() {
        return "How to use visitors";
    }

    @Override
    public String getDescription() {
        return "How to use visitors";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new JavaIsoVisitor<ExecutionContext>(){};
    }
}
