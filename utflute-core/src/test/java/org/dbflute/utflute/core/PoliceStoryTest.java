/*
 * Copyright 2014-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.dbflute.utflute.core;

import java.io.File;

import org.dbflute.utflute.core.filesystem.FileLineHandler;
import org.dbflute.utflute.core.policestory.javaclass.PoliceStoryJavaClassHandler;
import org.dbflute.utflute.core.policestory.jspfile.PoliceStoryJspFileHandler;
import org.dbflute.util.Srl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jflute
 * @since 0.4.0 (2014/03/16 Sunday)
 */
public class PoliceStoryTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    @Test
    public void test_policeStoryOfJavaClassChase_copyright() throws Exception {
        policeStoryOfJavaClassChase(new PoliceStoryJavaClassHandler() {
            public void handle(File srcFile, Class<?> clazz) {
                markHere("called");
                final StringBuilder sb = new StringBuilder();
                readLine(srcFile, "UTF-8", new FileLineHandler() {
                    public void handle(String line) {
                        sb.append(line).append(ln());
                    }
                });
                String text = sb.toString();
                log(clazz);
                assertContains(text, "Copyright 2014-2017");
            }
        });
        assertMarked("called");
    }

    @Test
    public void test_policeStoryOfJspFileChase_notExists() throws Exception {
        try {
            policeStoryOfJspFileChase(new PoliceStoryJspFileHandler() {
                public void handle(File jspFile) {
                    fail();
                }
            });
            fail();
        } catch (IllegalStateException e) {
            log(e.getMessage());
        }
    }

    // ===================================================================================
    //                                                                          Compatible
    //                                                                          ==========
    private void assertContains(String str, String keyword) {
        if (!Srl.contains(str, keyword)) {
            log("Asserted string: " + str); // might be large so show at log
            fail("the string should have the keyword but not found: " + keyword);
        }
    }

    private void fail() { // for compatible
        Assertions.fail();
    }

    private void fail(String msg) { // for compatible
        Assertions.fail(msg);
    }
}
