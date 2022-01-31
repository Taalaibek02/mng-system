public class CmdListItems extends RecordedCommand{
    Club club = Club.getInstance();
    @Override
    public void execute(String[] cmdParts) {
        club.listItems();
    }
    @Override
    public void undoMe() {}
    @Override
    public void redoMe(){}
}
