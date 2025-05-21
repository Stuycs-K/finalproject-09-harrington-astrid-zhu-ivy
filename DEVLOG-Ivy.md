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
