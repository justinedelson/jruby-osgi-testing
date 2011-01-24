package com.justinedelson.jrubyosgi.tests.base;

import static org.ops4j.pax.exam.CoreOptions.*;

import org.ops4j.pax.exam.options.MavenArtifactProvisionOption;

public class BaseTest {
    
    protected MavenArtifactProvisionOption jrubyBundle() {
        MavenArtifactProvisionOption opt = mavenBundle().groupId("org.jruby").artifactId("jruby-complete");
        if (System.getProperty("jruby.version") != null) {
        opt = opt.version(System.getProperty("jruby.version"));
        }
        return opt;
    }

}
