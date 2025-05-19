class Transform{
  public static void main(String[] args){
    String input = "hello world";
    byte[] w = new byte[64];
    // w[1] = 01101111001000000111011101101111;
    // w[1] = rotate(w[1], 7);
    // int n = (int) w[1];
    int n = (int) 01101111001000000111011101101111;
    n = Integer.rotateRight(n, 7);
    print(w[1]);
    print((byte) n);
  }

  // public static byte rotate(byte b, int n){
  //   return (byte)(())
  // }
}
