package com.company.projname.experiments;

import java.lang.annotation.*;
import java.lang.reflect.Method;

public class Annot {
    public static void main(String[] args) {
        final var o = new TestClass();
        final var clazz = o.getClass();


        final var r = clazz.getAnnotatedInterfaces();
        for(var i : r) {
            System.out.println(i.getType().getTypeName());
        }

        final var as = clazz.getAnnotations();
        for(var i : as) {
            System.out.println(i.annotationType().getName());
        }

        { // Single annot
            final var a = clazz.getAnnotation(MyAnnotRuntime.class);
            final var isPresent = clazz.isAnnotationPresent(MyAnnotRuntime.class);
            if(isPresent) {
                System.out.println(a.str() + " " + isPresent);
            } else {
                System.out.println("No single annot ");
            }
        }

        { // Multi annot
            final var a = clazz.getAnnotation(MyRepeatableAnnotRuntime.class);
            final var isPresent = clazz.isAnnotationPresent(MyRepeatableAnnotRuntime.class);
            System.out.println(a.value().length + " " + isPresent);
        }

        try {
            final var m1 = clazz.getMethod("foo");
            System.out.println(m1);

            final var m2 = clazz.getMethod("foo", String.class, int.class);
            System.out.println(m2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }



        final var subclazz = TestSubClass.class;
        final var isSubClassAnnotPresent = subclazz.isAnnotationPresent(MyAnnotRuntime.class);
        // Depends on Inherited annot on annot
        System.out.println("Subclass annot present? " + isSubClassAnnotPresent);

    }

    @MyAnnotRuntime(str = "kek", val = 4)
    void testFoo() { }
}


interface A1 {}
interface B1 {}

@MyAnnotRuntime(str = "runtime annot", val = 5)
@MyAnnotRuntime(str = "runtime annot", val = 5)
@MyAnnotRuntime(str = "runtime annot", val = 5)
@MyAnnotClass(str = "", val = 5)
class TestClass implements A1, B1 {
    public void foo() {}
    public void foo(String s, int i) {}
}

class TestSubClass extends  TestClass {}

// Source - compile time
// Class - Compile time and class files
// Runtime  - via reflection
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Repeatable(MyRepeatableAnnotRuntime.class)
@interface MyAnnotRuntime {
    String str();
    int val() default 55;
}

@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@interface MyRepeatableAnnotRuntime {
    MyAnnotRuntime[] value();
}


@Retention(value = RetentionPolicy.CLASS)
@interface MyAnnotClass {
    String str();
    int val();
}