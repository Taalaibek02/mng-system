import java.util.LinkedList;
import java.util.Queue;
public class Item implements Comparable<Item>{
    private String itemId;
    private String itemName;
    private Day arrivalDate;
    private ItemStatus status;
    Queue<Member> requestQueue;
    public Item(String itemId, String itemName){
        this.itemId = itemId;
        this.itemName = itemName;
        status = new ItemStatusAvailable();
        requestQueue = new LinkedList<Member>();
        this.arrivalDate = SystemDate.getInstance().clone();
    }
    @Override
    public String toString(){
        if(requestQueue.size() > 0 && getItemStatus().contains("holdshelf"))
            return String.format("%-5s%-17s%11s   %s", itemId, itemName, arrivalDate, status.txtItemStatus());
        else if(requestQueue.size()>0 && !getItemStatus().contains("holdshelf"))    
            return String.format("%-5s%-17s%11s   %s", itemId, itemName, arrivalDate, status.txtItemStatus()+" + " + requestQueue.size() + " request(s): " + getIdOfQueueMembers());  
        else
            return String.format("%-5s%-17s%11s   %s", itemId, itemName, arrivalDate, status.txtItemStatus());
    }
    public static String getListingHeader(){
        return String.format("%-5s%-17s%11s   %s", "ID", "Name", "  Arrival  ", "Status");  
    }
    @Override
    public int compareTo(Item another){
        if(this.itemId.equals(another.itemId))
            return 0;
        else if (this.itemId.compareTo(another.itemId)>0)
            return 1;
        else
            return -1;
    }
    public String getItemId(){return this.itemId;}
    public String getItemName(){return this.itemName;}
    public void setItemStatus(ItemStatus is){
        status = is;
    }
    public String getItemStatus(){
        return status.toString();
    }
    public void checkout(Member m)throws ExNotAvailableItem{
        status.checkout(m,this);
    }
    public void checkin(Member m) throws ExWrongCheckIn {
        status.checkin(m, this);
    }
    public void request(Member m) throws ExWrongRequest, ExSameMember, ExAlreadyRequested{
        status.request(m,this);
    }
    public void cancelRequest(Member m){
        requestQueue.remove(m);
        m.removeRequestedItem();
    }
    public void itemRequestQueue(Member m){
        requestQueue.add(m);
    }
    public String getIdOfQueueMembers(){
        String line = "";
        for(Member m: requestQueue){
            line += m.getID() + " ";
        }
        return line;
    }
    public String getOnHoldDate(){return ItemStatusOnHold.getOnHoldDate();}
}
