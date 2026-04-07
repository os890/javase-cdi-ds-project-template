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

package org.os890.cdi.template;

import jakarta.inject.Inject;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;

import java.util.logging.Logger;

/**
 * Demo application that boots a CDI SE container via DeltaSpike and
 * exercises an injected application-scoped bean.
 */
public class DemoApp {

    private static final Logger LOG = Logger.getLogger(DemoApp.class.getName());

    @Inject
    private ApplicationScopedBean applicationScopedBean;

    /**
     * Entry point that starts the CDI container, runs the application logic,
     * and shuts the container down.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        CdiContainer container = CdiContainerLoader.getCdiContainer();
        container.boot();
        ContextControl contextControl = container.getContextControl();
        contextControl.startContexts();

        try {
            BeanProvider.injectFields(new DemoApp()).runApplicationLogic();
        } finally {
            container.shutdown();
        }
    }

    /**
     * Logs the value obtained from the injected bean.
     */
    void runApplicationLogic() {
        LOG.info("Info of injected bean: " + this.applicationScopedBean.getValue());
    }
}
