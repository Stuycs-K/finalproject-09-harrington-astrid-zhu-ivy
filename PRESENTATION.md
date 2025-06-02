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

### Potential for collisions 
  
1993: sha1

But sha1 is insecure because hackers can find collisions. There is an [algorithm](https://security.googleblog.com/2017/02/announcing-first-sha1-collision.html) to do so.
  
2001: sha256

Sha256 isn't invertible either, so there are collisions. However, due to the complexity of the algorithm (64 steps), no collisions have been found to this day!! Thus, potential for collisions is not currently a practical concern. In fact, it was the motivation for switching to sha256 in the first place.

### Future innovation

Any present-day algorithm could be vulnerable to future innovations. For instance, sha256 could be made insecure by [quantum computing](https://thequantuminsider.com/2025/01/19/researcher-bitcoin-will-evolve-to-meet-quantum-threat/), which could theoretically speed up the process of finding collisions.

## Improvements

### Making small changes to constants

In the sha256 algorithm, round constants and hash values are hard-coded to certain values. The [hash values and round constants](https://github.com/liangtengyu/wx_gzh_article/blob/master/How%20SHA-2%20Works%20Step-By-Step%20(SHA-256).md) are "the first 32 bits of the fractional parts of the square roots of the first 8 primes" and "the first 32 bits of the fractional parts of the cube roots of the first 64 primes" respectively. This raises a question: why don't different systems initialize sha256 with different hash values and round constants? This would render rainbow tables useless, and hackers would have to start fresh with every new system they encountered.

The downside of this is that if (for some reason) the hash values and round constants got lost, there would be no way to tell what they used to be (and no way to confirm if a password matches a hash). This is the benefit of having a standardized sha256 function for all systems.

### Making small changes to the algorithm

make small changes like instead of using the last 64 bits for the length, use them for length - 1. or some other transformation of length. (this wld also ruin rainbow tables)
(maybe we don't do this b/c it's not standardized: ie. if you lose your system's values then everyone is locked out of their account.)
### just using sha is less secure than adding pepper/salt!!
### easy improvement: use more bits (ie. sha256 has 256 bits vs sha1 which only has 160.)
### ways to optimize? (speed for large files)
