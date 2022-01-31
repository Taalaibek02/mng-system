public class ItemStatusBorrowed implements ItemStatus {
    private Member borrowingMember;
    private Day loanDate;

    public ItemStatusBorrowed(Member m) {
        borrowingMember = m;
        loanDate = SystemDate.getInstance().clone();
    }

    @Override
    public void checkout(Member m, Item i) throws ExNotAvailableItem {
        throw new ExNotAvailableItem();
    }

    @Override
    public String txtItemStatus() {
        return "Borrowed by " + borrowingMember.getID() + " " + borrowingMember.getName() + " on " + loanDate;
    }

    @Override
    public void checkin(Member m, Item i) throws ExWrongCheckIn {
        if (m.getID() != borrowingMember.getID())
            throw new ExWrongCheckIn();
        if (i.requestQueue.size() == 0) {
            i.setItemStatus(new ItemStatusAvailable());
            m.removeBorrowedItem();
        } else {
            i.setItemStatus(new ItemStatusOnHold(i));
            m.removeBorrowedItem();
        }
    }

    @Override
    public void request(Member m, Item item) throws ExSameMember, ExAlreadyRequested {
        if (m.getID() == borrowingMember.getID())
            throw new ExSameMember();
        if (item.requestQueue.contains(m))
            throw new ExAlreadyRequested();
        item.itemRequestQueue(m);
        m.addRequestedItem();
    }
}
