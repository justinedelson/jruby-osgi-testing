package com.justinedelson.jrubyosgi.tests.redbridge;

import static org.ops4j.pax.exam.CoreOptions.*;

import org.jruby.embed.ScriptingContainer;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;

import com.justinedelson.jrubyosgi.tests.base.BaseSimpleInvocationTest;

/**
 * In this test, we do a simple JRuby script invocation via the RedBridge API.
 * 
 * @author Justin Edelson
 */
@RunWith(JUnit4TestRunner.class)
public class SimpleInvocationTest extends BaseSimpleInvocationTest {

    @Configuration
    public Option[] configure() {
        return options(
                jrubyBundle()
                );
    }

    @Override
    protected Object evalScript(String script) throws Exception {
        ScriptingContainer container = new ScriptingContainer();

        return container.runScriptlet(script);
    }

}
