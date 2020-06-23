module app {
    uses core.MyLogger;

    requires dal;
    requires core;

    exports app.main;
}