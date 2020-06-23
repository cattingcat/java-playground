package com.company.projname.experiments;

public class Interfaces {
}


interface  MyInterface {
    void foo();
    private void bar() { }

    default void buz() {
        System.out.println(55);
    }
}

class Clazz implements MyInterface {
    @Override
    public void foo() {

    }

    // Optional override because of default impl
//    @Override
//    public void buz() {
//    }
}


interface Constants {
    int FIRST = 1;
}

class Clazz2 implements Constants {
    void foo() {
        System.out.println(FIRST);
    }
}



interface A {
    default void foo() {}
}

interface B {
    default void foo() {}
}

class Clazz3 implements A, B {
    @Override
    public void foo() {
        A.super.foo();
        B.super.foo();
    }
}