package com.justinedelson.jrubyosgi.tests.base;

import java.io.InputStream;

import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import static org.junit.Assert.*;

import com.justinedelson.jrubyosgi.api.SampleService;

public abstract class BaseServiceImplementationTest extends BaseTest {

    protected abstract <T> T createImplementationObject(String script, Class<T> clazz) throws ScriptException;

    @Test
    public void implementAnInterface() throws Exception {
        InputStream is = getClass().getResourceAsStream("/api_implementation_1.rb");
        String str = IOUtils.toString(is);

        SampleService service = createImplementationObject(str, SampleService.class);
        assertNotNull(service);
        assertEquals("foofoo", service.doSomething("foo"));
    }

}
