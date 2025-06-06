# Dev Log:

This document must be updated daily every time you finish a work session.

## Astrid Harrington

### 2025-05-16 - Preliminary Research

We located the following four sources for constructing sha256:

1. Python: https://medium.com/@domspaulo/python-implementation-of-sha-256-from-scratch-924f660c5d57
2. Java: https://github.com/softknk/SHA256
3. C: https://www.tony.software/posts/sha256_building_hash_algorithm_scratch/
4. JavaScript: https://www.movable-type.co.uk/scripts/sha256.html

We are going to spent some time investigating these sources. We plan to do our project in Java, but haven't found a walkthrough in Java (all we have is an example sha256 encoder). However, the Python walkthrough seems approachable and will probably be helpful.

We are struggling to understand the steps involved in building sha256, so we are going to read through the following document:

https://github.com/liangtengyu/wx_gzh_article/blob/master/How%20SHA-2%20Works%20Step-By-Step%20(SHA-256).md

Then we will create a plan for how to proceed.

### 2025-05-19 - Started work on preprocessing

Having reviewed the steps required to build sha-256, I decided to start by working on the preprocessing function (which involves converting the message to bits, adding a big-endian 1, padding with zeros, and adding a 64-bit integer to represent the length of the original message such that the final length of the message is a multiple of 512 bits).

I made some progress but ran into a small issue that I plan to fix tonight.

### 2025-05-19 - Finished padding & parsing

I corrected the error that I ran into in classwork and I think I got the padding function working. Unfortunately, when I try to verify the array, it seems to suggest that my big-endian 1 is being represented as a negative number, -128 specifically. I think this may just be an issue with representation and not a deeper flaw, considering this chain on StackOverflow:

https://stackoverflow.com/questions/18754230/how-a-positive-value-becomes-negative-after-casting-byte-in-java

However, this may create some problems if we have to recast to ints to rotate. I need to consider this.

I also finished the function to parse byte[] bytes into byte[][] parsed (divided into rows of length 512 bits in order to make the next section of the algorithm easier).

### 2025-05-20 - Modified padding & parsing

I was absent from class today for a doctor's appointment, so I was only able to work from home. However, after conferring with Ivy, I decided that instead of returning a byte[][] object, I would return int[] bits that contained the individual bits and and int[][] parsed (also divided into rows of length 512 bits). This would help make the product of preprocessing compatible with the future steps involved in the sha256 algorithm.

This required that I rewrite most of the helper functions I had been using. More importantly, I made use of the Integer.toBinaryString() function to find the binary values of certain ints. This function does not always return Strings of length 8, requiring some troubleshooting to ensure that the bits were inserted in the correct location in the array.

At this point, I believe both the preprocessing and parsing functions are working.

### 2025-05-22 - Wrote Make32BitWords.java and added preprocessing to Sha256.java

During class today, Ivy and I started to discuss how we would merge our two halves of the sha256 algorithm. This led me to write the Make32BitWords.java function to ensure that the preprocessed message was in the right format for the rest of the algorithm.

Then, we created a Sha256 class for the overall sha256 algorithm. At home, I added the preprocessing functions to Sha256 and made sure they were working.

### 2025-05-27 - Tested from CLI and wrote makefile

After encountering many issues with running the makefile outside of the Code directory, we eventually decided to move the makefile into the Code directory. We also modified error messages from parseArgs() to more accurately reflect solutions to problems. Our makefile is now working! Going forward, we are going to finalize PROPOSAL.md/README.md and start work on PRESENTATION.md.

### 2025-05-28 - Finished proposal/readme and started work on presentation

Today, Ivy and I worked together on finalizing PROPOSAL.md and README.md. We also created a file for PRESENTATION.md and intend to start work on it.

### 2025-05-29 - Worked more on presentation

We worked more on brainstorming for presentation. We fleshed out some ideas for improvements of sha256, and we also started researching some weaknesses. We made some good progress, although more research is needed.

### 2025-05-30 - Figured out length extension attacks

I spent today trying to understand why sha256 is vulnerable to length extension attacks, and I think I've figured it out! I wrote an explanation in PRESENTATION.md.

### 2025-06-02 - Continued work on presentation

In class, I finished the sections on weaknesses/improvements in PRESENTATION.md, and I wrote an outline of preprocessing. All that's left is the outline of the actual sha256 algorithm and we'll be ready to record the video.

### 2025-06-03 - Finished presentation

I added some diagrams for length extension attack while Ivy finished the outline of sha256 algorithm. Now we're ready to film!
