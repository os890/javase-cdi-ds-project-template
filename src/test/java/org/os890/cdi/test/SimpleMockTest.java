/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.os890.cdi.test;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.os890.cdi.template.ApplicationScopedBean;
import org.os890.cdi.template.RequestScopedBean;
import org.os890.cdi.test.event.MockRegistrationEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import static org.mockito.Mockito.*;

@RunWith(CdiTestRunner.class)
public class SimpleMockTest
{
    @Inject
    private ApplicationScopedBean applicationScopedBean;

    @Inject
    private RequestScopedBean requestScopedBean;

    @Inject
    private Event<MockRegistrationEvent> mockRegistrationEvent;

    @Test
    public void injectionTest()
    {
        ApplicationScopedBean applicationScopedBean = mock(ApplicationScopedBean.class);
        when(applicationScopedBean.getValue()).thenReturn(7);
        this.mockRegistrationEvent.fire(new MockRegistrationEvent(applicationScopedBean));

        RequestScopedBean requestScopedBean = mock(RequestScopedBean.class);
        when(requestScopedBean.getValue()).thenReturn(3);
        this.mockRegistrationEvent.fire(new MockRegistrationEvent(requestScopedBean));

        Assert.assertEquals(new Integer(7), this.applicationScopedBean.getValue());
        Assert.assertEquals(new Integer(3), this.requestScopedBean.getValue());

        //replace mock instances
        applicationScopedBean = mock(ApplicationScopedBean.class);
        when(applicationScopedBean.getValue()).thenReturn(3);
        this.mockRegistrationEvent.fire(new MockRegistrationEvent(applicationScopedBean));

        requestScopedBean = mock(RequestScopedBean.class);
        when(requestScopedBean.getValue()).thenReturn(7);
        this.mockRegistrationEvent.fire(new MockRegistrationEvent(requestScopedBean));

        Assert.assertEquals(new Integer(3), this.applicationScopedBean.getValue());
        Assert.assertEquals(new Integer(7), this.requestScopedBean.getValue());
    }
}
