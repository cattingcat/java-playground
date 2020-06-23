package impls;

import core.MyLogger;

public class MyLogger2Impl implements MyLogger {
    @Override
    public void foo() {
        System.out.println("Hello from impl 2");
    }
}
