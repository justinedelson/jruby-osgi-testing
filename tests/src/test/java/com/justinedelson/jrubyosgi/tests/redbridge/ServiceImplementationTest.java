package com.justinedelson.jrubyosgi.tests.redbridge;

import static org.ops4j.pax.exam.CoreOptions.*;

import org.jruby.embed.ScriptingContainer;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;

import com.justinedelson.jrubyosgi.tests.base.BaseServiceImplementationTest;

@RunWith(JUnit4TestRunner.class)
public class ServiceImplementationTest extends BaseServiceImplementationTest{

    @Configuration
    public Option[] configure() {
        return options(
                jrubyBundle(),
                mavenBundle().groupId("com.justinedelson.jrubyosgi").artifactId("api.bundle").version("0.0.1-SNAPSHOT"),
                mavenBundle().groupId("commons-io").artifactId("commons-io").version("1.4")
        );
    }

    @Override
    protected <T> T createImplementationObject(String script, Class<T> clazz) {
        ScriptingContainer container = new ScriptingContainer();
        Object obj = container.runScriptlet(script);
        return container.getInstance(obj, clazz);
    }

}
