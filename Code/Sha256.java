class Sha256{
    static String message = "";
    
    public static void main(String[] args){
        if (parseArgs(args)){
            //PREPROCESSING BEGINS

            PreProcess preProcess = new PreProcess();
            Make32BitWords make32BitWords = new Make32BitWords();
            int[] bits = preProcess.preProcess(message);
            int[][] parsed = preProcess.parsing(bits);
            int[][] thirtyTwoBitWords = make32BitWords.make32BitWords(parsed);

            //PREPROCESSING FINISHED: thirtyTwoBitWords is an int[][] where each row contains the list of 32 bit words used in the next steps. 
            //(and there are as many rows as there are 512-bit chunks.)
        }

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
}