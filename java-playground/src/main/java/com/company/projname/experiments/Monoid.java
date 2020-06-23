package com.company.projname.experiments;

import java.util.function.Function;

public interface Monoid<T> {
    T mempty();
    T mappend(T a, T b);
}

/// Impossible w/o HKT
//interface Monad<M> {
//    <T> M<T> pure(T value);
//    <A, B> M<B> bind(M<A> ma, Function<A, M<B>> f);
//}

class Test {
    public static void main(String[] args) {
        final var res = sum(new IntSumMonoid(), new Integer[] {1,2,3,4,5,6});
        System.out.println(res);
    }

    static <T> T sum(Monoid<T> monoid, T[] arr) {
        var res = monoid.mempty();
        for(var i : arr) {
            res = monoid.mappend(res, i);
        }
        return res;
    }

    static class IntSumMonoid implements Monoid<Integer> {
        @Override
        public Integer mempty() {
            return 0;
        }

        @Override
        public Integer mappend(Integer a, Integer b) {
            return a + b;
        }
    }

    static class IntProductMonoid implements Monoid<Integer> {
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
