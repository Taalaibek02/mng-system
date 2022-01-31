import java.util.ArrayList;
import java.util.Collections;

public class Club {
    private ArrayList<Member> allMembers;
    private ArrayList<Item> allItems;
    private static Club instance = new Club();
    public static Club getInstance() {
        return instance;
    }

    private Club(){
        allMembers = new ArrayList<>();
        allItems = new ArrayList<>();
    }

    public void addMember(Member m) {
        allMembers.add(m);
        Collections.sort(allMembers);
    }

    public void removeMember(Member m){
        allMembers.remove(m);
    }

    //code for checking if ID is available
    public Boolean checkID(String[] cmdParts){
        for(Member m:allMembers){
            if(cmdParts[1].equals(m.getID()))
                return false;
        }
        return true;
    } 

    //code for checking if itemID is available
    public Boolean checkItemId(String[] cmdParts){
        for(Item i : allItems){
            if(cmdParts[1].equals(i.getItemId()))
                return false;
        }
        return true;
    }

    //code for returning user name which owns particular id
    public String inUseBy(String[] cmdParts){
        for (Member m: allMembers){
            if(cmdParts[1].equals(m.getID()))
                return m.getName();
        }
        return null;   
    }

    //code for returning item name which owns particular itemId
    public String inUseByItem(String[] cmdParts){
        for(Item i: allItems){
            if(cmdParts[1].equals(i.getItemId()))
                return i.getItemName();
        }
        return null;
    }

    public void listClubMembers(){
        System.out.println(Member.getListingHeader());
        for(Member m: allMembers){
            System.out.println(m.toString());
        }
    }

    public Member findMember(String id){
        for (Member m : allMembers){
            if(m.getID().equals(id))
                return m;
        }
        return null;
    }

    public Item findItem(String id){
        for(Item i: allItems){
            if(i.getItemId().equals(id))
                return i;
        }
        return null;
    }

    public void listItems(){
        System.out.println(Item.getListingHeader());
        for(Item i:allItems)
            System.out.println(i.toString());
    }

    public void addItem(Item i) {
        allItems.add(i);
        Collections.sort(allItems);
    }

    public void removeItem(Item i) {
        allItems.remove(i);
    }
  
}
