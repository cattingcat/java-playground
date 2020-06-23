package com.company.projname.experiments;

public class OverloadedCtor {

    OverloadedCtor(String s, Integer i) {}

    OverloadedCtor(String s) {
        this(s, 0);
    }
}
