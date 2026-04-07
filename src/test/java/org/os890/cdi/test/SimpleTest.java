/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.os890.cdi.test;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.os890.cdi.addon.dynamictestbean.EnableTestBeans;
import org.os890.cdi.template.ApplicationScopedBean;

/**
 * Verifies that {@link ApplicationScopedBean} is injectable and returns the
 * expected value inside a CDI SE container managed by the dynamic-cdi-test-bean-addon.
 */
@EnableTestBeans
class SimpleTest {

    @Inject
    private ApplicationScopedBean applicationScopedBean;

    /**
     * Asserts that the injected bean returns 14.
     */
    @Test
    void injectionTest() {
        Assertions.assertEquals(14, applicationScopedBean.getValue());
    }
}
