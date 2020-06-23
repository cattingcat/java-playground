package com.company.projname.experiments;

public class Generics {
    public static void main(String[] args) {
        final var tmp = new Gen<Integer>(5);
        System.out.println(tmp.getClass().getName());
    }
}


class Gen<T> {
    private final T obj;

    Gen(final T o) {
        obj = o;
    }
}


class Avr<T extends Number & Comparable<T>> {
    private final T[] vals;

    Avr(T... vals) {
        this.vals = vals;
    }

    double calc() {
        var s = 0.0;
        for(var i : vals) {
            s += i.doubleValue();
        }

        return s / vals.length;
    }

    boolean sameAvg(Avr<?> other) {
        return calc() == other.calc();
    }
}


class TwoDim {
    int x;
    int y;
}
class ThreeDim extends TwoDim {
    int z;
}

class Coords<T extends TwoDim> {
    T[] coords;
}

class CoordsTest {
    public static void main(String[] args) {

    }

    static void print(Coords<?> cs) {
        for(var i : cs.coords) {
            System.out.println(i.x);
            System.out.println(i.y);
        }
    }

    static void print2(Coords<? extends ThreeDim> cs) {
        for(var i : cs.coords) {
            System.out.println(i.x);
            System.out.println(i.y);
            System.out.println(i.z);
        }
    }
}



class GenericMethod {
    public static void main(String[] args) {
        System.out.println(isIn(5, new Integer[] {1, 2, 3, 4, 5}));
    }

    // Generic ctor
    <T> GenericMethod(T[] ts) { }

    static <T extends Comparable<T>, V extends T> boolean isIn (T a, V[] arr) {
        for(var i : arr) {
            if(a.equals(i)) return true;
        }

        return false;
    }
}

interface MinMax<T extends Comparable<T>> {
    T min();
    T max();
}


class InstanceOfGenerics {
    public static void main(String[] args) {
        final Object o1 = new SubClass();
        final Object o2 = new SubClass2();
        final Gen<Integer> g = new Gen<>(); // type inference with <>

        System.out.println(o1 instanceof  Gen<?>);
        // Impossible. No generic info at runtime
//        System.out.println(o2 instanceof  Gen<String>);
    }


    static class Gen<T> {

    }

    static class SubClass extends Gen<Integer> {}
    static class SubClass2 extends Gen<String> {}
}



class AmbigoutyTest {

    static class TestWithTwoGenerics<T, V> {
        void set(T t) {}
//        void set(V v) {}
    }
}