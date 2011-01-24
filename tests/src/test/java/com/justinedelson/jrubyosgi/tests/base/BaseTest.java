package com.justinedelson.jrubyosgi.tests.base;

import static org.ops4j.pax.exam.CoreOptions.*;

import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.options.MavenArtifactProvisionOption;

public class BaseTest {

    protected Option jrubyBundle() {
        String jrubyPath = System.getProperty("jruby.path");
        if (jrubyPath != null && (!"".equals(jrubyPath))) {
            System.out.println("Using JRuby from " + jrubyPath);
            return bundle("file://" + jrubyPath);
        } else {
            MavenArtifactProvisionOption opt = mavenBundle().groupId("org.jruby").artifactId("jruby-complete");
            if (System.getProperty("jruby.version") != null) {
                System.out.println("Using JRuby from Maven version " + System.getProperty("jruby.version"));
                opt = opt.version(System.getProperty("jruby.version"));
            } else {
                System.out.println("Using JRuby from Maven using Latest");
            }
            return opt;
        }

    }

}
