/*
 * Copyright 2014-2021 the original author or authors.
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

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.dbflute.system.DBFluteSystem;
import org.dbflute.util.DfCollectionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

/**
 * @author jflute
 */
public class PlainTestCaseTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    @Override
    @AfterEach
    protected void tearDown() throws Exception {
        if (getTestMethodName().startsWith("test_markHere_nonAsserted")) {
            try {
                super.tearDown();
            } catch (AssertionFailedError e) {
                log(e.getMessage());
            }
        } else {
            super.tearDown();
        }
    }

    // ===================================================================================
    //                                                                           Mark Here
    //                                                                           =========
    @Test
    public void test_markHere_basic() throws Exception {
        markHere("foo");
        assertMarked("foo");
        try {
            assertMarked("bar");
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        markHere("qux");
        assertMarked("qux");
    }

    @Test
    public void test_markHere_phase() throws Exception {
        markHere("foo");
        markHere("bar");
        assertMarked("foo");
        try {
            assertMarked("foo");
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        markHere("foo");
        markHere("foo");
        assertMarked("foo");
        try {
            assertMarked("foo");
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        assertMarked("bar");
    }

    @Test
    public void test_markHere_nonAsserted_basic() throws Exception {
        markHere("foo");
    }

    @Test
    public void test_markHere_nonAsserted_contains_assert() throws Exception {
        markHere("foo");
        markHere("foo");
        assertMarked("foo");
        markHere("bar");
        markHere("qux");
    }

    @Test
    public void test_markHere_nonAsserted_many_mark() throws Exception {
        markHere("foo");
        markHere("foo");
        markHere("bar");
        markHere("qux");
    }

    // ===================================================================================
    //                                                                      Logging Helper
    //                                                                      ==============
    @Test
    public void test_log_basic() throws Exception {
        // check your eyes
        log("foo");
        log("foo", "bar");
        log("foo", "bar", "qux");
        log("foo", "bar", "qux", new RuntimeException("corge"));
        log("foo", DBFluteSystem.currentDate(), DBFluteSystem.currentTimestamp());
    }

    @Test
    public void test_log_placeholder() throws Exception {
        // check your eyes
        log("sea: {}, land: {}"); // sea: {}, land: {}
        log("sea: {}, land: {}", "mystic"); // sea: mystic, land: {}
        log("sea: {}, land: {}", "mystic", "oneman"); // sea: mystic, land: oneman
        log("sea: {}, land: {}, piari: {}", "mystic", "oneman"); // sea: mystic, land: oneman, piari: {}
        log("sea: {}, land: {}, piari: {}", "mystic", "oneman", "plaza"); // sea: mystic, land: oneman, piari: plaza
        log("sea: {}, land: {}", "mystic", "oneman", "plaza"); // sea: mystic, land: oneman, plaza
        log("sea: {{}", "mystic"); // sea: {mystic
        log("sea: {}}", "mystic"); // sea: mystic}
        log("sea: {{}}", "mystic"); // sea: {mystic}
        log("sea: {{}}", "mys{}tic"); // sea: {mys{}tic}
        log("sea: {}", "\\"); // expects no exception, sea: \
        log("sea: {}", "$"); // expects no exception, sea: $
        log("sea: {}", "\\$"); // expects no exception, sea: \$
    }

    // ===================================================================================
    //                                                                         Cannon-ball
    //                                                                         ===========
    // at CannonballTest

    // ===================================================================================
    //                                                                             DBFlute
    //                                                                             =======
    @Test
    public void test_switchCurrentDate() {
        switchCurrentDate(() -> LocalDateTime.of(2016, 10, 27, 3, 0));
        log(DBFluteSystem.currentLocalDate(), DBFluteSystem.currentLocalDateTime(), DBFluteSystem.currentDate(),
                DBFluteSystem.currentTimestamp());
    }

    // ===================================================================================
    //                                                                          Compatible
    //                                                                          ==========
    private void fail() { // for compatible
        Assertions.fail();
    }

    protected <ELEMENT> ArrayList<ELEMENT> newArrayList(@SuppressWarnings("unchecked") ELEMENT... elements) {
        return DfCollectionUtil.newArrayList(elements);
    }
}
