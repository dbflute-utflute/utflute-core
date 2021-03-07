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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dbflute.utflute.core.binding.BindingAnnotationRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jflute
 */
public class InjectionTestCaseTest extends InjectionTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final List<String> markList = new ArrayList<String>();

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    @Override
    protected void xprepareTestCaseContainer() {
        markList.add("prepare"); // always called
    }

    @Override
    protected void xclearCachedContainer() {
        markList.add("clear"); // not called, keep cache mode
    }

    // ===================================================================================
    //                                                                   Component Binding
    //                                                                   =================
    @Override
    protected Map<Class<? extends Annotation>, BindingAnnotationRule> xprovideBindingAnnotationRuleMap() {
        return null;
    }

    // ===================================================================================
    //                                                                  Container Handling
    //                                                                  ==================
    @Override
    protected void xdestroyContainer() {
        markList.add("destroy"); // not called, keep cache mode
    }

    @Override
    protected <COMPONENT> COMPONENT getComponent(Class<COMPONENT> type) {
        return null;
    }

    @Override
    protected <COMPONENT> COMPONENT getComponent(String name) {
        return null;
    }

    @Override
    protected boolean hasComponent(Class<?> type) {
        return false;
    }

    @Override
    protected boolean hasComponent(String name) {
        return false;
    }

    // ===================================================================================
    //                                                                         Set up Test
    //                                                                         ===========
    @Test
    public void test_setup_overrideMethodsCalled() {
        Assertions.assertFalse(markList.isEmpty());
        Assertions.assertEquals(1, markList.size());
        Assertions.assertEquals("prepare", markList.get(0));
    }
}
