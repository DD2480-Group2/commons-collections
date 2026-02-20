<!---
 Licensed to the Apache Software Foundation (ASF) under one or more
 contributor license agreements.  See the NOTICE file distributed with
 this work for additional information regarding copyright ownership.
 The ASF licenses this file to You under the Apache License, Version 2.0
 (the "License"); you may not use this file except in compliance with
 the License.  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
# Report for assignment 3

## 3.1 & 3.2 Project

Name: Apache Commons Collections

URL: https://github.com/apache/commons-collections

The Apache Commons Collections package contains implementations, enhancements and utilities that complement the Java Collections Framework.

### Grading goals
The members have all attempted to reach P+ through the three requirements:
- Each group member writes at least four new or enhanced unit tests.
- Using the issue tracker and systematic commit messages to manage our project.
- We all refactored the complex function that is described in **3.4**, and reduced their respective CC with at least 35%.

## 3.3 Onboarding experience

Our group has primarily worked with Java projects using Maven for dependency management, compilation and testing. Therefore we initially searched for a Maven-based project, and both commons-lang and commons-img were recommended in the instructions. At the time we began selecting a project, both of the previously mentioned Apache projects had already been chosen by three groups. We started working with commons-io and commons-codec, but both proved difficult to test and did not provide much help with troubleshooting or guidance. We moved on to commons-collection and both building and testing with Maven worked immediately after cloning, making the onboarding experience with this project smooth and comfortable.

---

### 1. How easily can you build the project?

#### (a) Did you have to install a lot of additional tools to build the software?

As answered above, seeing as we had already worked on Java and Maven, the additional tools, or dependencies, had already been installed. Therefore, building worked immediately.

#### (b) Were those tools well documented?

Not really, seeing as users of Apache Commons Collections are most likely expected to be familiar with the JDK and Maven already. Jacoco is also mentioned, but there is no explanation of what Jacoco really is for similar reasons.

#### (c) Were other components installed automatically by the build script?

No system-wide installations took place, but running the mvn command did download dependencies/plugins such as: jacoco, surefire, apache rat etc.

#### (d) Did the build conclude automatically without errors?

Yes, but with 18 warnings.

#### (e) How well do examples and tests run on your system(s)?

Without any issues.

### 2.  Do you plan to continue or choose another project?

No, at the moment we are pleased with Apache Commons Collections.

## 3.4 Complexity

| Function                                                                                                                                                                                                                 | Jacoco CC | Lizard CC | Counted CC | Row # in File |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------:|----------:|-----------:|--------------:|
| [remove(final Object key)](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L1060)                     |        29 |        29 |         29 |          1060 |
| [put(final K key, final V value)](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L932)               |        25 |        25 |         26 |           932 |
| [equals(final Object obj)](https://github.com/DD2480-Group2/commons-collections/blob/b72b2dd0626b11270393bc1934ac38aded1fcb61/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L756)                                |        15 |        15 |         15 |           756 |
| [ConcurrentReferenceHashMap](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMap.java#L1443) |        12 |        11 |         11 |          1443 |
| [get(final Object key)](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L809)                         |        19 |        19 |         19 |           809 |

### Complex function 1: [remove@src/main/java/…/map/Flat3Map.java](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L1060)
1. Both group members who counted the functions CC independently got the same result of 29, which also aligned with the output from Lizard.
2. In combination with its high CC, the function also has many LOC with about 140. This is much higher than the average function in the project according to lizard who reported an average of 7 LOC per function.
3. The purpose of the remove-function is to remove an element from a performance-optimized Map which works faster than HashMaps when there are <=3 elements used. Since the implementation of the Map uses the classes fields to store key- and value data, the functions large CC comes from handling a lot of edge cases and the function trades simplicity for efficiency.
4. The remove function does not throw an exception, but if it did that would be a potential new branch and it would increase the CC by 1 for each exception that could be thrown.
5. There is documentation at the top level of the function for users using the method, but within the function itself, there is near to no documentation. So there is no explanation of how the function handles branching and the different outcomes based on what branch that will be taken. But from a high level, it is clear what the function will do and return given the input parameter.

### Complex function 2: [put@src/main/java/…/map/Flat3Map.java](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L932)
1. Teoman: 25 + 1, Samuel: 25 + 1
   Jacoco and Lizard both give 25.
   In other words, the manual count differs from the tool by one. The reason for this could probably be explained by the fact that there is a default case, which is probably not counted by Jacoco and Lizard. Seeing as I consider the default case as a branch, then this increased the value by one.
2. Yes, that generally seems to be the case. As for the put function, then it has 49 lines, and its complexity is 25. In the Flat3Map class, then it is the function with the second most number of lines of code, as well as being the function with the second highest in terms of complexity.
3. The purpose of this function, as noted by the JavaDoc, is to put a value at a specific key in the map. The number of lines in the function can be explained by the fact that the implementation of put in the Flat3Map class assumes a maximum size of 3. Therefore, there are switch cases that have to cover these different size cases. There is also the case where you call put on an existing key and when the key is null.
4. The put function does not throw any exceptions. After surrounding some of the code in a try-catch block, then the complexity is increased by one according to Lizard, as the catch block becomes its own branch. The try statement does not contribute to the complexity as the try is always executed first, meaning there is no other path of execution here as the try is always executed.
5. Yes, somewhat. There are comments above important branches that explain the different edge cases of the function. Besides this, the JavaDoc is not explicit as to the details of the function.

### Complex function 3: [equals@src/main/java/…/map/Flat3Map.java](https://github.com/DD2480-Group2/commons-collections/blob/b72b2dd0626b11270393bc1934ac38aded1fcb61/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L756)
1. Initially, one of the group members had miscounted the function’s CC, but fairly quickly realized they had forgotten to include one of the decision points, after which both group members’ results matched the CC of 15 as reported by Lizard.
2. This function has a length of 45 LOC. As such, it is not very long, considering its relatively high CC. In contrast to the first complex function used as an example, `remove()`, this function is significantly shorter relative to each function’s respective CC.
3. The `equals()` function checks equality between two Map objects by verifying their respective sizes, keys and values. The large CC is due to multiple checks like `if (obj == this)`, `if (!(obj instanceof Map))`, and `if (size > 0)`, as well as separate cases for different sizes (1, 2, or 3), each with separate branches for checking both key existence and value equality.
4. This function in particular does not include any exceptions, however, if it did, Lizard would take it into account as long as it’s in a try-catch block.
5. There is minimal documentation for the function as a whole, “Compares this map with another. [...] @return true if equal”. Although it doesn’t explicitly describe the specific cases leading to a false return value, e.g. size differences, absent keys, or non-equal values, its behavior is still clear considering the limited number of possible outcomes, and given the relatively readable code, there isn’t a significant need for much documentation. Still, the function could use some additional documentation than what is currently present.

### Complex function 4: [ConcurrentReferenceHashMap@src/main/java/…/map/ConcurrentReferenceHashMap.java](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMap.java#L1443)
1. JaCoCo calculated the complexity to be 12 but both manually counting and lizard got the CC to be 11. This was a bit surprising since we believed that JaCoCos complexity was the same as CC but since they write Cxty instead of CC it is possible they calculate complexity in some other way. Lizard does however call their complexity CCN (Cyclomatic Complexity Number) so we assume that 11 is the correct number of CC.
2. The function had 47 LOC meaning it is longer than most other functions, even slightly longer than Complex function 3 that is more complex (15 CC).
3. It is a constructor for the class ConcurrentReferenceHashMap. It creates a new, empty map with the specified initial capacity, reference types, load factor, and concurrency level. The map is divided in segments so different threads can safely access different parts of the map at the same time.
4. No, this function does throw an exception but it does not increase CC since it is not in a try-catch block but rather is used as a return if the first if-statement is true. JaCoCo could count it in their complexity which could be why it is one higher complexity.
5. Yes, the documentation is very clear. However, I would have added information about the max cap on concurrencyLevel and initialCapacity.

### Complex function 5: [get@src/main/java/…/map/Flat3Map.java](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L809)
1. The manual calculation of the CC matches the result reported by Lizard (CC = 19).
2. This function has 38 LOC which seems to be relatively low if we compare it to the previous ones. It is indeed more complex than equals() but has fewer LOC.
3. It returns the value associated with key, using a fast manual lookup for up to 3 elements, and a normal HashMap lookup once the map grows larger.
4. Although this function contains no exception handling, Lizard would include it in the complexity calculation if it were implemented inside a try–catch block.
5. No. The Javadoc states the general contract (returns the mapped value or null), but it does not describe the different behaviors caused by the internal branches (flat storage vs delegated map, special handling of null, etc.). It is functionally correct but not detailed about the various execution paths.

## 3.5 Coverage and Improvement
We used JaCoCo as our automated coverage measurement tool. JaCoCo was already integrated into the project through Maven,
which made it straightforward to use. We generated a coverage report by running ```mvn test``` and then ```mvn jacoco:report```.

JaCoCo then produced an HTML report that provided information about instruction coverage,
branch coverage and other key-values for analyzing the repo both on class level and on method level.

### 3.5.1 Your own coverage tool
We implemented a simple manual branch coverage tool by instrumenting the selected high CC functions.
The class is called `Coverage.java` and is located at `src/main/java/org/apache/commons/collections4`.
The class contains two fields and two methods. The fields are a boolean array and a boolean value.
The methods are hit(int n) that sets index n in the boolean array to true, signifying that we have explored branch n.
The second method prints out the boolean arrays index, if any index have been changed to true, by checking the other boolean field.
Our coverage tool is used by inserting a call to `Coverage.hit(ID)` in the different possible branches that the code can take.
When the tests then run, different parts of the code will be explored, and the tool will tell what branches that have been hit,
giving us information about what branches that were not hit.

Our branch for instrumented code to gather coverage measurements:
[Development-Coverage branch](https://github.com/DD2480-Group2/commons-collections/tree/Development-Coverage)

To obtain the patch, use the command ```git diff master..Development-Coverage src```

#### Evaluation

1. How detailed is your coverage measurement?

Our tool provides information about what branches have been explored in the instrumented functions through conditional-statements and switch-statements.
The level of detail is rather high when it comes to what branch has been taken, but it does not cover anything more than that.

2. What are the limitations of your own tool?

The limitations in our implemented tool lies partly in when if-statements contain multiple conditionals connected by ```&&``` and ```||```, since our tool can only explore the branches the code goes to, we do not explore the different combinations of conditionals inside the if-statement, but only when the if-statement results in true or false. Due to this our output might be interpreted as if a higher percentage of the branches are being covered, compared to JaCoCo’s output.
There is also currently limitations regarding how loops are handled, since we only explore different branches, we can only explore if we enter a loop or not, but not how many times we iterate.


3. Are the results of your tool consistent with existing coverage tools?

The output is somewhat aligned with what JaCoCo outputs with exceptions for when the code contains if-statements containing multiple conditionals connected through ```&&``` and ```||```, since JaCoCo explores all possible combinations of parameters for the if-statement, our tool simply explores the if-statement as true or false. This makes it so our tool tells a story of a higher branch coverage than might be present, whereas JaCoCo will determine more possible branch outcomes.

## 3.5.2 Coverage improvement

### Kevin, [remove(Object)](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L1060):
The parts of the remove method that were not covered through the original tests were two checks for when the Map was full
(3 elements) and we try to remove either key 1 or key 2 when the key itself is null.
Remove also missed a branch in which the Map we wanted to remove an element from only has 1 element,
and the key is not null and also matches the key of the element in the Map.
I also implemented a test for the equals method where no previous test confirmed that if the compared Maps had different size, the method should return false.

<details>
    <summary>JaCoCo before improvement </summary>

<img width="408" height="638" alt="image" src="https://github.com/user-attachments/assets/9cbcafd4-fdc8-4db1-8afb-cc5ac64f0c2f" />


</details>

<details>
    <summary>JaCoCo After Coverage improvement</summary>

<img width="410" height="637" alt="image" src="https://github.com/user-attachments/assets/34085c85-2cef-48cd-af50-238178049f89" />

</details>

Below we can see the branch coverage for the remove method before and after implemented tests:

<details>
    <summary> Method overview before and after branch coverage </summary>

Before:

| Element        | Instruction Coverage | Branch Coverage | Missed | Cxty |
|----------------|---------------------:|----------------:|-------:|-----:|
| remove(Object) |                  84% |             76% |     12 |   29 |  
After:

| Element        | Instruction Coverage | Branch Coverage | Missed | Cxty |
|----------------|---------------------:|----------------:|-------:|-----:|
| remove(Object) |                 100% |             82% |      9 |   29 |

</details>

#### Testcases added
Tests are located in `src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java`

| Test name             |                                                                                                                                                                        Row # in File |
|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| testRemove14          | [1070](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L1070) |
| testRemove15          | [1090](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L1090) |
| testRemove16          | [1110](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L1110) |
| testEquals3           |   [398](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L398) |

------------------------------------------------------------------------

### Teoman [put(K, V)](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L932):

Before improving the coverage, the coverage was 88% as noted above and in the comparison image below.

<details>
    <summary>JaCoCo before improvement </summary>



</details>

<details>
    <summary>JaCoCo After Coverage improvement</summary>



</details>

Below we can see the branch coverage for the put method before and after implemented tests:

<details>
    <summary> Method overview before and after branch coverage </summary>

Before:

| Element             | Instruction Coverage | Branch Coverage | Missed | Cxty |
|---------------------|---------------------:|----------------:|-------:|-----:|
| put(Object, Object) |                 100% |             88% |      5 |   25 |  
After:

| Element             | Instruction Coverage | Branch Coverage | Missed | Cxty |
|---------------------|---------------------:|----------------:|-------:|-----:|
| put(Object, Object) |                 100% |             97% |      1 |   25 |

</details>

#### Testcases added
Tests are located in `src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java`

| Test name       |                                                                                                                                                                      Row # in File |
|-----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| testPut7        | [742](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L742) |
| testPut8        | [769](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L769) |
| testPut9        | [797](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L797) |
| testPut10       | [825](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L825) |

------------------------------------------------------------------------

### Samuel, [equals(Object)](https://github.com/DD2480-Group2/commons-collections/blob/b72b2dd0626b11270393bc1934ac38aded1fcb61/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L756):

The branch coverage analysis of the `equals(Object)` method before new tests were added showed that the following branches were not covered by the existing tests:

- The two maps are of different sizes *(for which a test was added by Kevin Löv)*
- The other map is missing the key `key3`
- The key `key3` is mapped to different values in the two maps
- The other map is missing the key `key1`
- The key `key1` is mapped to different values in the two maps

<details>
    <summary>JaCoCo analysis before coverage improvement</summary>

<img width="400" alt="equals method coverage before" src="https://github.com/DD2480-Group2/commons-collections/blob/report/report-resources/equals_branch_coverage_before.png" />

</details>

<details>
    <summary>JaCoCo analysis after coverage improvement</summary>

<img width="400" alt="equals method coverage after" src="https://github.com/DD2480-Group2/commons-collections/blob/report/report-resources/equals_branch_coverage_after.png" />

</details>

Below we can see the branch coverage for the equals method before and after implemented tests:

<details>
    <summary> Method overview before and after branch coverage </summary>

Before:

| Element        | Instruction Coverage | Branch Coverage | Missed | Cxty |
|----------------|---------------------:|----------------:|-------:|-----:|
| equals(Object) |                  89% |             76% |      6 |   15 |  

After *(including the test was added by Kevin Löv)*:

| Element        | Instruction Coverage | Branch Coverage | Missed | Cxty |
|----------------|---------------------:|----------------:|-------:|-----:|
| equals(Object) |                 100% |             96% |      1 |   15 |

</details>

#### Testcases added
Tests are located in `src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java`

| Test name   |                                                                                                                                                                      Row # in File |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| testEquals4 | [423](https://github.com/DD2480-Group2/commons-collections/blob/b72b2dd0626b11270393bc1934ac38aded1fcb61/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L423) |
| testEquals5 | [452](https://github.com/DD2480-Group2/commons-collections/blob/b72b2dd0626b11270393bc1934ac38aded1fcb61/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L452) |
| testEquals6 | [478](https://github.com/DD2480-Group2/commons-collections/blob/b72b2dd0626b11270393bc1934ac38aded1fcb61/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L478) |
| testEquals7 | [503](https://github.com/DD2480-Group2/commons-collections/blob/b72b2dd0626b11270393bc1934ac38aded1fcb61/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L503) |

-------------------------------------------------------------------------------

### Adam [ConcurrentReferenceHashMap([...])](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMap.java#L1443):

<details>
    <summary>JaCoCo before improvement </summary>



</details>

<details>
    <summary>JaCoCo After Coverage improvement</summary>

<img width="1038" height="670" alt="image" src="https://github.com/user-attachments/assets/443b9954-f940-4fec-97a6-fd00749ce8fd" />

</details>

Below we can see the branch coverage for the ConcurrentReferenceHashMap constructor before and after implemented tests:


<details>
    <summary> Method overview before and after branch coverage </summary>

Before:

| Element                    | Instruction Coverage | Branch Coverage | Missed | Cxty |
|----------------------------|---------------------:|----------------:|-------:|-----:|
| ConcurrentReferenceHashMap |                  91% |             68% |      7 |   12 |  
After:

| Element                    | Instruction Coverage | Branch Coverage | Missed | Cxty |
|----------------------------|---------------------:|----------------:|-------:|-----:|
| ConcurrentReferenceHashMap |                  98% |             90% |      2 |   12 |

</details>

#### Testcases added
Tests are located in `src/test/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMapTest.java`

| Test name                                                               |                                                                                                                                                                                      Row # in File |
|-------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| concurrentReferenceHashMapThrowExeptionWhenConcurrencyLevelIsNegative   | [61](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMapTest.java#L61) |
| concurrentReferenceHashMapThrowExeptionWhenInitialCapacityIsNegative    | [73](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMapTest.java#L73) |
| concurrentReferenceHashMapThrowExeptionWhenLoadFactorIsZero             | [85](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMapTest.java#L85) |
| concurrentReferenceHashMapLimitsConcurrencyLevel                        | [97](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMapTest.java#L97) |

------------------------------------------------------------------------

### Ahmed [get(Object)](https://github.com/DD2480-Group2/commons-collections/blob/9a517a998c44a873d0f25624e765e93a59a02825/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L809):

<details>
    <summary>JaCoCo before improvement </summary>



</details>

<details>
    <summary>JaCoCo After Coverage improvement</summary>


</details>

<details>
    <summary> Method overview before and after branch coverage </summary>

Before:

| Element     | Instruction Coverage | Branch Coverage | Missed | Cxty |
|-------------|---------------------:|----------------:|-------:|-----:|
| get(Object) |                 100% |             84% |      5 |   19 |  
After:

| Element     | Instruction Coverage | Branch Coverage | Missed | Cxty |
|-------------|---------------------:|----------------:|-------:|-----:|
| get(Object) |                 100% |             80% |     12 |   29 |

</details>

#### Testcases added
Tests are located in `src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java`

| Test name |                                                                                                                                                                      Row # in File |
|-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| testGet4  | [551](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L551) |
| testGet5  | [565](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L565) |
| testGet6  | [576](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L576) |
| testGet7  | [588](https://github.com/DD2480-Group2/commons-collections/blob/e9955b940de4c78a02aa229c68ffcd5d69cb579f/src/test/java/org/apache/commons/collections4/map/Flat3MapTest.java#L588) |

------------------------------------------------------------------------

Our branch for instrumented code to gather coverage measurements:
[Development-Testing](https://github.com/DD2480-Group2/commons-collections/tree/development-testing)

To obtain the patch, use the command ```git diff master..development-testing src```

------------------------------------------------------------------------

## 3.5.3 Refactoring
This chapter contains both the plan and the actual refactoring of the methods to reach the P+ requirements.

### Function 1: `remove(final Object key)` line [1060](https://github.com/DD2480-Group2/commons-collections/blob/b697bac27ae25e9b3684f9873a2214467411737d/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L1060)
The function has high cyclomatic complexity due to the number of different branches being explored in the same function.
At the top-level of the function there are four if-statements, where two simply return if true,
while the other two contain switch-statements and many if-statements that increase the cyclomatic complexity.

The refactoring could be carried out by leaving the first two if-statements as is,
while extracting the switch-statements in the second two if-statements and instead calling two methods e.g.
```removeNullKey()``` and ```removeNonNullKey(key, hashCode)```. This would reduce the cyclomatic complexity of
```remove``` from 29, to around 5, where new methods would have CC’s of around 9 and 15 respectively.
Apart from the reduced CC, each method would also have a reduced amount of LOC, making them easier to overview and work with.
The drawbacks would be that it could be harder to follow controlflow when the method makes a lot of calls elsewhere.

Before implementing refactoring:

| Element                                                                                                                                                                                    | Instruction Coverage | Branch Coverage | Missed | Cxty |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------:|----------------:|-------:|-----:|
| [remove(Object)](https://github.com/DD2480-Group2/commons-collections/blob/b697bac27ae25e9b3684f9873a2214467411737d/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L1060) |                  84% |             76% |     12 |   29 |

After implementing refactoring:

| Element                                                                                                                                                                                                   | Instruction Coverage | Branch Coverage | Missed | Cxty |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------:|----------------:|-------:|-----:|
| [remove(Object)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L1101)                |                 100% |            100% |      0 |    4 |  
| [removeNonNullKey(Object, int)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L1183) |                  99% |             71% |      8 |   16 |
| [removeNullKey()](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L1115)               |                  64% |             81% |      3 |   10 |

### Function 2: `put(final K key, final V value)` line [932](https://github.com/DD2480-Group2/commons-collections/blob/b697bac27ae25e9b3684f9873a2214467411737d/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L932)
The `put(final K key, final V value)` has cyclomatic complexity 25. This can easily be explained by the number of if-checks and switch cases occurring in the function. More specifically, there are some if-checks that have a boolean condition &&, which causes 4 branches for each of these if-checks. Therefore, these if-checks are good contenders for reducing the CC.

To reduce the CC by at least 35%, the maximum allowed CC after refactoring is 17. For code that has a single if-check and little CC inside its block, it does not seem worthwhile to refactor since its only contribution is a branch value of 1. Instead, more focus can be placed on longer switch statements, and the if-checks with &&.

The if-check with && can easily be refactored into its own function as it is simply comparing two hashcodes and two keys and returning true if they are equal, or false if they are not. This refactoring is quite worthwhile since it is trivial and reduces the CC a lot with little effort. Besides this, there are three switch-statements that can be extracted into their own respective three functions. Since each of these switch-statements contain three cases, then combined they should reduce the CC of about 9-12 (depending on whether default is considered or not).

As for whether it is worthwhile to refactor in such a way, it is easier to argue for the extraction of the if-check with && conditions as they can be said to increase readability and strengthen the semantics while reducing the CC significantly. As for the switch-statements, then this is really a matter of taste. When extracting methods, it is important to consider that the method names should benefit the semantics of the code. If the methods are badly named, then they can be counterproductive. Also, having to take a lot of steps to understand the code can also hinder productivity. Since the switch-statements also have multiple exit points, it can be a bit more cumbersome to refactor, and one has to be careful that the refactoring doesn’t change the logic or break tests etc. At the end of the day, simply extracting a method and calling upon it—although technically decreasing the CC—in my opinion doesn’t seem enough of a justification by itself, and readability and logic here should be considered more importantly.

Before implementing refactoring:

| Element                                                                                                                                                                              | Instruction Coverage | Branch Coverage | Missed | Cxty |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------:|----------------:|-------:|-----:|
| [put(K, V)](https://github.com/DD2480-Group2/commons-collections/blob/b697bac27ae25e9b3684f9873a2214467411737d/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L932) |                 100% |             88% |      5 |   25 |

After implementing refactoring:

| Element                                                                                                                                                                                                                   | Instruction Coverage | Branch Coverage | Missed | Cxty |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------:|----------------:|-------:|-----:|
| [put(K, V)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L944)                                      |                 100% |            100% |      0 |    7 | 
| [updateIfNullKeyFound(V)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L970)                        |                 100% |             90% |      1 |    7 | 
| [updateIfKeyMatches(K, V, int)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L994C21-L994C69)       |                 100% |             90% |      1 |    7 |
| [keysAreEqual(K, int, K, int)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L1020C21-L1020C81)      |                 100% |             75% |      1 |    3 |
| [convertedToDelegateOnInsert(K, V)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L1024C21-L1024C64) |                 100% |            100% |      0 |    7 |

### Function 3: `equals(final Object obj)` line [756](https://github.com/DD2480-Group2/commons-collections/blob/b697bac27ae25e9b3684f9873a2214467411737d/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L756)

The `equals(Object)` method in the `Flat3Map` class has relatively high cyclomatic complexity due to multiple if-statements and repeated logic for comparing individual key-value pairs.

To reduce the method’s cyclomatic complexity, the refactoring would primarily focus on extracting both the switch-statement, as well as the repeated logic within each of its cases. By doing this, the cyclomatic complexity of the  `equals(Object)` method would be significantly reduced.

The cyclomatic complexity of the method could be further reduced by extracting the individual if-statements into separate, or a single, new methods, however, the benefits of doing so may be questioned when taking for example readability into consideration.

Before implementing refactoring:

| Element                                                                                                                                                                                   | Instruction Coverage | Branch Coverage | Missed | Cxty |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------:|----------------:|-------:|-----:|
| [equals(Object)](https://github.com/DD2480-Group2/commons-collections/blob/b697bac27ae25e9b3684f9873a2214467411737d/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L756) |                  89% |             76% |      6 |   15 |  

After implementing refactoring:

| Element                                                                                                                                                                                                     | Instruction Coverage | Branch Coverage | Missed | Cxty |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------:|----------------:|-------:|-----:|
| [equals(Object)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L760)                   |                  93% |             87% |      1 |    5 | 
| [entriesEqual(Map)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L777)                |                  89% |             75% |      3 |    8 | 
| [entryEquals(Map, Object, Object)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L797) |                 100% |            100% |      0 |    2 |

### Function 4: `[ConcurrentReferenceHashMap([...])` line [1443](https://github.com/DD2480-Group2/commons-collections/blob/b697bac27ae25e9b3684f9873a2214467411737d/src/main/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMap.java#L1443)
This method has high complexity with a CC of 11 which is close to being a safe/medium complexity if we draw the line at 10. A lot of the complexity comes from ensuring the parameters are correct (the first line is an if-statement making sure 3 of the parameters are greater than or equal to 0 or 1 with “||”). There are also some loops and if statements that caps the value of concurrencyLevel and initialCapacity.

To lower the CC the if statements to cap the value of the parameters was replaced with a Math.min() function and since 2 of the loops was to check the smallest power of 2 that was greater or equal to a value, it was turned into a helper function called ceilingPowerOfTwo with a while loop (CC = 2). This along with replacing a if-statement that rounded up a fraction resulted in the function having a CC of 6, a decrease by 45%.

Before implementing refactoring:

| Element                                                                                                                                                                                                                         | Instruction Coverage | Branch Coverage | Missed | Cxty |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------:|----------------:|-------:|-----:|
| [ConcurrentReferenceHashMap([...])](https://github.com/DD2480-Group2/commons-collections/blob/b697bac27ae25e9b3684f9873a2214467411737d/src/main/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMap.java#L1443) |                  91% |             68% |      7 |   12 |

After implementing refactoring:

| Element                                                                                                                                                                                                                           | Instruction Coverage | Branch Coverage | Missed | Cxty |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------:|----------------:|-------:|-----:|
| [ConcurrentReferenceHashMap([...])](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMap.java#L1443)   |                  95% |             66% |      4 |    7 | 
| [ceilingPowerOfTwo(int)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/ConcurrentReferenceHashMap.java#L1473C17-L1473C45)  |                 100% |            100% |      0 |    2 |

### Function 5: `get(final Object key)` line [809](https://github.com/DD2480-Group2/commons-collections/blob/b697bac27ae25e9b3684f9873a2214467411737d/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L809)
The function has high cyclomatic complexity due to the number of different branches being explored in the same function. At the top-level of the function there are 3 if-statements, where 1 simply return if true, while the 2 others contain switch-statements and many if-statements that increase the cyclomatic complexity.

The refactoring could be carried out by leaving the first if-statement as is, while extracting the switch-statements in the 2 other if-statements and instead calling two methods e.g. ```getNullKey()``` and ```getNonNullKey(key, hashCode)```. This would reduce the cyclomatic complexity of ```get``` from 19, to around 3.

Before implementing refactoring:

| Element                                                                                                                                                                                | Instruction Coverage | Branch Coverage | Missed | Cxty |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------:|----------------:|-------:|-----:|
| [get(Object)](https://github.com/DD2480-Group2/commons-collections/blob/b697bac27ae25e9b3684f9873a2214467411737d/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L809) |                 100% |             84% |      5 |   19 |

After implementing refactoring:

| Element                                                                                                                                                                                                          | Instruction Coverage | Branch Coverage | Missed | Cxty |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------:|----------------:|-------:|-----:|
| [get(Object)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L812)                           |                 100% |            100% |      0 |    3 | 
| [getNullKey()](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L823C15-L823C27)               |                 100% |             90% |      1 |    7 | 
| [getNonNullKey(Object, int)](https://github.com/DD2480-Group2/commons-collections/blob/520ed9d349f88f82048e6a61f755be1adfcba9cd/src/main/java/org/apache/commons/collections4/map/Flat3Map.java#L842C15-L842C54) |                 100% |             81% |      3 |   10 |

------------------------------------------------------------------------

Our branch for refactored functions:
[Development-Refactoring](https://github.com/DD2480-Group2/commons-collections/tree/Development-Refactoring)

To obtain the patch, use the command ```git diff master..Development-Refactoring```

## 3.5.4 Self-assessment: Way of working

### Current state according to the Essence standard

At the time of writing, we assess our team to be in the **Working well**-phase, and we think that is proved by the fact that even though we have decided to change how to work on some parts, the core foundation of how we work has allowed us to make those changes without any friction or trouble. The team now performs their tasks without hesitation about how to go about it, but naturally do it the way the team has previously agreed on. We went through the first three phases very early on during the project since the deadline of this project was a little closer than the previous ones, and more pressure was put on the team to decide on projects, tools and approach. Some decisions of finalizing the hand-in has to be made before we can move on to the **retired**-phase.

### Was the self-assessment unanimous? Any doubts about certain items?

Yes. The team's overall decision to begin with the project early was very appreciated and will be used for the final assignment to make sure we have the time we need to move through the stages and work together to the best of our ability.

### How have you improved so far?

With the experience from working together in the previous two assignments, our team has improved in many areas, particularly in coordination, tool usage, and efficiency. Familiarity with the chosen project’s tools allowed for faster onboarding and smoother collaboration.

Having previously finalized the work in the previous assignments close to their deadlines, we were for this assignment better at time planning and committing to finishing our work ahead of time, without having to rush finalizing any parts of the assignment last-minute.

### Where is potential for improvement?

Although we are mostly in the **Working well** state, our team could improve in communication across the team and consistency in participation. Moving on, we will focus to better ensure that we fulfill the requirements in **Principles Established** and **Foundation Established**, to allow each member to get started working as early as possible and for the work to be useful in a bigger picture in regards to the rest of the team's work. It feels like when these things are decided our team is very efficient and gets the work done without any problem and reaching the **Working well** stage again will not be a problem.

## 3.6 Overall experience

For at least one group member it was the first time using tools like JaCoCo to visualize how much of the actual code that the tests touch, and also that it is not very hard to implement your own way of checking how much the tests touch.

Overall fun assignment and very different from many previous tasks other courses have had, and I felt like it proved the importance of proper test coverage in a new way.
