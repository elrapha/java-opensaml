/*
 * Copyright [2005] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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

package org.opensaml.saml2.core.validator;

import org.opensaml.saml2.core.Evidence;
import org.opensaml.xml.validation.ValidationException;
import org.opensaml.xml.validation.Validator;

/**
 * Checks {@link org.opensaml.saml2.core.Evidence} for Schema compliance.
 */
public class EvidenceSchemaValidator implements Validator<Evidence> {

    /** Constructor */
    public EvidenceSchemaValidator() {

    }

    /** {@inheritDoc} */
    public void validate(Evidence evidence) throws ValidationException {
        validateAssertions(evidence);
    }

    /**
     * Checks that at least one Assertion, Assertion ID Reference, or Assertion URI Reference is present.
     * 
     * @param evidence
     * @throws ValidationException
     */
    protected void validateAssertions(Evidence evidence) throws ValidationException {
        if (evidence.getEvidence() == null || evidence.getEvidence().size() == 0) {
            throw new ValidationException(
                    "Must contain at least one AssertionIDReference, AssertionURIReference, or Assertion");
        }
    }
}