class Make32BitWords{
    public static void main(String[] args){

    }


    // this will take int[][] bits w/rows of length 512. it will transform each row of length 512 into a row of length 512/32=16
    public static int[][] make32BitWords(int[][] bits){
        int[][] ans = new int[bits.length][64];
        for (int row = 0; row < bits.length; row++){
            int thirtyTwoBitWord = 0;
            for (int col = 0; col <= bits[row].length; col++){
                if (col % 32 == 0 && col != 0){
                    System.out.println("modifying ans row " + row + " col "+ col/32);
                    
                    ans[row][col /32 - 1] = thirtyTwoBitWord;
                    thirtyTwoBitWord = 0;
                }
                if (col != bits[row].length){
                    thirtyTwoBitWord += (int) Math.pow(2,31 - (col%32))*bits[row][col];
                }
            }
        }
        return(ans);
    }
}