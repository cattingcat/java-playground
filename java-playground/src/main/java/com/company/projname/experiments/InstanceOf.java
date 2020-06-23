package com.company.projname.experiments;

import static java.lang.System.out;

public class InstanceOf {
    public static void main(String[] args) {
        final var a2 = new A2();
        final var b2 = new B2();
        final var c2 = new C2();
        final var a3 = new A3();
        final var c3 = new C3();

        out.println("c3 instanceof I2 : " + (c3 instanceof  I2));
        out.println("a3 instanceof A2 : " + (a3 instanceof  A2));

        // See run configuration flag [-ea]
        assert !a2.equals(b2) : "1";
        assert a2.equals(b2) : "2";
        assert 2 > 3 : "3";

        out.println("Fin");
    }
}

interface I2 {}
class A2 {}
class B2 {}

class C2 implements I2 {}
class A3 extends A2 {}

class C3 extends  C2 {}