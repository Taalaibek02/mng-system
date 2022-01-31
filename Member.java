public class Member implements Comparable<Member>{
    private String id;
    private String name;
    private Day joinDate;
    private int borrowedItems;
    private int requestedItems;

    public Member(String id, String name){
        this.id = id;
        this.name = name;
        this.borrowedItems = 0;
        this.requestedItems =0;
        this.joinDate = SystemDate.getInstance().clone();

    }

    @Override
    public String toString(){
        return String.format("%-5s%-9s%11s%7d%13d", id, name, joinDate, borrowedItems, requestedItems);
    }

    public static String getListingHeader(){
        return String.format("%-5s%-9s%11s%11s%13s","ID", "Name", "Join Date", "#Borrowed", "#Requested");
    }

    @Override
    public int compareTo(Member another){
        if (this.id.equals(another.id))
            return 0;
        else if(this.id.compareTo(another.id)>0)
            return 1;
        else
            return -1;
    }

    public String getID(){
        return this.id;
    }
    // @Override
    // public int compareTo(Member another){
    //     return this.id.compareTo(another.id);
    // }
    public String getName() {
        return this.name;
    }

    public void addBorrowedItem(){
        this.borrowedItems += 1;
    }

    public void addRequestedItem(){
        this.requestedItems +=1;
    }

    public void removeRequestedItem(){
        this.requestedItems -=1;
    }

    public void removeBorrowedItem(){
        this.borrowedItems -=1;
    }

    public int getBorrowed(){return borrowedItems;}
    public int getRequested(){return requestedItems;}
    
}
