/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.ConcurrentReferenceHashMap.Option;
import org.apache.commons.collections4.map.ConcurrentReferenceHashMap.ReferenceType;
import org.junit.jupiter.api.Test;

/**
 * Tests {@link ConcurrentReferenceHashMap}.
 */
class ConcurrentReferenceHashMapTest {

    @Test
    void testBuilderAll() {
        final Map<Integer, String> map0 = new HashMap<>();
        map0.put(1, "1");
        // @formatter:off
        final Map<Integer, String> map = ConcurrentReferenceHashMap.<Integer, String>builder()
                .setConcurrencyLevel(4)
                .setInitialCapacity(32)
                .setKeyReferenceType(ReferenceType.SOFT)
                .setLoadFactor(0.74f)
                .setOptions(EnumSet.of(Option.IDENTITY_COMPARISONS))
                .setSourceMap(map0)
                .get();
        // @formatter:on
        map0.put(2, "2");
        assertTrue(map.containsKey(1));
        assertFalse(map.containsKey(2));

    }

    /**
     * test that a negative concurrencyLevel throws the exeption IllegalArgumentException
     */
    @Test
    void concurrentReferenceHashMapThrowExeptionWhenConcurrencyLevelIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConcurrentReferenceHashMap.builder()
                    .setConcurrencyLevel(-1)
                    .get();
        });
    }

    /**
     * test that a negative initialCapacity throws the exeption IllegalArgumentException
     */
    @Test
    void concurrentReferenceHashMapThrowExeptionWhenInitialCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConcurrentReferenceHashMap.builder()
                    .setInitialCapacity(-1)
                    .get();
        });
    }

    /**
     * test that loadFactor equals zero results in the thrown exeption IllegalArgumentException
     */
    @Test
    void concurrentReferenceHashMapThrowExeptionWhenLoadFactorIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConcurrentReferenceHashMap.builder()
                    .setLoadFactor(0)
                    .get();
        });
    }

    /**
     * test that ConcurrentReferenceHashMap limits concurrencyLevel to max (1 << 16) if it is set to something higher than that
     */
    @Test
    void concurrentReferenceHashMapLimitsConcurrencyLevel() {
        int overMax = (1 << 16) + 1;
        int max = (1 << 16);
        final Map<Integer, String> overMaxMap = ConcurrentReferenceHashMap.<Integer, String>builder()
                .setConcurrencyLevel(overMax)
                .get();
        final Map<Integer, String> maxMap = ConcurrentReferenceHashMap.<Integer, String>builder()
                .setConcurrencyLevel(max)
                .get();

        assertTrue(overMaxMap.equals(maxMap));
    }
}
