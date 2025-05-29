# Presentation

## Overview

structure of sha (how we broke it down): preprocessing & transofmr

## Weaknesses

- energy/time required to hash
- length extension attack (need to research more)
- brute force/rainbow tables (safer to files)
- potential for collisions (NOTE: not a practical concern)
- while very secure rn, future innovation (ie. quantum computing?) could make it less so.

## Improvements

- why not use diff initial round constants/hash values for diff systems? then rainbow tables wouldn't work
- make small changes like instead of using the last 64 bits for the length, use them for length - 1. or some other transformation of length. (this wld also ruin rainbow tables)
(maybe we don't do this b/c it's not standardized: ie. if you lose your system's values then everyone is locked out of their account.)
- just using sha is less secure than adding pepper/salt!!
- easy improvement: use more bits (ie. sha256 has 256 bits vs sha1 which only has 160.)
- ways to optimize? (speed for large files)
