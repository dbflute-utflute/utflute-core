/*
 * Copyright 2014-2024 the original author or authors.
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
package org.dbflute.utflute.core.beanorder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @param <BEAN> The type of bean.
 * @author jflute
 */
public class ExpectedBeanOrderBy<BEAN> {

    protected final List<BeanOrderBySpec<BEAN>> orderBySpecList = new ArrayList<>();

    public ExpectedBeanOrderBy<BEAN> asc(Function<BEAN, Comparable<? extends Object>> oneArgInLambda) {
        orderBySpecList.add(createSpec(oneArgInLambda, true, false));
        return this;
    }

    public ExpectedBeanOrderBy<BEAN> desc(Function<BEAN, Comparable<? extends Object>> oneArgInLambda) {
        orderBySpecList.add(createSpec(oneArgInLambda, false, false));
        return this;
    }

    public ExpectedBeanOrderBy<BEAN> nullsFirstAsc(Function<BEAN, Comparable<? extends Object>> oneArgInLambda) {
        orderBySpecList.add(createSpec(oneArgInLambda, true, true));
        return this;
    }

    public ExpectedBeanOrderBy<BEAN> nullsFirstDesc(Function<BEAN, Comparable<? extends Object>> oneArgInLambda) {
        orderBySpecList.add(createSpec(oneArgInLambda, false, true));
        return this;
    }

    protected BeanOrderBySpec<BEAN> createSpec(Function<BEAN, Comparable<? extends Object>> oneArgInLambda, boolean asc,
            boolean nullsFirst) {
        return new BeanOrderBySpec<BEAN>(orderBySpecList.size() + 1, oneArgInLambda, asc, nullsFirst);
    }

    public List<BeanOrderBySpec<BEAN>> getOrderBySpecList() {
        return orderBySpecList;
    }
}
