public class CmdRegister extends RecordedCommand{
    private Member m;
    private Club club = Club.getInstance();

    @Override
    public void execute(String[] cmdParts) {
        try {
            if (!club.checkID(cmdParts))
                throw new ExMemberIdUsed(cmdParts[1],club.inUseBy(cmdParts));  
            m = new Member(cmdParts[1], cmdParts[2]);
            club.addMember(m);
            RecordedCommand.addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExMemberIdUsed e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe(){
        club.removeMember(m);
        RecordedCommand.addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        club.addMember(m);
        RecordedCommand.addUndoCommand(this);
    }
    
}
