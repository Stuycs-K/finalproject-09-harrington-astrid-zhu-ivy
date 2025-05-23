/* NOTES ON PREPROCESS: 

1. preProcess(String input) returns padded message.
2. parsing(byte[] bytes) returns byte[][] with the rows as 512-bit chunks that can be manipulated by the sha256 algorithm.

*/


class PreProcess{
    public static void main(String[] args){
        /*String input = "hello world";
        int[] bits = preProcess(input);
        //byte[][] parsed = parsing(bytes);
        int[][] parsed = parsing(bits);
        printDoubleArray(parsed);

        Make32BitWords rearranger = new Make32BitWords();

        int[][] thirtyTwoBitWords = rearranger.make32BitWords(parsed);
        printDoubleArray(thirtyTwoBitWords);*/
    }

    public static int[] preProcess(String input){
        byte[] bytes = input.getBytes();
        int[] bits = new int[8*bytes.length];

        for (int i = 0; i < bytes.length; i++){
            int b = (int) bytes[i];
            String binary = Integer.toBinaryString(b);
            binary = "0".repeat((8-binary.length()))+binary;
            for (int j = 0; j < 8; j++){
                if (binary.substring(j,j+1).equals("0")){
                    bits[i*8+j] = 0;
                }
                else{
                    bits[i*8+j] = 1;
                }

            }
        }

        int length = bytes.length * 8; // # of digits required for message representation in binary
        bits = append(128, bits); // appends big endian 1
        //printArray(bytes);
        int zeroBitsNeeded = 512 - ((length + 8 + 64) % 512);
        int[] zeroPadding = new int[zeroBitsNeeded];
        bits = combine(bits,zeroPadding);

        //THIS IS THE PROBLEM: must append 64 bits!!!
        int[] length64Bit = make64Bit(length);
        bits = combine(bits,length64Bit);

        //DEBUGGING
        //printArray(bits);
        //System.out.println("Length: " + bits.length);
        //System.out.println("Zeros: " + countZeros(bits));

        return bits;
    }
    
    public static byte[][] parsing(byte[] bytes){
        byte[][] parsed = new byte[bytes.length / (512 / 8)][512/8];
        for (int i = 0; i < bytes.length; i++){
            parsed[i / (512/8)][i % (512/8)] = bytes[i];
        }
        return(parsed);
    }

    public static int[][] parsing(int[] bits){
        int[][] parsed = new int[bits.length / 512][512];
        for (int i = 0; i < bits.length; i++){
            parsed[i / (512)][i % (512)] = bits[i];
        }
        return(parsed);
    }

    public static byte[] append(byte b, byte[] array){
        byte[] bytes = new byte[array.length + 1];
        for (int i = 0; i < array.length; i++){
            bytes[i] = array[i];
        }
        bytes[array.length] = b;
        return(bytes);
    }

    public static int[] append(int intByte, int[] array){
        int[] bits = new int[array.length + 8];
        for (int i = 0; i < array.length; i++){
            bits[i] = array[i];
        }
        String binary = Integer.toBinaryString(intByte);
        binary = "0".repeat((8-binary.length()))+binary;
        for (int j = 0; j < 8; j++){
                if (binary.substring(j,j+1).equals("0")){
                    bits[array.length+j] = 0;
                }
                else{
                    bits[array.length+j] = 1;
                }

            }
        return(bits);
    }

    public static byte[] combine(byte[] bytes1, byte[] bytes2){
        byte[] bytes = new byte[bytes1.length + bytes2.length];
        for (int i = 0; i < bytes1.length; i++){
            bytes[i] = bytes1[i];
        }
        for (int i = bytes1.length; i < bytes1.length + bytes2.length; i++){
            bytes[i] = bytes2[i - bytes1.length];
        }
        return(bytes);
    }

    public static int[] combine(int[] bits1, int[] bits2){
        int[] bits = new int[bits1.length + bits2.length];
        for (int i = 0; i < bits1.length; i++){
            bits[i] = bits1[i];
        }
        for (int i = bits1.length; i < bits1.length + bits2.length; i++){
            bits[i] = bits2[i - bits1.length];
        }
        return(bits);
    }

    public static int countZeros(byte[] bytes){
        int count = 0;
        for (int i = 0; i < bytes.length; i++){
            if (bytes[i] == 0){
                count++;
            }
        }
        return(count);
    }

    public static int countZeros(int[] bits){
        int count = 0;
        for (int i = 0; i < bits.length; i++){
            if (bits[i] == 0){
                count++;
            }
        }
        return(count);
    }

    /*public static byte[] make64Bit(int k){
        byte[] bytes = new byte[8];
        int i = 1;
        while (k > 0){
            bytes[bytes.length - i] = (byte)(k % 256);
            k /= 256;
        }
        return(bytes);
    }*/

    public static int[] make64Bit(int k){
        int[] bits = new int[64];
        String binary = Integer.toBinaryString(k);
        for (int i = 64 - binary.length(); i < 64; i++){
            if (binary.substring(i - (64 - binary.length()),i - (64-binary.length())+1).equals("1")){
                bits[i] = 1;
            }
            else{
                bits[i] = 0;
            }
        }
        return(bits);
    }

    public static void printArray(byte[] array){
        System.out.print("[");
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i]);
            if (i < array.length - 1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void printArray(int[] array){
        System.out.print("[");
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i]);
            if (i < array.length - 1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void printDoubleArray(byte[][] array){
        System.out.println("BEGIN DOUBLE ARRAY");
        for (int i = 0; i < array.length; i++){
            printArray(array[i]);
        }
        System.out.println("END DOUBLE ARRAY");
    }

    public static void printDoubleArray(int[][] array){
        System.out.println("BEGIN DOUBLE ARRAY");
        for (int i = 0; i < array.length; i++){
            printArray(array[i]);
        }
        System.out.println("END DOUBLE ARRAY");
    }

}