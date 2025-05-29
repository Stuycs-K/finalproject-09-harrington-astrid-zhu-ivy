# Dev Log:

This document must be updated daily every time you finish a work session.

## Ivy Zhu

### 2025-05-16 - Finding sources
Our main coding language is java, and a lot of tutorials online seem to be written in python. Hopefully there isn't too much trouble translating between languages. This tutorial seems pretty detailed:
1. Python: https://medium.com/@domspaulo/python-implementation-of-sha-256-from-scratch-924f660c5d57
And here are some others
2. Java: https://github.com/softknk/SHA256
3. C: https://www.tony.software/posts/sha256_building_hash_algorithm_scratch/
4. JavaScript: https://www.movable-type.co.uk/scripts/sha256.html

Reading tutorial and breaking down the task into multiple parts so we can work on different parts at once.
We're going to try how to compute sha256 hash by hand to better understand the process.
https://github.com/liangtengyu/wx_gzh_article/blob/master/How%20SHA-2%20Works%20Step-By-Step%20(SHA-256).md
https://sha256algorithm.com/
-64 mod 512

### 2025-05-19 - Coding rotate
Since sha256 requires rightrotate and rightshift, I'm trying to code that right now. I know java has some built in functions for rotating integers, so I'll try to use that but I'm facing integer number too large errors.

I worked on it a bit at home, and right rotate and right shift are now working! I've tried on some strings and xor produces the right result.

### 2025-05-20 - Finished rightrotate and rightshift
I've finished the subfunctions needed to transform the message into meesage schedule. It's harder to see if my function is right when everything is ones and zeroes and there are 64 cells in the array. It doesn't seem to work, but it has worked on individual cases, so I'll have to check that.

Step 5 is working! I checked with the example in the github/liangtengyu website. Now, I'll start working on step 6.

### 2025-05-21 - Working on changing a to f
I'm looking at the step where you have to right rotate and add and perform bitwise operations on letters a to h. It doesn't seem too different from the previous step, but I'm having trouble with the integer limits since these numbers are more than 2^31.

Need to make array of k (first few digits of cube roots of primes).

### 2025-05-22 - Finished turning into hash
I figured out that I just have to initialize the k array with numbers from online instead of writing another function to calculate everything. Now, I've finished step 7: adding a to h0, b to h1, etc. 
All that's left to do is to take the h0 to h7 and concatanate them to form the final hash! This works for hello world, but we haven't tried strings that take up more than 512 bits.

### 2025-05-23 - Working on combining
Astrid and I finished our individual portions, so now we just have to work on putting it together. I need to see how having a longer message to encode will change the process.

Finish combining everything!

### 2025-05-27 - Makefile
We're working on running our program from the terminal and with the makefile. Makefile is working! We'll be working on the proposal doc now.

### 2025-05-28 - README
I added instructions on how to run our program in the README. We'll work on presentation.md and brainstorm things to include in our video.

### 2025-05-29 - Research for video
Astrid and I are combing the internet for weaknesses and improvements for sha256. We found that sha256 was created after sha1 since sha1 was deemed unsafe since people were able to find collisions. We're planning on talking a bit about the history so that the viewer can understand why there are multiple sha algorithms and hence why three groups are working on sha projects. 

I was concerned that since there are three groups doing sha, and the minimum video length is 10 minutes, 30 minutes might be a bit much for explaining sha. Mr. K said that was a fair concern and that we should figure out how much time is needed to show off our individual programs, then split up the rest of the things we want to cover.
