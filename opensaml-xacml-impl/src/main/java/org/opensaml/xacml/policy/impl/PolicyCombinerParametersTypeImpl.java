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

package org.opensaml.xacml.policy.impl;

/**
 * Implementation of {@link PolicyCombinerParametersTypeUnmarshaller}.
 */
public class PolicyCombinerParametersTypeImpl extends CombinerParametersTypeImpl implements
        org.opensaml.xacml.policy.PolicyCombinerParametersType {

    /** Policy indentity reference. */
    private String policyIdRef;

    /**
     * Constructor.
     * 
     * @param namespaceURI the namespace the element is in
     * @param elementLocalName the local name of the XML element this Object represents
     * @param namespacePrefix the prefix for the given namespace
     */
    protected PolicyCombinerParametersTypeImpl(String namespaceURI, String elementLocalName, String namespacePrefix) {
        super(namespaceURI, elementLocalName, namespacePrefix);
        // combinerParameters = new XMLObjectChildrenList<CombinerParameterType>(this);
    }

    /** {@inheritDoc} */
    public String getPolicyIdRef() {
        return policyIdRef;
    }

    /** {@inheritDoc} */
    public void setPolicyIdRef(String ref) {
        this.policyIdRef = prepareForAssignment(this.policyIdRef, ref);
    }

}
