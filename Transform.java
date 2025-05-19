class Transform{
  public static void main(String[] args){
    String input = "hello world";
    byte[] w = input.getBytes();
    String w1 = "01101111001000000111011101101111";
    // w[1] = (byte) Integer.parseInt(w1, 2);
    int n = Integer.parseInt(w1, 2);;
    System.out.println(Integer.toBinaryString(n));
    // int n = (int) 01101111001000000111011101101111;
    n = Integer.rotateRight(n, 7);
    System.out.println(Integer.toBinaryString(n));
  }

  // public static byte rotate(byte b, int n){
  //   return (byte)(())
  // }
}
