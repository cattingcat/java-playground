package com.company.projname.experiments;

public class Enumerations {
    public static void main(String[] args) {
        bar();
    }

    public static void foo(Apple a) {
        if(a == Apple.Cortland) {

        }

        switch(a) {
            case Cortland:
                System.out.println("c");
                break;
            case Jonathan:
                System.out.println("j");
                break;
            case GoldenDel:
                System.out.println("g");
                break;
            default:
                System.out.println("def");
        }
    }

    public static void bar() {
        final Apple[] appls = Apple.values();
        for(var a : appls) {
            System.out.println(a.toString() + " " + a.ordinal());
        }
    }
}


enum Apple {
    Jonathan, GoldenDel, RedDel, Winesap, Cortland
}


enum Apple2 {
    Jonathan(1), GoldenDel(2), RedDel(3), Winesap(4), Cortland(5), None;


    private final int p;

    Apple2(final int p) {
        this.p = p;
    }

    // None uses default ctor
    Apple2() {
        this.p = -1;
    }

    int getPrice() {
        return p;
    }
}




enum IntMonoids implements Monoid<Integer> {
    Sum {
        @Override
        public Integer mempty() {
            return 0;
        }

        @Override
        public Integer mappend(Integer a, Integer b) {
            return a + b;
        }
    },
    Product {
        @Override
        public Integer mempty() {
            return 1;
        }

        @Override
        public Integer mappend(Integer a, Integer b) {
            return a * b;
        }
    }
}