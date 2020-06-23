
module utils {
    requires core;
    exports utils;
    provides core.MyLogger with impls.MyLoggerImpl, impls.MyLogger2Impl;
}