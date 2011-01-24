package com.justinedelson.jrubyosgi.tests.jsr223;

import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.container.def.PaxRunnerOptions.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jruby.embed.ScriptingContainer;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Inject;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.justinedelson.jrubyosgi.tests.base.BaseServiceImplementationTest;

@RunWith(JUnit4TestRunner.class)
public class ServiceImplementationTest extends BaseServiceImplementationTest {
    @Configuration
    public Option[] configure() {
        return options(
                dsProfile(),
                bundle("http://repository.springsource.com/ivy/bundles/external/javax.servlet/com.springsource.javax.servlet/2.5.0/com.springsource.javax.servlet-2.5.0.jar"),
                mavenBundle().groupId("org.osgi").artifactId("org.osgi.compendium").version("4.2.0"),
                mavenBundle().groupId("org.apache.sling").artifactId("org.apache.sling.commons.log").version("2.1.0"),
                mavenBundle().groupId("org.apache.sling").artifactId("org.apache.sling.commons.mime").version("2.1.4"),
                mavenBundle().groupId("org.apache.sling").artifactId("org.apache.sling.commons.osgi").version("2.0.6"),
                jrubyBundle(),
                mavenBundle().groupId("org.apache.sling").artifactId("org.apache.sling.scripting.core")
                        .version("2.0.14"),
                mavenBundle().groupId("org.apache.sling").artifactId("org.apache.sling.scripting.api").version("2.1.2"),
                mavenBundle().groupId("org.apache.sling").artifactId("org.apache.sling.api").version("2.1.0"),
                mavenBundle().groupId("com.justinedelson.jrubyosgi").artifactId("api.bundle").version("0.0.1-SNAPSHOT"),
                mavenBundle().groupId("commons-io").artifactId("commons-io").version("1.4")
        );
    }

    @Inject
    BundleContext bundleContext = null;

    @Override
    protected <T> T createImplementationObject(String script, Class<T> clazz) throws ScriptException {
     // Get the SEM registered by Sling
        ServiceReference sr = bundleContext.getServiceReference(ScriptEngineManager.class.getName());
        assertNotNull(sr);
        ScriptEngineManager sem = (ScriptEngineManager) bundleContext.getService(sr);
        assertNotNull(sem);

        ScriptEngine jruby = sem.getEngineByExtension("rb");
        assertNotNull(jruby);

        return (T) jruby.eval(script);
    }


}
