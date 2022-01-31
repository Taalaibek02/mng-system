public class ExQuotaLimit extends Exception{
    public ExQuotaLimit(){
        super("Loan quota exceeded.");
    }
}
