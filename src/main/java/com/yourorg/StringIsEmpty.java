/*
 * Copyright 2024 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yourorg;

import com.google.errorprone.refaster.annotation.AfterTemplate;
import com.google.errorprone.refaster.annotation.BeforeTemplate;
import org.openrewrite.java.template.RecipeDescriptor;

// TODO - This is a placeholder for a Refaster recipe. Implement the recipe by adding before and after annotated methods.
// The rule should replace calls to `String.length() == 0` with `String.isEmpty()`, as well as similar variants.
// You're done when all the tests in `StringIsEmptyTest` passes.
@RecipeDescriptor(
        name = "Standardize empty String checks",
        description = "Replace calls to `String.length() == 0` with `String.isEmpty()`."
)
public class StringIsEmpty {

    @BeforeTemplate
    boolean lengthEqualToZero(String s) {
        return s.length() == 0;
    }

    @BeforeTemplate
    boolean zeroEqualToLength(String s) {
        return 0 == s.length();
    }

    @BeforeTemplate
    boolean lengthLessThanOne(String s) {
        return s.length() < 1;
    }

    @BeforeTemplate
    boolean oneGreaterThanLength(String s) {
        return 1 > s.length();
    }

    @BeforeTemplate
    boolean equalsEmptyString(String s) {
        return s.equals("");
    }

    @BeforeTemplate
    boolean emptyStringEquals(String s) {
        return "".equals(s);
    }

    @AfterTemplate
    boolean isEmpty(String s) {
        return s.isEmpty();
    }
}
