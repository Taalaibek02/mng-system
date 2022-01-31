public class ItemStatusOnHold implements ItemStatus{
    private Member requestingMember;
    private Day date;
    private String dateStr;
    private static String onholdDate;
    @Override
    public void checkout(Member m, Item i) throws ExNotAvailableItem {
        i.setItemStatus(new ItemStatusBorrowed(m));
        m.addBorrowedItem();
    }
    public ItemStatusOnHold(Item i){
        requestingMember = i.requestQueue.peek();
        date = SystemDate.getInstance();
        dateStr = date.toString();
        onholdDate = Day.setDeadline(dateStr);
        requestingMember.removeRequestedItem();
    }
    @Override
    public String txtItemStatus() {
        return "On holdshelf for " + requestingMember.getID() + " " + requestingMember.getName() + " until " + onholdDate;//id, name, onhold due date
    }
    @Override
    public void checkin(Member m, Item item) throws ExWrongCheckIn {
        throw new ExWrongCheckIn();
    }
    @Override
    public void request(Member m, Item item) throws ExSameMember, ExAlreadyRequested {
        if(m.getID() == requestingMember.getID())
            throw new ExSameMember();
        if(item.requestQueue.contains(m))
            throw new ExAlreadyRequested();
        item.itemRequestQueue(m);
        m.addRequestedItem();
    }
    public static String getOnHoldDate(){return onholdDate;}
}
