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

package org.opensaml.xmlsec.signature.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import net.shibboleth.utilities.java.support.collection.IndexingObjectStore;

import org.opensaml.core.xml.AbstractXMLObject;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.xmlsec.signature.X509CRL;

/** Concrete implementation of {@link X509CRL}. */
public class X509CRLImpl extends AbstractXMLObject implements X509CRL {

    /** Class-level index of Base64 encoded CRL values. */
    private static final IndexingObjectStore<String> B64_CRL_STORE = new IndexingObjectStore<>();

    /** Index to a stored Base64 encoded CRL. */
    private String b64CRLIndex;

    /**
     * Constructor.
     * 
     * @param namespaceURI the namespace the element is in
     * @param elementLocalName the local name of the XML element this Object represents
     * @param namespacePrefix the prefix for the given namespace
     */
    protected X509CRLImpl(String namespaceURI, String elementLocalName, String namespacePrefix) {
        super(namespaceURI, elementLocalName, namespacePrefix);
    }

    /** {@inheritDoc} */
    public String getValue() {
        return B64_CRL_STORE.get(b64CRLIndex);
    }

    /** {@inheritDoc} */
    public void setValue(String newValue) {
        // Dump our cached DOM if the new value really is new
        final String currentCert = B64_CRL_STORE.get(b64CRLIndex);
        final String b64Cert = prepareForAssignment(currentCert, newValue);

        // This is a new value, remove the old one, add the new one
        if (!Objects.equals(currentCert, b64Cert)) {
            B64_CRL_STORE.remove(b64CRLIndex);
            b64CRLIndex = B64_CRL_STORE.put(b64Cert);
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<XMLObject> getOrderedChildren() {
        return Collections.emptyList();
    }

    /** {@inheritDoc} */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        B64_CRL_STORE.remove(b64CRLIndex);
    }
}