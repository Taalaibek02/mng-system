public class ExWrongCheckIn extends Exception{
    public ExWrongCheckIn(){
        super("The item is not borrowed by this member.");
    }
}
