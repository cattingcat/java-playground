package com.company.projname.experiments;

import com.company.projname.webapp.Main;

public final class Classes {
    public static void main(final String[] args) {
        final var tst = new InnerClassTest(55);
        final var inner = tst.retInner();
        inner.kek();

        tst.methodInner();
    }
}


class FinalMethodClass {
    final void kek() {

    }

}

class InnerClassTest {
    final int outerField;

    InnerClassTest(final int field) {
        this.outerField = field;
    }

    public Inner retInner() {
        return new Inner();
    }

    public void methodInner() {
        final var i = 666;

        class InnerMethod {
            public void kek() {
                // access to outer class
                System.out.println(outerField + i);
            }
        }

        final var instance = new InnerMethod();
        instance.kek();
    }

    public class Inner {
        public void kek() {
            // access to outer class
            System.out.println(outerField);
        }
    }

    static class InnerStatic {
        void kek() {
            // No access to outer class obviously
//            System.out.println(outerField);
            // Can create inner clas instance only within instance methods
//            final var t = new Inner();
        }
    }
}


class VarArgTesst {
    void foo(int ... v) {
        final var l = v.length;
        System.out.println(l);
    }
}



class InheritanceTest {
    static class Class1 {
        void foo() {
            System.out.println("c1");
        }

        final void bar() {

        }
    }

    static class Class2 extends Class1 {
        void foo() {
            System.out.println("c2");
        }

        // Overide b default, so it is impossible
//        void bar() { }
    }



    public static void main(final String[] args) {
        final Class1 c = new Class2();
        c.foo();
    }
}

final class Box {
    double width;
    double height;
    double depth;

    double volume() {
        return width * height * depth;
    }

    void multiply(final double n) {
        width *= n;
        height *= n;
        depth *= n;
    }
}

class VisibilityTest {
    void foo() {
        final var b = new Box();
        b.multiply(6);

        // visible because of same package
        Syntax.testDefaultVisibility();

        // package modifier by default
//        Main.getConfigProperties();
    }
}