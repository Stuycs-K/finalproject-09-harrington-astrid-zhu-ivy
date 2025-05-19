class PreProcess{
    public static void main(String[] args){
        String input = "hello world";
        preProcess(input);
    }

    public static byte[] preProcess(String input){
        byte[] bytes = input.getBytes();
        bytes = append((byte)1, bytes);
        printArray(bytes);
        return new byte[0];
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

}