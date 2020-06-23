package com.company.projname.experiments;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Lamda {
    public static void main(String[] args) {
        test((s) -> 55);

        test((s) -> {
            return 55;
        });

        test2((s) -> 55);
        test2((s) -> 55.4);
        test2((s) -> 55.4F);

        int i = 0;
        final var j = new AtomicReference<>(0);
        test2((s) -> {
            // Impossible to change vars outside
//            i = 88;
            j.set(99);
            return 8;
        });

        // Pass statics
        test2(Lamda::function);

        // Pass methods
        final var o = new TestClass();
        test2(o::function);

//        final var f = TestClass2.function(TestClass2::<Integer, String>f, new Integer[] {1,2,3,4}, "");
//        System.out.println(f);

        final Function<String, TestClass2> ctorRef = TestClass2::new;
        final Function<Integer, Integer[]> arrCtorRef = Integer[]::new;

    }

    static int function(String s) { return 0; }

    static void test(FunctionalInterface f) {
        f.foo("wrwer");
    }

    static void test2(Function<String, ? extends Number> f) {
        f.apply("wrwer");
    }

    static class TestClass {
        int function(String s) { return 0; }
    }

    static class TestClass2 {
        TestClass2(String s) {}

        static <T, R> R function(BiFunction<R, T, R> f, T[] arr, R z) {
            var res = z;
            for(var i : arr) {
                res = f.apply(res, i);
            }
            return res;
        }

        static <T, R> R f(R s, T i) {
            throw new Error("qwe");
        }
    }
}

interface FunctionalInterface {
    int foo(String s);
}