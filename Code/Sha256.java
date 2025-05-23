import java.util.Arrays;

class Sha256{
    // static String message = "";
    static String message = "a b c d e f g h i j k l m n o p q r s t u v w x y z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
    // static String message = "hello world";

    public static void main(String[] args){
        // if (parseArgs(args)){
        //     //PREPROCESSING BEGINS

        //     PreProcess preProcess = new PreProcess();
        //     Make32BitWords make32BitWords = new Make32BitWords();
        //     int[] bits = preProcess.preProcess(message);
        //     int[][] parsed = preProcess.parsing(bits);
        //     int[][] thirtyTwoBitWords = make32BitWords.make32BitWords(parsed);

        //     //PREPROCESSING FINISHED: thirtyTwoBitWords is an int[][] where each row contains the list of 32 bit words used in the next steps. 
        //     //(and there are as many rows as there are 512-bit chunks.)
        // }

        // testing
        PreProcess preProcess = new PreProcess();
        Make32BitWords make32BitWords = new Make32BitWords();
        int[] bits = preProcess.preProcess(message);
        int[][] parsed = preProcess.parsing(bits);
        int[][] thirtyTwoBitWords = make32BitWords.make32BitWords(parsed);
        process(thirtyTwoBitWords);
    }

    public static boolean parseArgs(String[] args){
        if (args.length != 1){
            System.out.println("Please provide only one argument to hash. (java Sha256 message_to_hash)");
            return(false);
        }
        else{
            message = args[0];
            return(true);
        }
    }

    public static void process(int[][] words32){
        Transform.initializeH();
        String hash = "";
        for (int i = 0; i < words32.length; i++){
            Transform.msgSchedule(words32[i]);
            hash = Transform.compression(Transform.k, words32[i]);
        }
        System.out.println(hash);
    }
}