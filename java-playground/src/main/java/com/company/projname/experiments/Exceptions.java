package com.company.projname.experiments;

public class Exceptions {
    public static void main(String[] args) {
        final var e1 = new IndexOutOfBoundsException();
        final var e2 = new ClassNotFoundException();
//        e1.addSuppressed(e2);
        e1.initCause(e2);
        throw e1;
    }


    void undeclaredError() {
        throw new Error();
    }

    void undeclaredRuntime() {
        throw new RuntimeException();
    }

    void foo() throws MyException {
        throw new MyException();
    }

    void multicatchTest() {
        try {

        } catch (final NullPointerException | IllegalArgumentException e) {

        }
    }
}


class MyException extends Exception {

}
