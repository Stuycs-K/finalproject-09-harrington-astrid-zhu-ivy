class Sha256{
    String message = "";
    
    public static void main(String[] args){
        if (parseArgs(args)){

        }

        //PREPROCESSING BEGINS
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