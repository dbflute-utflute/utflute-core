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

import junit.framework.AssertionFailedError;

/**
 * @author jflute
 */
public class PlainTestCaseTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    @Override
    protected void tearDown() throws Exception {
        if (getName().startsWith("test_markHere_nonAsserted")) {
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
    //                                                                       Assert Helper
    //                                                                       =============
    // -----------------------------------------------------
    //                                              Contains
    //                                              --------
    public void test_assertContains() throws Exception {
        assertContains("foo", "fo");
        try {
            assertContains("foo", "Fo");
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        assertContainsIgnoreCase("foo", "Fo");
        assertNotContains("foo", "ux");
        assertNotContains("foo", "Fo");
        try {
            assertNotContains("foo", "fo");
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        assertNotContainsIgnoreCase("foo", "ux");
        try {
            assertNotContains("foo", "Fo");
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
    }

    public void test_assertContainsAll() throws Exception {
        assertContainsAll("foo", "fo", "oo");
        try {
            assertContainsAll("foo", "fo", "sea");
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
    }

    public void test_assertContainsKeyword() throws Exception {
        assertContainsKeyword(newArrayList("foo", "bar", "qux"), "ar");
        try {
            assertContainsKeyword(newArrayList("foo", "bar", "qux"), "co");
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        assertContainsKeywordAll(newArrayList("foo", "bar", "qux"), "ar", "ux");
        try {
            assertContainsKeywordAll(newArrayList("foo", "bar", "qux"), "ar", "no");
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        assertContainsKeywordAny(newArrayList("foo", "bar", "qux"), "ar", "ux");
        assertContainsKeywordAny(newArrayList("foo", "bar", "qux"), "ar", "no");
        try {
            assertContainsKeywordAny(newArrayList("foo", "bar", "qux"), "no1", "no2");
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
    }

    // -----------------------------------------------------
    //                                                  Has
    //                                                 -----
    public void test_assertHas() throws Exception {
        assertHasAnyElement(newArrayList("foo"));
        assertHasAnyElement(newArrayList("foo", "bar"));
        try {
            assertHasAnyElement(newArrayList());
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        assertHasOnlyOneElement(newArrayList("foo"));
        try {
            assertHasOnlyOneElement(newArrayList("foo", "bar"));
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        try {
            assertHasOnlyOneElement(newArrayList());
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        assertHasPluralElement(newArrayList("foo", "bar"));
        try {
            assertHasPluralElement(newArrayList("foo"));
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        try {
            assertHasPluralElement(newArrayList());
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        assertHasZeroElement(newArrayList());
        try {
            assertHasZeroElement(newArrayList("foo"));
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
        try {
            assertHasZeroElement(newArrayList("foo", "bar"));
            fail();
        } catch (AssertionFailedError e) {
            log(e.getMessage());
        }
    }

    // -----------------------------------------------------
    //                                             Exception
    //                                             ---------
    public void test_exception_basic() throws Exception {
        String str = null;
        assertException(NullPointerException.class, () -> str.toString());
        assertException(IllegalStateException.class, () -> {
            throw new IllegalStateException("a\nb\nc");
        });
        assertException(IllegalStateException.class, () -> {
            throw new IllegalStateException("sealandpiari");
        }).handle(cause -> {
            assertContains(cause.getMessage(), "land");
        });
    }

    // ===================================================================================
    //                                                                           Mark Here
    //                                                                           =========
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

    public void test_markHere_nonAsserted_basic() throws Exception {
        markHere("foo");
    }

    public void test_markHere_nonAsserted_contains_assert() throws Exception {
        markHere("foo");
        markHere("foo");
        assertMarked("foo");
        markHere("bar");
        markHere("qux");
    }

    public void test_markHere_nonAsserted_many_mark() throws Exception {
        markHere("foo");
        markHere("foo");
        markHere("bar");
        markHere("qux");
    }

    // ===================================================================================
    //                                                                      Logging Helper
    //                                                                      ==============
    public void test_log_basic() throws Exception {
        // check your eyes
        log("foo");
        log("foo", "bar");
        log("foo", "bar", "qux");
        log("foo", "bar", "qux", new RuntimeException("corge"));
        log("foo", currentUtilDate(), currentTimestamp());
    }

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
    public void test_switchCurrentDate() {
        switchCurrentDate(() -> LocalDateTime.of(2016, 10, 27, 3, 0));
        log(currentLocalDate(), currentLocalDateTime(), currentUtilDate(), currentTimestamp());
    }
}
