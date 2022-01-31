public class CmdCancelRequest extends RecordedCommand{
    private Item i;
    private Member m;
    Club club = Club.getInstance();
    @Override
    public void execute(String[] cmdParts) {
        try{
            if(club.findMember(cmdParts[1])==null)
                    throw new ExMemberNotFound();
                m = club.findMember(cmdParts[1]);
                if(club.findItem(cmdParts[2])==null)
                    throw new ExItemNotFound();
                i = club.findItem(cmdParts[2]);
                if (!i.requestQueue.contains(m))
                    throw new ExRequestNotFound();
                i.cancelRequest(m);
                clearRedoList();
                RecordedCommand.addUndoCommand(this);
                System.out.println("Done.");
        }catch (ExMemberNotFound | ExItemNotFound e) {
            System.out.println(e.getMessage());
        } catch (ExRequestNotFound e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void undoMe() {
        try {
            i.request(m);
            RecordedCommand.addRedoCommand(this);
        } catch (ExWrongRequest | ExSameMember | ExAlreadyRequested e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void redoMe() {
        try{
            if (!i.requestQueue.contains(m))
                throw new ExRequestNotFound();
            i.cancelRequest(m);
            RecordedCommand.addUndoCommand(this);
        }catch(ExRequestNotFound e){
            System.out.println(e.getMessage());
        }
    }
}
