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

package org.opensaml.util.storage;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import com.google.common.base.Optional;

import net.shibboleth.utilities.java.support.annotation.constraint.NotEmpty;
import net.shibboleth.utilities.java.support.collection.Pair;
import net.shibboleth.utilities.java.support.component.DestructableComponent;
import net.shibboleth.utilities.java.support.component.IdentifiableComponent;
import net.shibboleth.utilities.java.support.component.InitializableComponent;
import net.shibboleth.utilities.java.support.component.ValidatableComponent;

/**
 * Generic data storage facility. Implementations will vary in how much
 * persistence they can supply, and must support short values of at least 255
 * characters, and long text values of arbitrary size.
 * 
 * <p>Storage is divided into "contexts" identified by a string label.
 * Keys need to be unique only within a given context, so multiple
 * components can share a single store safely as long as they
 * use different labels.</p>
 *
 * <p>The allowable sizes for contexts, keys, and short values can vary
 * and be reported by the implementation to callers, but MUST be at least
 * 255 characters.</p>
 * 
 * <p>Expiration is expressed in seconds since the epoch.</p>
 * 
 * <p>It is implementation dependent whether a store partitions records by
 * type (string vs. text) and callers must not mix create/read/update/delete
 * calls of different sizes for a given context and key.</p>
 */
@ThreadSafe
public interface StorageService extends InitializableComponent, DestructableComponent, IdentifiableComponent,
    ValidatableComponent {

    /**
     * Returns the capabilities of the underlying store.
     *
     * @return interface to access the service's capabilities
     */
    @Nonnull public StorageCapabilities getCapabilities();

    /**
     * Creates a new "string" record in the store.
     * 
     * @param context       a storage context label
     * @param key           a key unique to context
     * @param value         value to store
     * @param expiration    optional expiration for record
     * 
     * @return  true iff record was inserted, false iff a duplicate was found
     * @throws IOException  if fatal errors occur in the insertion process 
     */
    public boolean createString(@Nonnull @NotEmpty final String context, @Nonnull @NotEmpty final String key,
            @Nonnull @NotEmpty final String value, @Nonnull final Optional<Long> expiration) throws IOException;
    
    /**
     * Returns an existing "string" record from the store, along with its version.
     *
     * <p>The first member of the pair returned will contain the version of the record in the store,
     * or will be absent if no record exists. The second member will contain the record read back.
     * If absent, the record either didn't exist (if the first member was also absent) or the record
     * was the same version as that supplied by the caller.</p>
     * 
     * @param context       a storage context label
     * @param key           a key unique to context
     * @param version       if set, only return record if newer than supplied version
     * 
     * @return  a pair consisting of the version of the record read back, if any, and the record itself
     * @throws IOException  if errors occur in the read process 
     */
    @Nonnull public Pair<Optional<Integer>, Optional<StorageRecord>> readString(@Nonnull @NotEmpty final String context,
            @Nonnull @NotEmpty final String key, @Nonnull final Optional<Integer> version) throws IOException;
    
    /**
     * Updates an existing "string" record in the store.
     * 
     * @param context       a storage context label
     * @param key           a key unique to context
     * @param value         updated value (may be absent, indicating only the expiration should be updated)
     * @param expiration    updated expiration, absent indicating no expiration
     * @param version       if present, only update if the current version matches this value
     * 
     * @return the version of the record after update, absent if no record exists, or -1 if the version
     *  parameter is present and does not match the current version before update (so the caller is out of sync)
     * @throws IOException  if errors occur in the update process 
     */
    @Nonnull public Optional<Integer> updateString(@Nonnull @NotEmpty final String context,
            @Nonnull @NotEmpty final String key, @Nonnull final Optional<String> value,
            @Nonnull final Optional<Long> expiration, @Nonnull final Optional<Integer> version) throws IOException;
    
    /**
     * Deletes an existing "string" record from the store.
     * 
     * @param context       a storage context label
     * @param key           a key unique to context
     * 
     * @return true iff the record existed and was deleted
     * @throws IOException  if errors occur in the deletion process 
     */
    public boolean deleteString(@Nonnull @NotEmpty final String context, @Nonnull @NotEmpty final String key)
            throws IOException;

    /**
     * Creates a new "text" record in the store.
     * 
     * @param context       a storage context label
     * @param key           a key unique to context
     * @param value         value to store
     * @param expiration    optional expiration for record
     * 
     * @return  true iff record was inserted, false iff a duplicate was found
     * @throws IOException  if fatal errors occur in the insertion process 
     */
    public boolean createText(@Nonnull @NotEmpty final String context, @Nonnull @NotEmpty final String key,
            @Nonnull @NotEmpty final String value, @Nonnull final Optional<Long> expiration) throws IOException;
    
    /**
     * Returns an existing "text" record from the store, along with its version.
     *
     * <p>The first member of the pair returned will contain the version of the record in the store,
     * or will be absent if no record exists. The second member will contain the record read back.
     * If absent, the record either didn't exist (if the first member was also absent) or the record
     * was the same version as that supplied by the caller.</p>
     * 
     * @param context       a storage context label
     * @param key           a key unique to context
     * @param version       if set, only return record if newer than supplied version
     * 
     * @return  a pair consisting of the version of the record read back, if any, and the record itself
     * @throws IOException  if errors occur in the read process 
     */
    @Nonnull public Pair<Optional<Integer>, Optional<StorageRecord>> readText(@Nonnull @NotEmpty final String context,
            @Nonnull @NotEmpty final String key, @Nonnull final Optional<Integer> version) throws IOException;
    
    /**
     * Updates an existing "text" record in the store.
     * 
     * @param context       a storage context label
     * @param key           a key unique to context
     * @param value         updated value (may be absent, indicating only the expiration should be updated)
     * @param expiration    updated expiration, absent indicating no expiration
     * @param version       if present, only update if the current version matches this value
     * 
     * @return the version of the record after update, absent if no record exists, or -1 if the version
     *  parameter is present and does not match the current version before update (so the caller is out of sync)
     * @throws IOException  if errors occur in the update process 
     */
    @Nonnull public Optional<Integer> updateText(@Nonnull @NotEmpty final String context,
            @Nonnull @NotEmpty final String key, @Nonnull final Optional<String> value,
            @Nonnull final Optional<Long> expiration, @Nonnull final Optional<Integer> version) throws IOException;
    
    /**
     * Deletes an existing "text" record from the store.
     * 
     * @param context       a storage context label
     * @param key           a key unique to context
     * 
     * @return true iff the record existed and was deleted
     * @throws IOException  if errors occur in the deletion process 
     */
    public boolean deleteText(@Nonnull @NotEmpty final String context, @Nonnull @NotEmpty final String key)
            throws IOException;
    
    /**
     * Manually trigger a cleanup of expired records. The method <strong>MAY</strong> return without guaranteeing
     * that cleanup has already occurred.
     * 
     * @param context       a storage context label
     * 
     * @throws IOException  if errors occur in the cleanup process
     */
    public void reap(@Nonnull @NotEmpty final String context) throws IOException;
    
    /**
     * Updates the expiration time of all records in the context.
     * 
     * @param context       a storage context label
     * @param expiration    a new expiration timestamp, absent indicating no expiration
     * 
     * @throws IOException  if errors occur in the cleanup process
     */
    public void updateContext(@Nonnull @NotEmpty final String context,
            @Nonnull @NotEmpty final Optional<Long> expiration) throws IOException;

    /**
     * Forcibly removes all records in a given context along with any
     * associated resources devoted to maintaining the context.
     * 
     * @param context       a storage context label
     * 
     * @throws IOException  if errors occur in the cleanup process
     */
    public void deleteContext(@Nonnull @NotEmpty final String context) throws IOException;
    
}