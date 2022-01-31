public interface ItemStatus {
    public String txtItemStatus(); 
    public void checkout(Member m, Item i) throws ExNotAvailableItem;
    public void checkin(Member m, Item item) throws ExWrongCheckIn;
    public void request(Member m, Item item) throws ExWrongRequest, ExSameMember, ExAlreadyRequested;
}
