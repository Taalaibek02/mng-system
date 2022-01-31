public class CmdListMembers extends RecordedCommand{
    Club club = Club.getInstance();
    @Override
    public void execute(String[] cmdParts){
        club.listClubMembers();
    }
    @Override 
    public void undoMe(){}
    @Override 
    public void redoMe(){}
}
