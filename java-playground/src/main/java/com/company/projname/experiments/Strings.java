package com.company.projname.experiments;

public class Strings {
    public static void main(final String[] args) {
        final var s = "qwe";
        final var d = new String(new char[]{'q', 'w', 'e'});

        System.out.println("s == d: " + (s == d));
        System.out.println("s equals d: " + (s.equals(d)));
    }
}
