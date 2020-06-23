package impls;

import core.MyLogger;

public class MyLoggerImpl implements MyLogger {
    @Override
    public void foo() {
        System.out.println("Hello from impl");
    }
}
