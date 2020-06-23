package com.company.projname.experiments;

import java.util.*;

public class Collections {
    public static void main(final String[] args) {
        testTreeSet();
        testQueue();

    }

    <T> void foo(final Collection<T> c) {

    }

    <T> void listFoo(final List<T> l) {

    }

    void testMyIterble() {
        for(var i : new MyIterable()) {

        }
    }

    void testArrayList() {
        final var al = new ArrayList<Integer>(50);
        al.add(55);

        final var arr = al.toArray();
    }

    void testLinkedList() {
        final var ll = new LinkedList<Integer>();
        ll.add(55);
        ll.addFirst(56);
        ll.addLast(54);
        ll.poll();
    }

    void testHashSet() {
        final var hs = new HashSet<Integer>();
        final var lhs = new LinkedHashSet<Integer>();

        lhs.add(55);

    }

    static void testTreeSet() {
        final var ts = new TreeSet<Integer>();

        ts.add(55);
        ts.add(1);
        ts.add(5);
        ts.add(2);
        ts.add(35);
        ts.add(7);
        ts.add(0);

        for(var i : ts) {
            System.out.println(i);
        }

    }

    static void testLinkedHashMap() {
        final var lhm = new LinkedHashMap<Integer, String>();
        lhm.put(55, "");
    }


    static void testQueue() {
        final var q = new ArrayDeque<Integer>();
        final var qq = (ArrayDeque) q;
        qq.add("eqweq");
        final var cq = java.util.Collections.checkedQueue(qq, Integer.class);
        cq.add("eqweq");
    }
}

final class MyIterable implements Iterable<Integer> {
    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator();
    }

    private final class MyIterator implements Iterator<Integer> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Integer next() {
            return null;
        }
    }
}