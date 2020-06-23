package com.company.projname.webapp;

import com.company.projname.data.SimpleMapper;
import io.javalin.Javalin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.*;


import java.io.IOException;
import java.util.Properties;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) throws Exception {
        final var logger = LoggerFactory.getLogger(Main.class);

        final var sessionFactory = getSqlSessionFactory();

        try (final var session = sessionFactory.openSession()) {
            final var simpleMapper = session.getMapper(SimpleMapper.class);

            logger.info(String.valueOf(simpleMapper.count()));
        }




        final var app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("Hello World"));
    }

    static SqlSessionFactory getSqlSessionFactory() throws IOException {
        final var stream = Resources.getResourceAsReader("mybatis-conf.xml");
        final var sessionFactory = new SqlSessionFactoryBuilder().build(stream);
        return sessionFactory;
    }

    static void testServiceLoader() {
        final var logger = LoggerFactory.getLogger(Main.class);
        final var res = ServiceLoader.load(SomeInterface.class);
        final var fst = res.findFirst();

        if(fst.isEmpty()) {
            logger.info("empty");
        } else {
            fst.get().kek();
            logger.info("some");
        }
    }

    static Properties getConfigProperties() throws IOException {
        final var props = new Properties();
        final var stream = Resources.getResourceAsReader("config.properties");
        props.load(stream);

        return props;
    }
}
