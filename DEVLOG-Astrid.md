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
