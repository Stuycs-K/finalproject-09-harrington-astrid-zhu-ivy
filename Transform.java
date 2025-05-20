import java.util.Arrays;

class Transform{
  public static void main(String[] args){
    String input = "hello world";
    String w1 = "01101111001000000111011101101111";
    // w[1] = (byte) Integer.parseInt(w1, 2);
    int n = Integer.parseInt(w1, 2);;
    System.out.println(Integer.toBinaryString(n));
    // System.out.println(Integer.toBinaryString(Integer.rotateRight(n, 7)));
    // System.out.println("11011110110111100100000011101110");
    // System.out.println(Integer.toBinaryString(Integer.rotateRight(n, 18)));
    // System.out.println("00011101110110111101101111001000");
    // System.out.println(Integer.toBinaryString(rightshift(n, 3)));

    int s0 = Integer.rotateRight(n, 7) ^ Integer.rotateRight(n, 18) ^ rightshift(n, 3);
    System.out.println(Integer.toBinaryString(s0).equals("11001110111000011001010111001011"));
    int[] w = new int[64];
    w[0] = Integer.parseInt("01101000011001010110110001101100", 2);
    w[1] = Integer.parseInt("01101111001000000111011101101111", 2);
    w[2] = Integer.parseInt("01110010011011000110010010000000", 2);
    msgSchedule(w);
    printBin(w);
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
    System.out.println(w[16]); // mod 2^32 ?
    System.out.println(Integer.parseInt("00110111010001110000001000110111", 2));
  }

  public static void printBin(int[] w){
    for (int num : w) {
            System.out.println(Integer.toBinaryString(num));
        }
  }
}
