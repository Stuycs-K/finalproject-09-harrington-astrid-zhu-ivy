/* NOTES ON PREPROCESS: 

1. preProcess(String input) returns padded message.
2. parsing(byte[] bytes) returns byte[][] with the rows as 512-bit chunks that can be manipulated by the sha256 algorithm.

*/


class PreProcess{
    public static void main(String[] args){
        String input = "hello world";
        byte[] bytes = preProcess(input);
        byte[][] parsed = parsing(bytes);
        //printDoubleArray(parsed);
    }

    public static byte[] preProcess(String input){
        byte[] bytes = input.getBytes();
        int length = bytes.length * 8; // # of digits required for message representation in binary
        bytes = append((byte)128, bytes); // appends big endian 1
        //printArray(bytes);
        int zeroBitsNeeded = 512 - ((length + 8 + 64) % 512);
        byte[] zeroPadding = new byte[zeroBitsNeeded / 8];
        bytes = combine(bytes,zeroPadding);

        //THIS IS THE PROBLEM: must append 64 bits!!!
        byte[] length64Bit = make64Bit(length);
        bytes = combine(bytes,length64Bit);

        //DEBUGGING
        printArray(bytes);
        System.out.println("Length: " + bytes.length);
        System.out.println("Zeros: " + countZeros(bytes));

        return bytes;
    }
    
    public static byte[][] parsing(byte[] bytes){
        byte[][] parsed = new byte[bytes.length / (512 / 8)][512/8];
        for (int i = 0; i < bytes.length; i++){
            parsed[i / (512/8)][i % (512/8)] = bytes[i];
        }
        return(parsed);
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

    public static byte[] append(byte b, byte[] array){
        byte[] bytes = new byte[array.length + 1];
        for (int i = 0; i < array.length; i++){
            bytes[i] = array[i];
        }
        bytes[array.length] = b;
        return(bytes);
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

    public static int countZeros(byte[] bytes){
        int count = 0;
        for (int i = 0; i < bytes.length; i++){
            if (bytes[i] == 0){
                count++;
            }
        }
        return(count);
    }

    public static byte[] make64Bit(int k){
        byte[] bytes = new byte[8];
        int i = 1;
        while (k > 0){
            bytes[bytes.length - i] = (byte)(k % 256);
            k /= 256;
        }
        return(bytes);
    }

    public static void printDoubleArray(byte[][] array){
        System.out.println("BEGIN DOUBLE ARRAY");
        for (int i = 0; i < array.length; i++){
            printArray(array[i]);
        }
        System.out.println("END DOUBLE ARRAY");
    }

}