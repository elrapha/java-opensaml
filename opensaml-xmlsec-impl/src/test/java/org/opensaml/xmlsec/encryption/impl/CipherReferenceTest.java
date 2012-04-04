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

package org.opensaml.xmlsec.encryption.impl;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.opensaml.core.xml.XMLObjectProviderBaseTestCase;
import org.opensaml.xmlsec.encryption.CipherReference;
import org.opensaml.xmlsec.encryption.Transforms;

/**
 *
 */
public class CipherReferenceTest extends XMLObjectProviderBaseTestCase {
    
    private String expectedURI;
    
    /**
     * Constructor
     *
     */
    public CipherReferenceTest() {
        singleElementFile = "/data/org/opensaml/xmlsec/encryption/impl/CipherReference.xml";
        childElementsFile = "/data/org/opensaml/xmlsec/encryption/impl/CipherReferenceChildElements.xml";
    }

    /** {@inheritDoc} */
    @BeforeMethod
    protected void setUp() throws Exception {
        expectedURI = "urn:string:foo";
    }

    /** {@inheritDoc} */
    @Test
    public void testSingleElementUnmarshall() {
        CipherReference cr = (CipherReference) unmarshallElement(singleElementFile);
        
        AssertJUnit.assertNotNull("CipherReference", cr);
        AssertJUnit.assertEquals("URI attribute", expectedURI, cr.getURI());
        AssertJUnit.assertNull("Transforms child", cr.getTransforms());
    }

    /** {@inheritDoc} */
    @Test
    public void testChildElementsUnmarshall() {
        CipherReference cr = (CipherReference) unmarshallElement(childElementsFile);
        
        AssertJUnit.assertNotNull("CipherReference", cr);
        AssertJUnit.assertEquals("URI attribute", expectedURI, cr.getURI());
        AssertJUnit.assertNotNull("Transforms child", cr.getTransforms());
    }

    /** {@inheritDoc} */
    @Test
    public void testSingleElementMarshall() {
        CipherReference cr = (CipherReference) buildXMLObject(CipherReference.DEFAULT_ELEMENT_NAME);
        
        cr.setURI(expectedURI);
        
        assertXMLEquals(expectedDOM, cr);
    }

    /** {@inheritDoc} */
    @Test
    public void testChildElementsMarshall() {
        CipherReference cr = (CipherReference) buildXMLObject(CipherReference.DEFAULT_ELEMENT_NAME);
        
        cr.setURI(expectedURI);
        cr.setTransforms((Transforms) buildXMLObject(Transforms.DEFAULT_ELEMENT_NAME));
        
        assertXMLEquals(expectedChildElementsDOM, cr);
    }

}
