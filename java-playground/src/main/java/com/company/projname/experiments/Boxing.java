package com.company.projname.experiments;

public class Boxing {
    public static void main(String[] agrs) {
        final byte b = Integer.valueOf(5).byteValue();

//        final int i = null;
        final Integer i = null;
        int j = i; // NullPtr
    }
}
