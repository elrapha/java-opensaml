/*
 * Licensed to the University Corporation for Advanced Internet Development, 
 * Inc. (UCAID) under one or more contributor license agreements.  See the 
 * NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The UCAID licenses this file to You under the Apache 
 * License, Version 2.0 (the "License"); you may not use this file except in 
 * compliance with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.core.config;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

/**
 * A class which provides basic testing for the ConfigurationService.
 */
public class ConfigurationServiceTest {
    
    /**
     *  Test registering, retrieving and deregistering a config
     *  object referenced in the config service as a class impl.
     */
    @Test
    public void testBasicRegistrationAndRetrievalAsClass() {
        AssertJUnit.assertNull(ConfigurationService.get(BasicTestConfig.class));
        
        BasicTestConfig config = new BasicTestConfig();
        config.setValue("test-value");
        ConfigurationService.register(BasicTestConfig.class, config);
        
        AssertJUnit.assertNotNull(ConfigurationService.get(BasicTestConfig.class));
        BasicTestConfig retrievedConfig = ConfigurationService.get(BasicTestConfig.class);
        AssertJUnit.assertEquals("test-value", retrievedConfig.getValue());
        
        ConfigurationService.deregister(BasicTestConfig.class);
        AssertJUnit.assertNull(ConfigurationService.get(BasicTestConfig.class));
    }
    
    /**
     *  Test registering, retrieving and deregistering a config
     *  object referenced in the config service as an interface.
     */
    @Test
    public void testBasicRegistrationAndRetrievalAsInterface() {
        AssertJUnit.assertNull(ConfigurationService.get(TestConfig.class));
        
        BasicTestConfig config = new BasicTestConfig();
        config.setValue("test-value");
        ConfigurationService.register(TestConfig.class, config);
        
        AssertJUnit.assertNotNull(ConfigurationService.get(TestConfig.class));
        TestConfig retrievedConfig = ConfigurationService.get(TestConfig.class);
        AssertJUnit.assertEquals("test-value", retrievedConfig.getValue());
        
        ConfigurationService.deregister(TestConfig.class);
        AssertJUnit.assertNull(ConfigurationService.get(TestConfig.class));
    }
    
    /**
     * Testing config interface.
     */
    public interface TestConfig {
        
        /**
         * Get config value.
         * 
         * @return the value
         */
        public String getValue();
    }
    
    /**
     * Testing config imple.
     */
    public class BasicTestConfig implements TestConfig {
        
        /** Config value.*/
        private String value;
        
        /** {@inheritDoc} */
        public String getValue() {
            return value;
        }
        
        /**
         * Set the config value.
         * 
         * @param newValue the new config value
         */
        public void setValue(String newValue) {
            value = newValue;
        }
        
    }

}
