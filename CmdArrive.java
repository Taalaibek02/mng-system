public class CmdArrive extends RecordedCommand{
    Item i;
    Club club = Club.getInstance();
    @Override
    public void execute(String[] cmdParts) {
        try{
            if(!club.checkItemId(cmdParts))
                throw new ExItemIdUsed(cmdParts[1], club.inUseByItem(cmdParts));
            i = new Item(cmdParts[1], cmdParts[2]);
            club.addItem(i);
            RecordedCommand.addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        }catch (ExItemIdUsed e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        club.removeItem(i);
        RecordedCommand.addRedoCommand(this);
    }
    @Override
    public void redoMe() {
        club.addItem(i);
        RecordedCommand.addUndoCommand(this);
    }
}
