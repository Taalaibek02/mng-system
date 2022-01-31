public class ExSameMember extends Exception{
    public ExSameMember(){
        super("The item is already borrowed by the same member.");
    }
}
