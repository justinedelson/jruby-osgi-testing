package com.justinedelson.jrubyosgi.tests.base;

import static org.junit.Assert.*;

import org.junit.Test;

public abstract class BaseSimpleInvocationTest extends BaseTest {

    protected abstract Object evalScript(String script) throws Exception;

    @Test
    public void putsHelloWorld() {
        eval("puts \"Hello World!\"");
    }

    private Object eval(String script) {
        try {
            return evalScript(script);
        } catch (Exception e) {
            System.out.println("Unable to run script: " + e.getMessage());
            e.printStackTrace();
            fail();
            return null;
        }

    }

    @Test
    public void returningValueFromScriptlet() {
        Object o = eval("\"Hello World!\"");
        assertNotNull(o);
        assertTrue(o instanceof String);
        assertEquals("Hello World!", o);

    }

}
