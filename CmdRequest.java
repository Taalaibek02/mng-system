public class CmdRequest extends RecordedCommand{
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
            if(m.getRequested()==3)
                throw new ExRequestQuota();
            i.request(m);
            clearRedoList();
            RecordedCommand.addUndoCommand(this);
            System.out.println("Done. This request is no. "+i.requestQueue.size() + " in the queue.");
        }catch (ExMemberNotFound | ExItemNotFound e) {
            System.out.println(e.getMessage());
        }catch (ExWrongRequest e) {
            System.out.println(e.getMessage());
        }catch (ExRequestQuota e){
            System.out.println(e.getMessage());
        } catch (ExSameMember e) {
            System.out.println(e.getMessage());
        } catch (ExAlreadyRequested e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void undoMe() {
        i.requestQueue.remove(m);
        m.removeRequestedItem();
        RecordedCommand.addRedoCommand(this);
    }
    @Override
    public void redoMe() {
        try{
            i.request(m);
            RecordedCommand.addUndoCommand(this);
        } catch (ExWrongRequest e){
            System.out.println(e.getMessage());
        } catch (ExSameMember e) {
            System.out.println(e.getMessage());
        } catch (ExAlreadyRequested e) {
            System.out.println(e.getMessage());
        }
    }
}
