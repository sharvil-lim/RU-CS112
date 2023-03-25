## Infinity War – 100 course points

The purpose of this assignment is to practice your understanding of graphs and the adjacency matrix graph storage.

**This assignment WILL TAKE A SUBSTANTIAL AMOUNT OF TIME TO COMPLETE.**

Start your assignment early! **You need time to understand the assignment** and to answer the many questions that will arise as you read the description and the code provided.

Refer to our Programming [Assignments FAQ](https://ds.cs.rutgers.edu/assignment-faq/) for instructions on how to install VSCode, how to use the command line and how to submit your assignments.

## Overview

We do not expect that you have seen the movie in order to complete this assignment. This description will give you enough background information for you to understand how to complete each task. 

In the MCU (Marvel Cinematic Universe), right after the Big Bang occurred there were 6 singularities contained within Infinity Stones that were spread across the infant universe. 

These stones include:

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-16-at-8.06.53-PM.png)

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-16-at-8.07.04-PM.png)

**Backstory of Thanos**

Before the events of _Avengers: Infinity War_, a titan named Thanos witnessed the death of his home planet due to overpopulation. Reeling from his experience, Thanos made it his life mission to exterminate half of all life in the universe to delay overpopulation on other worlds like his. Thanos determined that using the 6 Infinity Stones which he could hold in his Infinity Gauntlet, a simple snap of his fingers would painlessly eliminate 50% of all life, and he can finally rest in peace victorious.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-16-at-8.07.14-PM.png)

**Background Information and Characters**

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-16-at-8.14.03-PM-426x1024.png)

### Implementation Notes

The structure of this assignment is quite different from the previous assignments.

-   **DO NOT** use static variables on your code.
-   In each given Java class, you will read from a given set of input files (passed in as command line arguments), and write to a given output file (passed in as a command line argument).
-   **DO NOT** change the names of any of the given Java files, or the project structure itself (do not change directory names or create new directories).
-   **DO NOT** remove the package statement from any of the given input files.
-   **DO NOT** use System.exit() in your code.
-   Unlike any previous assignment, **YOU MAY** (and should) create your own classes in the src/avengers folder. **YOU MAY** import “java.util.\*”, but **DO NOT** import anything else. Make sure any new classes have a package statement: `package avengers;`
-   The classes that you create **MAY NOT** have spaces in their names.
-   In order to grade a problem, we run the corresponding Java class and verify the output file. This means you have full freedom in your project structure, as long as our provided classes output the correct answer to the correct output file. Take this opportunity to practice your project design skills, and write clean code that avoids redundancy.
-   **DO NOT** remove the package statement from the provided classes.

### Using StdIn and StdOut

-   Use **StdIn**.setFile(fileName) to set the current input file you want to read from.
-   You can now use methods like **StdIn**.readInt(), **StdIn**.readString() and **StdIn**.readLine() to operate on the input file as if it was standard input.
-   The methods **StdIn**.readInt() and **StdIn**.readString() actually leave the newline character unread, so if you use **StdIn**.readLine() after one of these methods, it will read this character rather than the next line. If you want to read the next line with **StdIn**.readLine(), you will need to call **StdIn**.readLine() once to read the newline character and then again to read the next line. **StdIn**.readInt() and **StdIn**.readString() ignore spaces and newlines by default.
-   Use **StdOut**.setFile(fileName) to set the current output file you want to write to. It creates the file if it doesn’t already exist.
-   You can now use methods like **StdOut**.print() and **StdOut**.println() to operate on the output file as if it was standard output.
-   Autolab **ignores** empty lines and extra spaces the your output files may have.

### Overview of Files

1.  **_ForgeStormBreaker.java_**  
    -   Print to the output file the sum of the 2D array from the input file.
2.  **_LocateTitan.java_**  
    1.  Create the adjacency matrix using the input file. Each vertex represents a generator.
    2.  Divide each value in the adjacency matrix (so matrix\[I\]\[j\]) with the functionality values of  
        vertex i and vertex j (meaning functionalities\[i\] and  
        functionalities(\[j\])
    3.  Use Dilkstra’s algorithm to find the minimum cost from **vertex 0** and the **last vertex**.
    4.  Print the minimum cost to the output file.
3.  _**MindStoneNeighborNeurons.java**_
    1.  Create a graph representation from the input file. Each vertex represents a nerve and each edge represents a synapse. All the vertices have a varying out-degree EXCEPT the **Mind Stone vertex which has out-degree O**.
    2.  Find the Mind Stone vertex, and identify the vertices that neighbor (have an edge) the Mind Stone vertex.
    3.  Print Mind Stone vertex neighbors to the output file.
4.  _**UseTimeStone.java**_
    1.  Create the adjacency matrix using the input file. Each vertex is an event, each path with connecting vertices is a  
        timeline. Each vertex has an EU value.
    2.  Determine the TOTAL number of timelines.
    3.  Determine the number of timelines where _sum\_of\_EU\_of\_all\_vertices\_in\_the\_timeline >= threshold EU_.
    4.  Print the number from step (3) to the output file.
5.  _**PredictThanosSnap.java**_
    1.  Create the adjacency matrix using the input file. Each vertex represents a person, and each edge represents a connection.
    2.  Use the StdRandom.uniform() function and the given pseudocode to determine which vertices to delete.
    3.  Delete the vertices (this can be done directly on the adjacency matrix).
    4.  Determine if the resulting graph is connected. A graph is connected with there is a path from each vertex to every other vertex. 
    5.  Print **true** to the output file if the graph is connected, **false** otherwise.

### Tasks

### 1\. ForgeStormBreaker

To forge the Stormbreaker, Thor has to endure the power of a Neutron Star near Nidavellir.  The star’s plasma rushes through the opening that Thor is inside, as depicted in the image to the right.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-2.57.31-PM.png)

In physics, _flux_ is defined as the amount of fluid (or field strength) flowing through an area.

In the image to the right, the box with the matrix of numbers shows the _flux intensity_ of the sun’s plasma through the opening that Thor is inside. Taking the sum of all the numbers reveals the total flux of the plasma through the opening. 

Thus, **the flux would be (3 + 4 + 5 + 1 + 2) + (12 – 1 + 7 + 2 + 8) + (11 – 6 + 3 + 5 + 2) = 58.** 

**Task****:**  given a 2D array where the values are the flux intensity data of the Neutron Star, calculate the total flux that Thor has to endure. Write this number into the output file. 

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-3.23.43-PM.png)

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-3.24.00-PM.png)

Some preconditions:

-   The numbers in the 2D array are guaranteed to be integers.

-   The dimensions of the 2D array may not be the same.

On Earth, Vision (who has the Mind Stone contained in his head) is protected by the Scarlet Witch (Wanda Maximoff) against more of Thanos’ forces as they try to acquire the Mind Stone. They are joined by Captain America (Steve Rogers), Falcon, and Black Widow (Natasha Romanoff). 

-   [Scarlet Witch & Vision vs The Black Order | The Avengers: Infinity War](https://www.youtube.com/watch?v=7b7fBshO630)

Meanwhile, the Guardians who did not go to Nidavellir (basically Peter Quill, Gamora, Drax, and Mantis) head to Knowhere to prevent Thanos from acquiring the Reality Stone. However, this fails as Thanos has already gained the Reality Stone and uses it to trick the Guardians into an illusion, and captures Gamora (Thanos’ daughter) since she knows the location of the Soul Stone.  

1.  [GOTG Arrives at Knowhere – Thanos Tortures Collector Scene – Avengers: Infinity War Movie Clip HD](https://www.youtube.com/watch?v=z-IWeiD9sJI)
2.  [Avengers: Infinity War | Knowhere Scene | Thanos: “I like you.”](https://www.youtube.com/watch?v=-uKtIo3RrFs&t=169s)

Thanos tortures his daughter Nebula until Gamora reveals that the Soul Stone is in Vormir. Afterwards, Thanos and Gamora depart there while Nebula begs the Guardians to meet her on Titan, Thanos’ homeworld. 

-   [Thanos Tortures Nebula Scene – Avengers Infinity War (2018) Movie Clip HD](https://www.youtube.com/watch?v=2YyrTlraNUM)

### 2\. LocateTitan

After hijacking the Q-ship from Earth, Dr. Strange, Iron Man, and Spider Man decide to go to Titan. In the MCU, interstellar travel happens via wormholes (Einstein-Rosen Bridges).

-   [AVENGERS INFINITY WAR “Avengers Vs Guardians of the Galaxy”](https://www.youtube.com/watch?v=BWJ_P9gml_Y)

Suppose that the route from Earth to Titan can be represented by a Weighted Undirected Graph where the vertices are _wormhole generators_ and the edges are the _wormholes_ themselves. A wormhole is created by two wormhole generators, so the wormhole would be a “path” between these generators.

**Some important points:**

-   There is _no_ direct wormhole that can extend from Earth to Titan, so going from Earth to Titan requires intermediate “stops” at different wormhole generators.
-   For instance, a hypothetical path could be from a generator on Earth to a generator on Planet X, and from the generator on Planet X to a generator on Titan.
-   Each edge (Wormhole) would have an associated weight, which is the energy cost of creating the wormhole that the particular edge represents.
-   Each vertex (Wormhole Generator) would also have a weight, and this number (a _double_ data type) represents how functional that generator is. Due to Thanos’ conquest so far across the universe, not all of the wormhole generators would be 100% functional.
-   The Wormhole Generators would be identified by cardinal numbers (vertex 1 would be generator 1, vertex 2 would be generator 2, etc).

**SUPPOSE there are _n_ vertices IN THE GRAPH. ASSUME THAT GENERATOR #0 (vertex 0, the source) IS EARTH AND GENERATOR #(n-1) (vertex n-1, the destination) IS TITAN.**

**Your input is made of 2 portions:**

-   The generator number and the associated functionality.
-   An adjacency matrix displaying the energy cost of traveling from one generator to another.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-3.22.05-PM.png)

The corresponding graph looks like this.

-   The functionality of each generator is the number in black that is next to each vertex. For instance, the functionality of Generator #1 is 0.3
-   The energy cost between Generators i and j (in this image, that would be the number inside the _square_ on each edge) would be the value at the i’th row and j’th column in the adjacency matrix.
-   For instance, the edge between Generator #0 and Generator #2 has the number 4 boxed. Thus, the energy cost between Generator #0 and Generator #2 w0uld be 4.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-3.31.52-PM.png)

Right now, the adjacency matrix only shows the energy cost of each edge. However, we also need to factor in how each generator is _not_ fully functional. Thus, if the _total cost_ acknowledges the energy cost of each wormhole _and_ the functionalities of the generators creating the wormhole, then the _less_ functional the generators are, the _higher_ the total cost of creating and using the wormhole would be.

Thus, if we want the adjacency matrix to hold the _total cost_ of each wormhole, we can DIVIDE the energy cost by the functionality of BOTH generators that the wormhole connects.

Hence, once you read the input in and create your adjacency matrix, change EVERY edge weight (energy cost) by DIVIDING it by the functionality of BOTH vertices (generators) that the edge points to. Then, typecast this number to an integer (this is done to avoid precision errors).

For instance, the edge from Generator #1 (row 1) to Generator #4 (column 4) has an energy cost of 7.

-   Divide this by the functionality values of Generator #1 (which is 0.3) and Generator #4 (which is 0.9). As such, you would change the 4 to a 7/(0.3\*0.9) = 25.925.
-   Next, typecast this to an int, so (int) (25.925.) = 25.
-   Thus, the value at row 3 and column 4 of the matrix would _now_ be 25.
-   This means the _total cost_ between Generator #1 and Generator #4 is 25.
-   Do this for every edge!

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-3.34.59-PM.png)

Then, using your _new_ graph, calculate the minimum cost from Earth (Generator #0) and Titan (the final Generator; this would be Generator #5 since there are 6 Generators so the last Generator index is 6-1 = 5) in the example above.

Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. Write this number into your output file!

Here is a good reference video for Dijkstra’s Algorithm:

-   **[Dijkstra algorithm | Single source shortest path algorithm](https://www.youtube.com/watch?v=Sj5Z-jaE2x0)**

**Pseudocode for Dijkstra’s Algorithm:**

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-30-at-11.54.49-AM.png)

**Task****:** using the Adjacency Matrix of _n_ vertices and starting from Earth (**vertex 0**), modify the edge weights using the functionality values of the vertices that each edge connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0). Write this number into the output file.

**Some preconditions:**

-   The generator functionality values are guaranteed to be _between_ 0 and 1.
-   The energy costs are guaranteed to be positive integers.
-   The graph is guaranteed to be undirected.
-   The graph is guaranteed to be connected.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-3.49.17-PM.png)

Iron Man, Dr. Strange, and Spiderman arrive at Titan but are ambushed by the Guardians who think they are part of the Black Order. After some tension, they plan to work together to kill Thanos.

Meanwhile, to protect Vision, the Avengers on Earth head to Wakanda, a country in East Africa which has the technology to remove the Mind Stone in Vision’s head _without_ killing him in the process.

-   [Arriving on Wakanda Scene – Avengers: Infinity War (2018) Movie Clip HD \[1080p 50FPS\]](https://www.youtube.com/watch?v=iwkxsrAjGdM)

### 3\. MindStoneNeighborNeurons

A Wakandan named Shuri attempts to figure out how to remove the Mind Stone from Vision’s head.

-   [**I’m Sure You Did Your Best – Shuri Helps Vision Scene – Avengers Infinity War Movie Clip HD**](https://www.youtube.com/watch?v=qTV5kpM8Fvs)

Extracting the Mind Stone from Vision without killing him requires the detection of a specific set of neurons that CONNECT to the Mind Stone. Given a Directed Graph that contains vertices (neurons) and edges (synapses), the MindStoneNeighborNeurons method returns a list of neurons that connect to the Mind Stone. In the brain, cranial nerves usually have dendrites and an axon. A synapse occurs _between_ a dendrite and an axon.  The dendrites _receive_ information from other nerves, and the axon _transmits_ information to other nerves. As such, information always flows _from_ dendrites of a nerve, and then _into_ the axon of that nerve. That is why the graph is directed!

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-3.54.24-PM.png)

For this task, you can assume that the neurons have multiple dendrites and _only_ one axon. You can also assume that a nerve’s axon _always_ connects to ONLY 1 dendrite of another nerve (or it connects to the Mind Stone). 

Also, the Mind Stone does not have any axons, _but_ it does receive information from other nerves. As such, the Mind Stone always has an out-degree of 0. Use this to your advantage!

Your input file will be a Set of Edges representation of Vision’s neural network.

-   The first number _n_ is the number of vertices (neurons). In the below example, _n = 17_.
-   The next _n_ lines are the names of the neurons. These are Strings. In the below example, the Strings are a, b, c, d, etc.
-   The next line is the number _m_ of edges (synapses). In the below example, _m = 16_.
-   The next _m_ lines are the synapses. Each line contains two Strings, so the synapse is between the neurons represented by the first and second String. In the below example, these synapses would be a d, b d, c d, etc.

**Preconditions:**

-   The names of the nerves may not be single letters or characters (instead, they may be strings).

-   The input is guaranteed to be a set of edges representation of a neural network (each nerve has 1 axon, every axon connects to only 1 dendrite of another nerve, and the Mind Stone has no outgoing axons). As such, the graph is guaranteed to be connected.

The input file to the right represents the following graph (a neural network). NOTE that in this example, vertex x represents the Mind Stone.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-3.59.55-PM.png)

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-4.48.06-PM.png)

The neurons connecting to the Mind Stone are d, e, i, and l. Print these into the output file, and EACH neuron in a _new line_! It is also fine if there is a new line after the _last_ printed neuron also. As such, the corresponding output file looks like this:

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-4.48.13-PM.png)

Preconditions:

-   The names of the nerves may not be single letters or characters (instead, they may be strings).
-   The input is guaranteed to be a set of edges representation of a neural network (each nerve has 1 axon, every axon connects to only 1 dendrite of another nerve, and the Mind Stone has no outgoing axons). As such, the graph is guaranteed to be connected.

**Task****:** given a Set of Edges representing Vision’s Neural Network, identify all of the vertices that connect to the Mind Stone. List the names of these neurons in the output file.

On Titan, the Guardians plan out with Iron Man and Spiderman regarding an action scheme to stop Thanos. Meanwhile, using his Time Stone, Dr. Strange tries to predict the possible future outcomes, discovering that out of around 14 million, they win only one.

### 4\. Challenge – UseTimeStone (25 points extra credit)

Dr. Strange uses his Time Stone to view possible alternate realities.

-   [**Avengers: Infinity War (2018) – “14,000,605” | Movie Clip HD**](https://www.youtube.com/watch?v=ZCPN9SfdH7c)

When Dr. Strange calculates all the possible futures, the network of events that can potentially occur can be represented by a Directed Graph. The value that Dr. Strange obtained would basically be the number of possible paths through this graph – in other words, the number of possible timelines. Think of each timeline as a “path” through the graph where each vertex represents an event that could potentially occur.

For instance, starting from Event 0 in the graph below, there are 6 possible timelines that could occur.

These timelines are 0, 0-1, 0-1-4, 0-1-2, 0-1-2-3, and 0-3.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-4.41.07-PM.png)

First determine the number of possible timelines in the graph, starting from the first event (event 0, in the above example). Print this value into the output file.

Each “event” also has an expected utility (EU) which is a number that shows how “desirable” the event is. The EU of a timeline would be the SUM of the EUs of all the events in that timeline (meaning all the vertices in the path).

In _Avengers: Infinity War_, Dr. Strange only sees 1 timeline where the Avengers win against Thanos. We want to determine the number of timelines starting from event 0 where the EU of the timeline is _at least_ the EU of the Avengers winning! The EU of the Avengers winning is given as the threshold EU.

As such, given an input threshold EU value, next determine the number of timelines starting from event 0 where the total EU is GREATER than OR equal to the threshold. Print this value into the output file in a NEW line!

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-4.41.36-PM.png)

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-4.41.43-PM.png)

Again, the timelines start from event 0. As such, the possible timelines and corresponding EU values are listed below.

The possible timelines are:

-   0 (EU = 2)
-   0-1 (EU =2-1 =  1)
-   0-1-2 (EU = 2-1+3 = 4)
-   0-1-3 (EU = 5)
-   0-1-3-5 (EU = 7)
-   0-1-3-5-6 (EU = 11)
-   0-1-3-5-7 (EU = 4)
-   0-4 (EU = -4)
-   0-4-3 (EU = 0)
-   0-4-3-5 (EU = 2)
-   0-4–3-5-6 (EU = 6)
-   0-4-3-5-7 (EU = -1)

Here, there are 12 possible timelines, and there are 4 timelines with an EU higher than or equal to  the threshold of 5 (in the input file). Thus, you would print “12” and “4” in separate lines in the output file.

Thus, the output file for the above example looks like this:

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-4.47.55-PM.png)

Some preconditions:  

-   The EU values are guaranteed to be integers.
-   The graph is guaranteed to be connected.

**Task****:** given a starting event and an Adjacency Matrix representing a graph of all possible events once Thanos arrives on Titan, determine the total possible number of timelines that could occur AND the number of timelines with a total EU at least the threshold value. Write this number into the output file.

On Vormir, Thanos sacrifices Gamora’s soul for the Soul Stone by throwing her off a cliff.

1.  [Thanos Sacrifices Gamora Scene | Avengers: Infinity War (2018) Marvel Movie Clip](https://www.youtube.com/watch?v=-699mZ1yc3g)
2.  [Gamora’s Death Scene – Avengers Infinity War (2018) Movie Clip HD \[1080p 50FPS\]](https://www.youtube.com/watch?v=t3-L7-tAQMM&t=97s)

Thanos arrives on Titan and after an intense showdown, Dr. Strange gives up the Time Stone to Thanos who was about to kill Iron Man using the 4 Infinity Stones he already had in his gauntlet.

-   [Avengers infinity war | battle of titans HD 4k](https://www.youtube.com/watch?v=pfpdGvgK7sg)

The Wakandans hold a fierce battle against the Black Order at Wakanda.

-   [Avengers: Infinity War (2018) – “Battle Of Wakanda” | Movie Clip HD](https://www.youtube.com/watch?v=g5_w5XrC7Ts&t=8s)

Eventually, Thor, Rocket, and Groot arrive in Wakanda to assist in defeating the Black Order.

-   [Thor Arrives at the Battle of Wakanda in Avengers: Infinity War (2018)](https://www.youtube.com/watch?v=Ip6YwpLcIjI)

Finally, Thanos shows up in Wakanda to take the Mind Stone. Using his Infinity Stones, Thanos easily blocks and incapacitates many of the characters fighting him there including Captain America, Black Panther, and Bruce Banner.

Using all of her power, Wanda destroys the Mind Stone (reluctantly killing Vision in the process) before Thanos reaches her. However, using the Time Stone, Thanos reversed time until Vision was still alive, and then grabs the Mind Stone in Vision’s head and kills him.

### 5\. PredictThanosSnap

A final attack by Thor has him dig his Strombreaker through Thanos’ chest. However, Thanos snaps his fingers and teleports away, causing half of all life to disintegrate shortly afterward.

-   [**Avengers: Infinity War (2018) – “Snap Of Disintegration”| Movie Clip HD**](https://www.youtube.com/watch?v=zWhEgdh0X9c&t=118s)

Thanos’ snap would have had an immeasurable impact on planets such as Earth. Eliminating half of the population without notice would lead to unprecedented accidents, crashes, power outages, and loss of governmental control.

In the PredictThanosSnap method, we work with a social network (an Adjacency Matrix) where each vertex represents a person. We want to find out if half of the people (vertices) are removed, are the remaining people still able to contact each other? In other words, if half of the vertices are removed, is the graph still connected?

Because Thanos’ snap was impartial and unbiased, we use a random() function to determine which vertices to eliminate. In essence, each person has a 50% probability of disappearing, and you will use a seed so that the randomization yields the same results in Autolab as well as your program.

Then, return a boolean (true or false) which indicates if the graph is still connected after the removal.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-4.59.06-PM.png)

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-4.59.13-PM.png)

The social network in the example (after the snap) is _not_ connected since person 6 has _no_ way of contacting person  1 and person  3 (since person 4 disappears).

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-4.59.26-PM.png)

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-4.59.33-PM.png)

After reading in the seed, use _StdRandom.setSeed(seed)_ to prepare the seed to use in StdRandom.

Then, call _StdRandom.uniform()_ for each vertex (make sure to go IN ORDER from vertex 0 to the last vertex). If _StdRandom.uniform()_ is less than OR equal to 0.5, then delete the vertex.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-5.05.50-PM.png)

In theory, the seed ensures that Autolab generates the same random numbers as you when calling StdRandom.uniform().  The StdRandom class uses the seed to perform the uniform() method. In the pseudocode, the 0.5 is chosen since each person has a 50% chance of disappearing. 

Also, the choice of deleting when the random number is less than or equal to 0.5 is an arbitrary decision, but you must follow it to ensure consistency between your results and Autolab’s results. The same applies for the reason behind iterating from vertex 0 to the last vertex and not in any other order.

Here is more information regarding seeds if you are interested: [Seeds Stack Overflow](https://stats.stackexchange.com/questions/354373/what-exactly-is-a-seed-in-a-random-number-generator).

The specific seed value causes vertices 1, 2, 4, and 6 to be removed. As such, the corresponding graph looks like this:

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-5.08.17-PM.png)

This graph is not connected. As such, the output would thus be false. 

The output file would then look like this:

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-5.08.24-PM.png)

Preconditions:

-   The graph is guaranteed to be undirected.
-   The _input_ graph is guaranteed to be connected.

**Task****:** Given an adjacency matrix, use a random() function to remove half of the vertices and write into the output file a boolean (true or false) indicating if the graph is still connected.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-5.13.18-PM.png)

### Helpful Java Classes

The following are some data structures which are automatically imported with “**java.util.\***” that can help make your code cleaner and more efficient. You are free to not use any of these, and you are also free to use any other class under “java.util.\*” as you see fit. I will not be covering every single method for these data structures, just some useful ones for this assignment. You can find more information about how to use these classes online.

-   **ArrayList** is an ordered array-like structure with no size limit, as it automatically resizes

-   -   You can initialize an empty ArrayList named “name” which holds objects of type “Type” with ArrayList name = new ArrayList();
    -   For example, an ArrayList of integers named “arrList” is initialized with ArrayList arrList = new ArrayList<>();
    -   You can add a new element of type “Type” to the end of your ArrayList in average case O(1) time with name.add(newElement);
    -   You can get the element at some index of your ArrayList in O(1) time with name.get(index);
    -   You can set some index to some new element in O(1) time with name.set(index, newElement);
    -   You can check if the ArrayList contains some element (returns a boolean) in O(n) time with name.contains(element)

-   **HashMap** is an unordered data structure which stores and retrieves key value pairs

-   -   You can initialize an empty HashMap named “name” that maps objects of type “Key” to objects of type “Value” with HashMap<Key, Value> name = new HashMap<>();
    -   For example, a HashMap named “map” which maps strings to integers is initialized with HashMap<String, Integer> map = new HashMap<>();
    -   You can add a new key value pair, or update an existing key with a new value in average case O(1) time with name.put(key, value);
    -   You can check if the HashMap contains some key in average case O(1) time (returns a boolean) with name.containsKey(key)
    -   You can check the value of some key in the HashMap in average case O(1) time with name.get(key)
    -   You can iterate over all the keys in the HashMap with for (Key key : name.keySet()) where Key is the type of keys in the HashMap.

-   **HashSet** is an unordered data structure which only stores keys

-   -   You can initialize an empty HashSet named “name” that stores objects of type “Key” with HashSet name = new HashSet<>();
    -   For example, a HashSet named “set” which stores strings is initialized with HashSet set = new HashSet<>();
    -   You can add a new key to the hash set in average case O(1) time with name.add(key)
    -   You can check if a key exists in the hash set in average case O(1) time (returns a boolean) with name.contains(key)
    -   You can remove a key in average case O(1) time with name.remove(key)
    -   You can iterate over all the keys in the HashSet with for (Key key : name) where Key is the type of keys in the HashSet.

### Executing and Debugging

-   You can run your program through VSCode or you can use the Terminal to compile and execute. We suggest running through VSCode because it will give you the option to debug.
-   [How to debug your code](https://code.visualstudio.com/docs/java/java-debugging)
-   If you choose the Terminal, from InfinityWar directory/folder:
    -   to compile:  **javac -d bin src/avengers/\*.java**
    -   to execute ForgeStormBreaker: **java -cp bin **avengers.ForgeStormBreaker forgestormbreaker.in forgestormbreaker.out****

### Zipping the directory for submission (READ THIS before submiting)

-   Be careful when zipping the directory for submission.
-   Autolab is expecting the exact directory organization we provided to you.
-   Autolab is expecting the zip file to be named **infinitywar.zip**
-   All files that you have written **MUST** be in the **InfinityWar/src/avengers** directory.

To zip the **InfinityWar** directory navigate to the parent directory:

-   zip -r **infinitywar.zip** InfinityWar

Inspect the zip by listing the files in zip without uncompressing it:

-   unzip -l infinitywar.zip

Your zip file must have the following structure:

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2022/11/Screen-Shot-2022-11-18-at-6.49.17-PM.png)

### Before submission

**Collaboration policy.** Read our collaboration policy [here](https://ds.cs.rutgers.edu/#academic-integrity).

**Submitting the assignment.**

You will have to submit a zip file. See previous section on how to zip the directory.

Submit _infinitywar.zip_ separately via the web submission system called Autolab. To do this, click the _Assignments_ link from the course website; click the _Submit_ link for that assignment.