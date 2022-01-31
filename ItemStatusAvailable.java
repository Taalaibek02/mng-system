public class ItemStatusAvailable implements ItemStatus{
    @Override
    public void checkout(Member m, Item i) {
        i.setItemStatus(new ItemStatusBorrowed(m));
        m.addBorrowedItem();
    }
    @Override
    public String txtItemStatus() {
        return "Available";
    }
    @Override
    public void checkin(Member m, Item i) throws ExWrongCheckIn{
        throw new ExWrongCheckIn();
    }
    @Override
    public void request(Member m, Item item) throws ExWrongRequest{
        throw new ExWrongRequest();
    }
}
