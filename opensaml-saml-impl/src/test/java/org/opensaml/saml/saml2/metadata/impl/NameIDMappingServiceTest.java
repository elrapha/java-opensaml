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

/**
 * 
 */
package org.opensaml.saml.saml2.metadata.impl;

import org.opensaml.saml.common.BaseSAMLObjectProviderTestCase;
import org.opensaml.saml.saml2.metadata.NameIDMappingService;

/**
 * Test case for creating, marshalling, and unmarshalling
 * {@link org.opensaml.saml.saml2.metadata.impl.NameIDMappingServiceServiceImpl}.
 */
public class NameIDMappingServiceTest extends BaseSAMLObjectProviderTestCase {
    
    protected String expectedBinding;
    protected String expectedLocation;
    protected String expectedResponseLocation;
    
    /**
     * Constructor
     */
    public NameIDMappingServiceTest() {
        singleElementFile = "/data/org/opensaml/saml2/metadata/impl/NameIDMappingService.xml";
        singleElementOptionalAttributesFile = "/data/org/opensaml/saml2/metadata/impl/NameIDMappingServiceOptionalAttributes.xml";
    }
    
    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();
        
        expectedBinding = "urn:binding:foo";
        expectedLocation = "example.org";
        expectedResponseLocation = "example.org/response";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        NameIDMappingService service = (NameIDMappingService) unmarshallElement(singleElementFile);
        
        assertEquals("Binding URI was not expected value", expectedBinding, service.getBinding());
        assertEquals("Location was not expected value", expectedLocation, service.getLocation());
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesUnmarshall() {
        NameIDMappingService service = (NameIDMappingService) unmarshallElement(singleElementOptionalAttributesFile);
        
        assertEquals("Binding URI was not expected value", expectedBinding, service.getBinding());
        assertEquals("Location was not expected value", expectedLocation, service.getLocation());
        assertEquals("ResponseLocation was not expected value", expectedResponseLocation, service.getResponseLocation());;
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        NameIDMappingService service = (NameIDMappingService) buildXMLObject(NameIDMappingService.DEFAULT_ELEMENT_NAME);
        
        service.setBinding(expectedBinding);
        service.setLocation(expectedLocation);

        assertEquals(expectedDOM, service);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesMarshall() {
        NameIDMappingService service = (NameIDMappingService) buildXMLObject(NameIDMappingService.DEFAULT_ELEMENT_NAME);
        
        service.setBinding(expectedBinding);
        service.setLocation(expectedLocation);
        service.setResponseLocation(expectedResponseLocation);

        assertEquals(expectedOptionalAttributesDOM, service);
    }
}