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

package org.opensaml.xml.signature.validator;

import org.opensaml.core.xml.BaseXMLObjectValidatorTestCase;
import org.opensaml.xmlsec.signature.Transform;
import org.opensaml.xmlsec.signature.support.SignatureConstants;
import org.opensaml.xmlsec.signature.validator.TransformSchemaValidator;

/**
 *
 */
public class TransformSchemaValidatorTest extends BaseXMLObjectValidatorTestCase {
    
    public TransformSchemaValidatorTest() {
        targetQName = Transform.DEFAULT_ELEMENT_NAME;
        validator = new TransformSchemaValidator();
    }

    protected void populateRequiredData() {
        super.populateRequiredData();
        Transform transform = (Transform) target;
        
        transform.setAlgorithm(SignatureConstants.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
    }
    
    public void testMissingTransformAlgo() {
        Transform transform = (Transform) target;
        
        transform.setAlgorithm(null);
        assertValidationFail("Transform algorithm was null, should have failed validation");
        
        transform.setAlgorithm("");
        assertValidationFail("Transform algorithm was empty, should have failed validation");
        
        transform.setAlgorithm("       ");
        assertValidationFail("Transform algorithm was all whitespace, should have failed validation");
    }

}
