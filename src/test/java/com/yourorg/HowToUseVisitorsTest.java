package com.yourorg;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

//public class HowToUseVisitorsTest implements RewriteTest {
//
//    @Override
//    public void defaults(RecipeSpec spec){
//        spec.recipe(new HowToUseVisitors());
//    }
//
//    @Test
//    void sumAllTheIntegers(){
//        rewriteRun(
//          java(
//            """
//              class Test{
//                    int test(){
//                    return 21+21;
//                    }
//              }
//              """,
//            """
//                class Test{
//                    int itChanged(){
//                    return 21+21;
//                    }
//              }
//              """
//          )
//        );
//    }
//}
