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

package org.apache.commons.collections4;

public class Coverage {

    public static final boolean[] coverage_array  = new boolean[600]; // IDs 0..199
    private static boolean endPrint = false;

    public static void hit(int id) {
        // Mark your
        coverage_array[id] = true;

        if (!endPrint) {
            endPrint = true;
            Runtime.getRuntime().addShutdownHook(new Thread(Coverage::reportAll, "DIYCoverageReport"));
        }
    }

    private static void reportAll() {
        System.out.println("\n==== BRANCH COVERAGE ====");

        System.out.print("HIT: ");
        for (int i = 0; i < coverage_array.length; i++) {
            if (coverage_array[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println("=============================\n");
    }
}
