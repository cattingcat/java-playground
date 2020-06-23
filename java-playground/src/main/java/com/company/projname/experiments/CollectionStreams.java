package com.company.projname.experiments;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CollectionStreams {
    public static void main(final String[] args) {
        final var arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(2);
        arr.add(1);
        final var stream = arr.stream().map((i) -> i + 1);
        final var set = stream.collect(Collectors.toSet());
    }
}
