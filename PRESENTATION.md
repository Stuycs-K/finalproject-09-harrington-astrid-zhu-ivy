# Presentation

## Overview

structure of sha (how we broke it down): preprocessing & transform

## Weaknesses

### Energy/time required to hash

sha256 is slower and more [computationally intensive](https://nordvpn.com/blog/sha-256/) than other comparable hashes (like MD5).

### Length Extension Attack

Length extension attacks are useful in a scenario in which hashed messages are appended to a secret key and sent. For example, MESSAGE1 "hello world" and SECRETKEY "key" could be concatenated to form the new string "keyhello world" (SECRETKEY + MESSAGE1). Then this string could be hashed and sent alongside the message ("hello world"), confirming that the message was sent by a person in possession of SECRETKEY.

A hash is vulnerable to a length extension attack if given MESSAGE1 and the hash of SECRETKEY + MESSAGE1, the hash of SECRETKEY + MESSAGE1 + MESSAGE2 can be determined. This would allow an attacker to send additional malicious text (MESSAGE2) appended to MESSAGE1 without actually knowing SECRETKEY. 

Sha256 is somewhat [vulnerable](https://kerkour.com/sha256-length-extension-attacks) to length extension attacks. Sha256 works by performing successive computations based on 512-bit chunks created by the message: it moves forward, not backward. In an ideal case, the final hash of SECRETKEY + MESSAGE1 would be an intermediate step along the way to hashing SECRETKEY + MESSAGE1 + MESSAGE2, and it could be used to compute the second hash.

In reality, this is not entirely true. Because of sha256's preprocessing and padding functions, SECRETKEY + MESSAGE1 is padded with an extra 1, lots of 0s, and the length in bits of SECRETKEY + MESSAGE1. Therefore, in order to append MESSAGE2 to the hash (and send the matching plaintext message), an attacker would need to know the length of SECRETKEY. This would tell them exactly how SECRETKEY + MESSAGE1 was preprocessed and padded, and they could reverse those processes to find the plaintext message PADDING that would correspond to the padding. Then they could preprocess and pad MESSAGE2 (substituting the length of SECRETKEY + MESSAGE1 + PADDING + MESSAGE2 for the length of MESSAGE2 in the process) before continuing to hash it. Thus, the attacker would need to send that hash alongside the plaintext message SECRETKEY + MESSAGE1 + PADDING + MESSAGE2, making it somewhat less controlled. Regardless, there is some vulnerability.

This would be particularly dangerous in a case when a computer receives and reads the message. A human could probably figure out that the message was tampered with, but a computer might indiscriminately read the message and run code embedded within it if it recognizes the SECRETKEY. 

### Brute force/rainbow tables

Sha256 is a very popular hashing algorithm, so it has been explored by hackers in the past. Rainbow tables for common passwords exist, reducing the computational intensity of brute forcing hashed passwords. 

This is only a concern for smaller messages like passwords: it would be near impossible to brute force a hashed file.

### potential for collisions (NOTE: not a practical concern. sha1 has a lot more collisions: hence sha256)
  
1993: sha1
  
2001: sha256

way to generate sha1 collision: https://security.googleblog.com/2017/02/announcing-first-sha1-collision.html

NO KNOWN sha256 COLLISIONS!!!

### very secure rn, but future innovation (ie. quantum computing?) could make it less so.

## Improvements

- why not use diff initial round constants/hash values for diff systems? then rainbow tables wouldn't work
- make small changes like instead of using the last 64 bits for the length, use them for length - 1. or some other transformation of length. (this wld also ruin rainbow tables)
(maybe we don't do this b/c it's not standardized: ie. if you lose your system's values then everyone is locked out of their account.)
- just using sha is less secure than adding pepper/salt!!
- easy improvement: use more bits (ie. sha256 has 256 bits vs sha1 which only has 160.)
- ways to optimize? (speed for large files)
