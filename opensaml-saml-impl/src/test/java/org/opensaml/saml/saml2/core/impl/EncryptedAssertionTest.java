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

package org.opensaml.saml.saml2.core.impl;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.opensaml.core.xml.XMLObjectProviderBaseTestCase;
import org.opensaml.saml.saml2.core.EncryptedAssertion;
import org.opensaml.xmlsec.encryption.EncryptedData;
import org.opensaml.xmlsec.encryption.EncryptedKey;

/**
 * Test case for creating, marshalling, and unmarshalling {@link org.opensaml.saml.saml2.core.impl.EncryptedAssertionImpl}.
 */
public class EncryptedAssertionTest extends XMLObjectProviderBaseTestCase {

    /** Count of EncryptedKey subelements. */
    private int encryptedKeyCount = 3;

    /** Constructor. */
    public EncryptedAssertionTest() {
        singleElementFile = "/data/org/opensaml/saml/saml2/core/impl/EncryptedAssertion.xml";
        childElementsFile = "/data/org/opensaml/saml/saml2/core/impl/EncryptedAssertionChildElements.xml";
    }

    /** {@inheritDoc} */
    @Test
    public void testSingleElementUnmarshall() {
        EncryptedAssertion encElement = (EncryptedAssertion) unmarshallElement(singleElementFile);

        AssertJUnit.assertNotNull(encElement);
        AssertJUnit.assertNull("EncryptedData child element", encElement.getEncryptedData());
        AssertJUnit.assertEquals("# of EncryptedKey children", 0, encElement.getEncryptedKeys().size());
    }
    
    /** {@inheritDoc} */
    @Test
    public void testChildElementsUnmarshall() {
        EncryptedAssertion encElement = (EncryptedAssertion) unmarshallElement(childElementsFile);
        
        AssertJUnit.assertNotNull("EncryptedAssertion was null", encElement);
        AssertJUnit.assertNotNull("EncryptedData child element", encElement.getEncryptedData());
        AssertJUnit.assertEquals("# of EncryptedKey children", encryptedKeyCount, encElement.getEncryptedKeys().size());

    }

    /** {@inheritDoc} */
    @Test
    public void testSingleElementMarshall() {
        EncryptedAssertion encElement = (EncryptedAssertion) buildXMLObject(EncryptedAssertion.DEFAULT_ELEMENT_NAME);

        assertXMLEquals(expectedDOM, encElement);
    }

    /** {@inheritDoc} */
    @Test
    public void testChildElementsMarshall() {
        EncryptedAssertion encElement = (EncryptedAssertion) buildXMLObject(EncryptedAssertion.DEFAULT_ELEMENT_NAME);
        
        encElement.setEncryptedData((EncryptedData) buildXMLObject(EncryptedData.DEFAULT_ELEMENT_NAME));
        for (int i=0; i < encryptedKeyCount; i++) {
            encElement.getEncryptedKeys().add((EncryptedKey) buildXMLObject(EncryptedKey.DEFAULT_ELEMENT_NAME));
        }
        
        assertXMLEquals(expectedChildElementsDOM, encElement);
    }
}