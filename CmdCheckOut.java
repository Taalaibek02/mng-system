public class CmdCheckOut extends RecordedCommand{
    private Item i;
    private Member m;
    Club club = Club.getInstance();

    @Override
    public void execute(String[] cmdParts) {
        try {
            if(club.findMember(cmdParts[1])==null)
                throw new ExMemberNotFound();
            m = club.findMember(cmdParts[1]);
            if(club.findItem(cmdParts[2])==null)
                throw new ExItemNotFound();
            i = club.findItem(cmdParts[2]);
            if(m.getBorrowed()==6)
                throw new ExQuotaLimit();
            i.checkout(m);
            clearRedoList();
            RecordedCommand.addUndoCommand(this);
            System.out.println("Done.");
        } catch (ExMemberNotFound | ExItemNotFound e) {
            System.out.println(e.getMessage());
        } catch (ExNotAvailableItem e) {
            System.out.println(e.getMessage());
        } catch (ExQuotaLimit e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        m.removeBorrowedItem();
        i.setItemStatus(new ItemStatusAvailable() {});
        RecordedCommand.addRedoCommand(this);
    }
    @Override
    public void redoMe() {
        try {
            i.checkout(m);
            RecordedCommand.addUndoCommand(this);
        } catch (ExNotAvailableItem e) {
            System.out.println(e.getMessage());
        }
        
    }
}
