public class ExRequestQuota extends Exception{
    public ExRequestQuota(){
        super("Item request quota exceeded.");
    }
}
