public class ExItemIdUsed extends Exception{
    public ExItemIdUsed(String problemid, String problemname){
        super("Item ID already in use: "+ problemid + " " + problemname);
    }
    public ExItemIdUsed(){
        super("Item ID already in use!");
    }
}
