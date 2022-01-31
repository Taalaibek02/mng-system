public class ExMemberIdUsed extends Exception{

    public ExMemberIdUsed(String problemid, String problemname){
        super("Member ID already in use: "+ problemid + " " + problemname);
    }
    public ExMemberIdUsed(){
        super("Member ID already in use!");
    }
}
