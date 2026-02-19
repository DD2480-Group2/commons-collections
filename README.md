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

### Complex function 1: remove @src/main/java/…\map\Flat3Map.java
1. Both group members who counted the functions CC independently got the same result of 29, which also aligned with the output from Lizard.
2. In combination with its high CC, the function also has many LOC with about 140. This is much higher than the average function in the project according to lizard who reported an average of 7 LOC per function. 
3. The purpose of the remove-function is to remove an element from a performance-optimized Map which works faster than HashMaps when there are <=3 elements used. Since the implementation of the Map uses the classes fields to store key- and value data, the functions large CC comes from handling a lot of edge cases and the function trades simplicity for efficiency.
4. The remove function does not throw an exception, but if it did that would be a potential new branch and it would increase the CC by 1 for each exception that could be thrown.
5. There is documentation at the top level of the function for users using the method, but within the function itself, there is near to no documentation. So there is no explanation of how the function handles branching and the different outcomes based on what branch that will be taken. But from a high level, it is clear what the function will do and return given the input parameter.

### Complex function 2: put@src/main/java/…/map/Flat3Map.java
1. Teoman: 25 + 1, Samuel: 25 + 1
Jacoco and Lizard both give 25.
In other words, the manual count differs from the tool by one. The reason for this could probably be explained by the fact that there is a default case, which is probably not counted by Jacoco and Lizard. Seeing as I consider the default case as a branch, then this increased the value by one.
2. Yes, that generally seems to be the case. As for the put function, then it has 49 lines, and its complexity is 25. In the Flat3Map class, then it is the function with the second most number of lines of code, as well as being the function with the second highest in terms of complexity.
3. The purpose of this function, as noted by the JavaDoc, is to put a value at a specific key in the map. The number of lines in the function can be explained by the fact that the implementation of put in the Flat3Map class assumes a maximum size of 3. Therefore, there are switch cases that have to cover these different size cases. There is also the case where you call put on an existing key and when the key is null.
4. The put function does not throw any exceptions. After surrounding some of the code in a try-catch block, then the complexity is increased by one according to Lizard, as the catch block becomes its own branch. The try statement does not contribute to the complexity as the try is always executed first, meaning there is no other path of execution here as the try is always executed.
5. Yes, somewhat. There are comments above important branches that explain the different edge cases of the function. Besides this, the JavaDoc is not explicit as to the details of the function.

### Complex function 3: equals@src/main/java/…/map/Flat3Map.java
1. Initially, one of the group members had miscounted the function’s CC, but fairly quickly realized they had forgotten to include one of the decision points, after which both group members’ results matched the CC of 15 as reported by Lizard.
2. This function has a length of 45 LOC. As such, it is not very long, considering its relatively high CC. In contrast to the first complex function used as an example, `remove()`, this function is significantly shorter relative to each function’s respective CC.
3. The `equals()` function checks equality between two Map objects by verifying their respective sizes, keys and values. The large CC is due to multiple checks like `if (obj == this)`, `if (!(obj instanceof Map))`, and `if (size > 0)`, as well as separate cases for different sizes (1, 2, or 3), each with separate branches for checking both key existence and value equality.
4. This function in particular does not include any exceptions, however, if it did, Lizard would take it into account as long as it’s in a try-catch block.
5. There is minimal documentation for the function as a whole, “Compares this map with another. [...] @return true if equal”. Although it doesn’t explicitly describe the specific cases leading to a false return value, e.g. size differences, absent keys, or non-equal values, its behavior is still clear considering the limited number of possible outcomes, and given the relatively readable code, there isn’t a significant need for much documentation. Still, the function could use some additional documentation than what is currently present.

### Complex function 4: ConcurrentReferenceHashMap@src/main/java/…/map/ConcurrentReferenceHashMap.java
This function was chosen since JaCoCo calculated the complexity to be 12 but both manually counting and lizard got the CC to be 11. This was a bit surprising since we believed that JaCoCos complexity was the same as CC but since they write Cxty instead of CC it is possible they calculate complexity in some other way. Lizard does however call their complexity CCN (Cyclomatic Complexity Number) so we assume that 11 is the correct number of CC.
The function had 47 LOC meaning it is longer than most functions, even slightly longer than Complex function 3 that is more complex (15 CC).
It is a constructor for the class ConcurrentReferenceHashMap. It creates a new, empty map with the specified initial capacity, reference types, load factor, and concurrency level. The map is divided in segments so different threads can safely access different parts of the map at the same time. 
No, this function does throw an exception but it does not increase CC since it is not in a try-catch block but rather is used as a return if the first if-statement is true. 
Yes, the documentation is very clear. However, I would have added information about the max cap on concurrencyLevel and initialCapacity.

### Complex function 5:
1. The manual calculation of the CC matches the result reported by Lizard (CC = 19).
2. This function has 38 LOC which seems to be relatively low if we compare it to the previous ones. It is indeed more complex than equals() but has fewer LOC.
3. It returns the value associated with key, using a fast manual lookup for up to 3 elements, and a normal HashMap lookup once the map grows larger.
4. Although this function contains no exception handling, Lizard would include it in the complexity calculation if it were implemented inside a try–catch block.
5. No. The Javadoc states the general contract (returns the mapped value or null), but it does not describe the different behaviors caused by the internal branches (flat storage vs delegated map, special handling of null, etc.). It is functionally correct but not detailed about the various execution paths.



## 3.5 Refactoring

### Function 1: remove  
The function has high cyclomatic complexity due to the number of different branches being explored in the same function. At the top-level of the function there are four if-statements, where two simply return if true, while the other two contain switch-statements and many if-statements that increase the cyclomatic complexity. 

The refactoring could be carried out by leaving the first two if-statements as is, while extracting the switch-statements in the second two if-statements and instead calling two methods e.g. ```removeNullKey()``` and ```removeNonNullKey(key, hashCode)```. This would reduce the cyclomatic complexity of ```remove``` from 29, to around 5, where new methods would have CC’s of around 9 and 15 respectively. Apart from the reduced CC, each method would also have a reduced amount of LOC, making them easier to overview and work with. The drawbacks would be that it could be harder to follow controlflow when the method makes a lot of calls elsewhere.

### Function 2: put 

### Function 3: `equals(Object)`
The `equals(Object)` method in the `Flat3Map` class has relatively high cyclomatic complexity due to multiple if-statements and repeated logic for comparing individual key-value pairs.

To reduce the method’s cyclomatic complexity, the refactoring would primarily focus on extracting both the switch-statement, as well as the repeated logic within each of its cases. By doing this, the cyclomatic complexity of the  `equals(Object)` method would be significantly reduced.

The cyclomatic complexity of the method could be further reduced by extracting the individual if-statements into separate, or a single, new methods, however, the benefits of doing so may be questioned when taking for example readability into consideration.

### Function 4: ConcurrentReferenceHashMap (Adam, aiming for P+)
This method has high complexity with a CC of 11 which is close to being a safe/medium complexity if we draw the line at 10. A lot of the complexity comes from ensuring the parameters are correct (the first line is an if-statement making sure 3 of the parameters are greater than or equal to 0 or 1 with “||”). There are also some loops and if statements that caps the value of concurrencyLevel and initialCapacity. 

To lower the CC the if statements to cap the value of the parameters was replaced with a Math.min() function and since 2 of the loops was to check the smallest power of 2 that was greater or equal to a value, it was turned into a helper function called ceilingPowerOfTwo with a while loop (CC = 2). This along with replacing a if-statement that rounded up a fraction resulted in the function having a CC of 6, a decrease by 45%.  

Before:
<img width="1473" height="127" alt="image" src="https://github.com/user-attachments/assets/d45418d1-4604-473e-8faa-2d031edf6859" />

After:
<img width="1457" height="163" alt="image" src="https://github.com/user-attachments/assets/c135fb21-2b22-4b2a-bef0-b041f6664dd8" />


### Function 5: get
The function has high cyclomatic complexity due to the number of different branches being explored in the same function. At the top-level of the function there are 3 if-statements, where 1 simply return if true, while the 2 others contain switch-statements and many if-statements that increase the cyclomatic complexity. 

The refactoring could be carried out by leaving the first if-statement as is, while extracting the switch-statements in the 2 other if-statements and instead calling two methods e.g. ```getNullKey()``` and ```getNonNullKey(key, hashCode)```. This would reduce the cyclomatic complexity of ```get``` from 19, to around 3.


### Carried out refactoring (optional, P+):

#### Kevin
<img width="912" height="61" alt="image" src="https://github.com/user-attachments/assets/3ac9c040-a559-4264-8f0e-73f8548ce84c" />

#### Samuel

**Before**

![equals method refactoring before](https://github.com/DD2480-Group2/commons-collections/blob/report/report-resources/equals_coverage_before.png)

**After**

![equals method refactoring after](https://github.com/DD2480-Group2/commons-collections/blob/report/report-resources/equals_refactor_after.png)

Our branch for refactored functions:
[Development-Refactoring](https://github.com/DD2480-Group2/commons-collections/tree/Development-Refactoring)

To obtain the patch, use the command ```git diff master..Development-Refactoring```

## Coverage

### Tools
We used JaCoCo as our automated coverage measurement tool. JoCoCo was already integrated into the project through Maven, which made it straightforward to use. We generated a coverage report by running ```mvn test``` and then ```mvn jacoco:report```.
 
JaCoCo then produced a HTML report that provided information about instruction coverage, branch coverage and other key-values for analyzing the repo both on class level and on method level.

<img width="906" height="412" alt="image" src="https://github.com/user-attachments/assets/b2748d22-448c-4471-8883-644861f91d14" />


### Your own coverage tool
We implemented a simple manual branch coverage tool by instrumenting the selected high CC functions. Each branch that gets explored contains a call to ```Coverage.hit(ID)``` which sets a boolean variable in place ID to true, signifying that we have explored that branch. After the test has run all true values will output their index, showing what branches were explored and what branches that were not covered.

Our branch for instrumented code to gather coverage measurements:
[Development-Coverage branch](https://github.com/DD2480-Group2/commons-collections/tree/Development-Coverage)

To obtain the patch, use the command ```git diff master..Development-Coverage```

Our tool supports `if`/`else` statements and `switch` cases, it can also handle loops but only as a way of seeing if at least one iteration of the loop was performed. 

### Evaluation

1. How detailed is your coverage measurement?
Our tool provides information about what branches have been explored in the instrumented functions through conditional-statements and switch-statements. The level of detail is rather high when it comes to what branch has been taken, but it does not cover anything more than that.

2. What are the limitations of your own tool?
	The limitations in our implemented tool lies partly in when if-statements contain multiple conditionals connected by ```&&``` and ```||```, since our tool can only explore the branches the code goes to, we do not explore the different combinations of conditionals inside the if-statement, but only when the if-statement results in true or false. There is also currently limitations regarding how loops are handled, since we only explore different branches, we can only explore if we enter a loop or not, but not how many times we iterate.

but we can not explore some of the branches that JaCoCo explores through conditional statements including ```&&``` & ```||```. Due to this our output might be interpreted as if a higher percentage of the branches are being covered, compared to JaCoCo’s output. So our 

3. Are the results of your tool consistent with existing coverage tools?
	The output is somewhat aligned with what JaCoCo outputs with exceptions for when the code contains if-statements containing multiple conditionals connected through ```&&``` and ```||```, since JaCoCo explores all possible combinations of parameters for the if-statement, our tool simply explores the if-statement as true or false. This makes it so our tool tells a story of a higher branch coverage than might be present, whereas JaCoCo will determine more possible branch outcomes.


## Coverage improvement
### Show the comments that describe the requirements for the coverage.
#### Kevin:
The parts of the remove method that were not covered through the original tests were two checks for when the Map was full (3 elements) and we try to remove either key 1 or key 2 when the key itself is null. Below we can see the branch coverage for the remove method:

<img width="906" height="89" alt="image" src="https://github.com/user-attachments/assets/7a2f737d-e494-47ed-869f-aba476500cec" />
and after:
<img width="912" height="86" alt="image" src="https://github.com/user-attachments/assets/64056e0d-83bd-4478-b186-ea76a6aece7d" />


Javadocs for the two methods covering the case above can be found below.
```
/** 
* Requirement: If the map contains a mapping for the null key,
* remove(null) should return associated value and remove the entry from the map
*
* The map contains three key-value pairs, 
* of which the second has the null-key bound to value TWO.
* When calling remove(null) the function should return the value 
* which is TWO and remove the entry
*/
```
```   
/**
* Requirement: If the map contains a mapping for the null key,
* remove(null) should return associated value and remove the entry from the map
*
* The map contains three key-value pairs, 
* of which the first has the null-key bound to value ONE.
* When calling remove(null) the function should return the value 
* which is ONE and removes the entry.
*/
```
Remove also missed a branch in which the Map we wanted to remove an element from only has 1 element, and the key is not null and also matches the key of the element in the Map. Javadocs for that method can be found below:
```
/**
* Requirement: If this map and other map are not of the same size
* the method this.equal(other) should return false.
*
* This map contains three entries and other only contains 2 entries.
* When calling this.equals(other) the method should return false, since they are not of the same size
*/
```
I also implemented a test for the equals method where no previous test confirmed that if the compared Maps had different size, the method should return false. Javadoc for this method:
```
/**
* Requirement: If this map and other map are not of the same size
* the method this.equal(other) should return false.
*
* This map contains three entries and other only contains 2 entries.
* When calling this.equals(other) the method should return false, since they are not of the same size
*/
```

#### Teoman:

The current branch coverage of put is 88%.


#### Samuel:

The branch coverage analysis of the `equals(Object)` method before new tests were added showed that the following branches were not covered by the existing tests:

- The two maps are of different sizes *(for which a test was added by Kevin Löv)*
- The other map is missing the key `key3`
- The key `key3` is mapped to different values in the two maps
- The other map is missing the key `key1`
- The key `key1` is mapped to different values in the two maps

The following was the branch coverage of the method as reported by JaCoCo before new tests were added:

![equals method coverage before](https://github.com/DD2480-Group2/commons-collections/blob/report/report-resources/equals_coverage_before.png)

After implementing new tests, including the test created by Kevin Löv, the following branch coverage was achieved:

![equals method coverage after](https://github.com/DD2480-Group2/commons-collections/blob/report/report-resources/equals_coverage_after.png)

The following JavaDoc comments are related to the 4 test methods I wrote, addressing the latter branches that were previously not covered:


```
/**
 * Requirement: The equals(Object) method should return false if
 * the other map is missing a key that is present in this map.
 *
 * <p>
 * This test creates two Flat3Map instances which differ only in
 * key3. When equals(Object) checks whether the other map contains
 * key3 by evaluating the condition (!other.containsKey(key3)),
 * the method should return false.
 * </p>
 */
@Test
void testEquals4() {
[...]
```

```
/**
 * Requirement: The equals(Object) method should return false if
 * a key in the other map is mapped to a different value than
 * the one present in this map.
 *
 * <p>
 * This test creates two Flat3Map instances which differ only in
 * the value mapped to key3. When equals(Object) checks whether
 * key3 in the other map is mapped to the same value as its own
 * by evaluating the condition
 * (!Objects.equals(value3, otherValue)), the method should
 * return false.
 * </p>
 */
@Test
void testEquals5() {
[...]
```

```
/**
 * Requirement: The equals(Object) method should return false if
 * the other map is missing a key that is present in this map.
 *
 * <p>
 * This test creates two Flat3Map instances which differ only in
 * key1. When equals(Object) checks whether the other map contains
 * key1 by evaluating the condition (!other.containsKey(key1)),
 * the method should return false.
 * </p>
 */
@Test
void testEquals6() {
[...]
```

```
/**
 * Requirement: The equals(Object) method should return false if
 * a key in the other map is mapped to a different value than
 * the one present in this map.
 *
 * <p>
 * This test creates two Flat3Map instances which differ only in
 * the value mapped to key1. When equals(Object) checks whether
 * key1 in the other map is mapped to the same value as its own
 * by evaluating the condition
 * (!Objects.equals(value1, otherValue)), the method should
 * return false.
 * </p>
 */
@Test
void testEquals7() {
[...]
```

#### Adam:
Test coverage before:
<img width="1038" height="788" alt="image" src="https://github.com/user-attachments/assets/77f74493-220c-4821-8061-6c4ac704c429" />

Test coverage after:
<img width="1038" height="670" alt="image" src="https://github.com/user-attachments/assets/443b9954-f940-4fec-97a6-fd00749ce8fd" />

Tests comments:
```
/**
* test that a negative concurrencyLevel throws the exeption IllegalArgumentException
*/
```
```
/**
* test that a negative initialCapacity throws the exeption IllegalArgumentException
*/
```
```
/**
* test that loadFactor equals zero results in the thrown exeption IllegalArgumentException
*/
```
```
/**
* test that ConcurrentReferenceHashMap limits concurrencyLevel to max (1 << 16) if it is set to something higher than that
*/
```

#### Ahmed:
Before: 
<img width="1424" height="66" alt="image" src="https://github.com/user-attachments/assets/db1493b2-7126-4491-bd92-b8b7d807762f" />

After:
<img width="1428" height="52" alt="image" src="https://github.com/user-attachments/assets/03415324-b7c5-4e13-bc1e-fa6f65bf5a19" />



### Report of old coverage:

<img width="1046" height="206" alt="image" src="https://github.com/user-attachments/assets/5e025521-f4db-41ee-b256-0527e0eab51f" />


### Report of new coverage:

<img width="1049" height="231" alt="image" src="https://github.com/user-attachments/assets/21f63617-f165-420e-b130-a91eaea0af74" />


### Test cases added:

#### Adam:

- concurrentReferenceHashMapThrowExeptionWhenConcurrencyLevelIsNegative
- concurrentReferenceHashMapThrowExeptionWhenInitialCapacityIsNegative
- concurrentReferenceHashMapThrowExeptionWhenLoadFactorIsZero
- concurrentReferenceHashMapLimitsConcurrencyLevel	

#### Ahmed:

- testGet4
- testGet5
- testGet6
- testGet7

#### Kevin:

	- testRemove14 (removeNull_returnsValue1_whenKey1_isNull_andSizeIs3)
	- testRemove15 (removeNull_returnsValue2_whenKey2_isNull_andSizeIs3)
	- testRemove16 (remove_returnsNull_whenMapSizeIsOne_andParamNotKeyInMap)
	- testEquals3     (equals_returnsFalse_whenMapsAreDifferentSize)

#### Samuel:

- testEquals4
- testEquals5
- testEquals6
- testEquals7

#### Teoman:

	-
	-
	-
	-

---

### git diff …

[Development-Testing](https://github.com/DD2480-Group2/commons-collections/tree/development-testing)

```git diff master..development-testing```

## Self-assessment: Way of working

### Current state according to the Essence standard

At the time of writing, we assess our team to be in the **Working well**-phase, and we think that is proved by the fact that even though we have decided to change how to work on some parts, the core foundation of how we work has allowed us to make those changes without any friction or trouble. The team now performs their tasks without hesitation about how to go about it, but naturally do it the way the team has previously agreed on. We went through the first three phases very early on during the project since the deadline of this project was a little closer than the previous ones, and more pressure was put on the team to decide on projects, tools and approach. Some decisions of finalizing the hand-in has to be made before we can move on to the **retired**-phase.

### Was the self-assessment unanimous? Any doubts about certain items?

Yes. The team's overall decision to begin with the project early was very appreciated and will be used for the final assignment to make sure we have the time we need to move through the stages and work together to the best of our ability.

### How have you improved so far?

With the experience from working together in the previous two assignments, our team has improved in many areas, particularly in coordination, tool usage, and efficiency. Familiarity with the chosen project’s tools allowed for faster onboarding and smoother collaboration.

Having previously finalized the work in the previous assignments close to their deadlines, we were for this assignment better at time planning and committing to finishing our work ahead of time, without having to rush finalizing any parts of the assignment last-minute.

### Where is potential for improvement?

Although we are mostly in the **Working well** state, our team could improve in communication across the team and consistency in participation. Moving on, we will focus to better ensure that we fulfill the requirements in **Principles Established** and **Foundation Established**, to allow each member to get started working as early as possible and for the work to be useful in a bigger picture in regards to the rest of the team's work. It feels like when these things are decided our team is very efficient and gets the work done without any problem and reaching the **Working well** stage again will not be a problem.


## Overall experience

For one group member it was the first time using tools like JaCoCo to visualize how much of the actual code that the tests touch, and also that it is not very hard to implement your own way of checking how much the tests touch. 

Overall fun assignment and very different from many previous tasks other courses have had, and I felt like it proved the importance of proper test coverage in a new way.
