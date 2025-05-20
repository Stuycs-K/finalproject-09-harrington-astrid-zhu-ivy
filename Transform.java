class Transform{
  public static void main(String[] args){
    String input = "hello world";
    byte[] w = input.getBytes();
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
  }

  public static int rightshift(int n, int d){
    return (n >>> d);
  }
}
