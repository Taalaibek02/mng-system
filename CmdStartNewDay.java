public class CmdStartNewDay extends RecordedCommand{
    private String oldDate;
    private String newDate;
    @Override
    public void execute(String[] cmdParts){
        oldDate = SystemDate.getInstance().toString();
        newDate = cmdParts[1];
        SystemDate.getInstance().set(newDate);
        clearRedoList();
        addUndoCommand(this);
        System.out.println("Done.");
    }
    @Override
    public void undoMe() {
        SystemDate.getInstance().set(oldDate);
        RecordedCommand.addRedoCommand(this);
    }
    @Override
    public void redoMe() {
        SystemDate.getInstance().set(newDate);
        RecordedCommand.addUndoCommand(this);
    }
}
