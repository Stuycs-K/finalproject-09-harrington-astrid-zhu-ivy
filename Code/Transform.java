import java.util.Arrays;

class Transform{
  // global variables
  public static int h0, h1, h2, h3, h4, h5, h6, h7;
  public static int[] k = { 
   0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
   0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
   0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
   0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
   0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
   0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
   0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
   0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2};
    
  public static void main(String[] args){
    String input = "hello world";
    int[] w = new int[64];
    w[0] = Integer.parseInt("01101000011001010110110001101100", 2);
    w[1] = Integer.parseInt("01101111001000000111011101101111", 2);
    w[2] = Integer.parseInt("01110010011011000110010010000000", 2);
    w[15] = Integer.parseInt("00000000000000000000000001011000", 2);
    msgSchedule(w);
    String res = printBin(w);
    String result = 
            "01101000011001010110110001101100 01101111001000000111011101101111\n" +
            "01110010011011000110010010000000 00000000000000000000000000000000\n" + //
            "00000000000000000000000000000000 00000000000000000000000000000000\n" + //
            "00000000000000000000000000000000 00000000000000000000000000000000\n" + //
            "00000000000000000000000000000000 00000000000000000000000000000000\n" + //
            "00000000000000000000000000000000 00000000000000000000000000000000\n" + //
            "00000000000000000000000000000000 00000000000000000000000000000000\n" + //
            "00000000000000000000000000000000 00000000000000000000000001011000\n" + //
            "00110111010001110000001000110111 10000110110100001100000000110001\n" + //
            "11010011101111010001000100001011 01111000001111110100011110000010\n" + //
            "00101010100100000111110011101101 01001011001011110111110011001001\n" + //
            "00110001111000011001010001011101 10001001001101100100100101100100\n" + //
            "01111111011110100000011011011010 11000001011110011010100100111010\n" + //
            "10111011111010001111011001010101 00001100000110101110001111100110\n" + //
            "10110000111111100000110101111101 01011111011011100101010110010011\n" + //
            "00000000100010011001101101010010 00000111111100011100101010010100\n" + //
            "00111011010111111110010111010110 01101000011001010110001011100110\n" + //
            "11001000010011100000101010011110 00000110101011111001101100100101\n" + //
            "10010010111011110110010011010111 01100011111110010101111001011010\n" + //
            "11100011000101100110011111010111 10000100001110111101111000010110\n" + //
            "11101110111011001010100001011011 10100000010011111111001000100001\n" + //
            "11111001000110001010110110111000 00010100101010001001001000011001\n" + //
            "00010000100001000101001100011101 01100000100100111110000011001101\n" + //
            "10000011000000110101111111101001 11010101101011100111100100111000\n" + //
            "00111001001111110000010110101101 11111011010010110001101111101111\n" + //
            "11101011011101011111111100101001 01101010001101101001010100110100\n" + //
            "00100010111111001001110011011000 10101001011101000000110100101011\n" + //
            "01100000110011110011100010000101 11000100101011001001100000111010\n" + //
            "00010001010000101111110110101101 10110000101100000001110111011001\n" + //
            "10011000111100001100001101101111 01110010000101111011100000011110\n" + //
            "10100010110101000110011110011010 00000001000011111001100101111011\n" + //
            "11111100000101110100111100001010 11000010110000101110101100010110\n";
    // System.out.println(res.equals(result));

    // Step 6 - Compression

    h0 = Integer.parseInt("01101010000010011110011001100111", 2);
    // h1 = Integer.parseInt("10111011011001111010111010000101", 2);
    h2 = Integer.parseInt("00111100011011101111001101110010", 2);
    // h3 = Integer.parseInt("10100101010011111111010100111010", 2);
    h4 = Integer.parseInt("01010001000011100101001001111111", 2);
    // h5 = Integer.parseInt("10011011000001010110100010001100", 2);
    h6 = Integer.parseInt("00011111100000111101100110101011", 2);
    h7 = Integer.parseInt("01011011111000001100110100011001", 2);

    h1 = (int) (3144134277L % (long) Math.pow(2, 32));
    h3 = (int) (2773480762L % (long) Math.pow(2, 32));
    h5 = (int) (2600822924L % (long) Math.pow(2, 32));
    compression(k, w);
  }

  public static int rightshift(int n, int d){
    return (n >>> d);
  }

  public static void msgSchedule(int[] w){
    for (int i = 16; i < 64; i++){
      int n = w[i-15];
      int s0 = Integer.rotateRight(n, 7) ^ Integer.rotateRight(n, 18) ^ rightshift(n, 3);
      n = w[i-2];
      int s1 = Integer.rotateRight(n, 17) ^ Integer.rotateRight(n, 19) ^ rightshift(n, 10);
      w[i] = w[i-16] + s0 + w[i-7] + s1;
    }
    // System.out.println(w[16]); // mod 2^32 ?
    // System.out.println(Integer.parseInt("00110111010001110000001000110111", 2));
  }

  public static void compression(int[] k, int[] w){
    int a, b, c, d, e, f, g, h;
    a = h0;
    b = h1;
    c = h2;
    d = h3;
    e = h4; 
    f = h5;
    g = h6;
    h = h7;
    for (int i = 0; i < 64; i++){
      int s1 = Integer.rotateRight(e, 6) ^ Integer.rotateRight(e, 11) ^ Integer.rotateRight(e, 25);
      int choice = (e & f) ^ ((~e) & g); 
      int temp1 = h + s1 + choice + k[i] + w[i];

      int s0 = Integer.rotateRight(a, 2) ^ Integer.rotateRight(a, 13) ^ Integer.rotateRight(a, 22);
      int majority = (a & b) ^ (a & c) ^ (b & c);
      int temp2 = s0 + majority;
      h = g;
      g = f;
      f = e;
      e = d + temp1;
      d = c;
      c = b;
      b = a;
      a = temp1 + temp2;
      // System.out.println(Integer.toBinaryString(a));
    }
    // System.out.println(Integer.toBinaryString(a));
    h0 += a;
    h1 += b;
    h2 += c;
    h3 += d;
    h4 += e;
    h5 += f;
    h6 += g;
    h7 += h;
    String hash = "" + Integer.toHexString(h0) + Integer.toHexString(h1) + Integer.toHexString(h2) + Integer.toHexString(h3) 
    + Integer.toHexString(h4) + Integer.toHexString(h5) + Integer.toHexString(h6) + Integer.toHexString(h7);
    System.out.println(hash);
  }

  public static String printBin(int[] w){
    String str = "";
    for (int i = 0; i < w.length; i++) {
      int num1 = w[i];
      i++;
      int num2 = w[i];
      String s1 = String.format("%" + 32 + "s", Integer.toBinaryString(num1)).replace(' ', '0');
      String s2 = String.format("%" + 32 + "s", Integer.toBinaryString(num2)).replace(' ', '0');
      // System.out.println(s1 + " " + s2);
      str += s1 + " " + s2 + "\n";
    }
    return str;
  }
}
