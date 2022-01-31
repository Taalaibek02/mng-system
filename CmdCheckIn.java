public class CmdCheckIn extends RecordedCommand{
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
            i.checkin(m);
            clearRedoList();
            RecordedCommand.addUndoCommand(this);
            if(i.requestQueue.size() == 0)
                System.out.println("Done.");
            else{
                System.out.println("Item [" + i.getItemId() + " " + i.getItemName() +"] is ready for pick up by [" + i.requestQueue.peek().getID() + " " + i.requestQueue.poll().getName() + "]. On hold due on " + i.getOnHoldDate() + ".");
                System.out.println("Done.");
            }
        } catch (ExMemberNotFound | ExItemNotFound e) {
            System.out.println(e.getMessage());
        } catch (ExWrongCheckIn e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void undoMe() {
        try {
            i.checkout(m);
            RecordedCommand.addRedoCommand(this);
        } catch (ExNotAvailableItem e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void redoMe() {
        try {
            i.checkin(m);
            RecordedCommand.addUndoCommand(this);
        } catch (ExWrongCheckIn e) {
            System.out.println(e.getMessage());
        }
    }
}
