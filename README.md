# mng-system
Java Project. Warehouse management system is implemented. 
I have programmed a system that can keep track of items and customers statuses. 
The program registers new members in the system and adds new items in the storage. 
It allows system users to borrow and request any item that is present in a storage.
Once any item is checked out by somebody, the item status changes to "borrowed". Once the item has been returned to storage, its status changes to "available".
If the item is currently not available, the member can request to immediately get an item, after it will be checked in by another member. If several customers request an item,
a queue will be formed automatically.
"register" - registers new member in the system.
"arrive" - adds new item in the storage.
"listMembers" - list all existing members, their statuses and registration date.
"listItems" - list all existing items, their statuses, and queue of requests.
"checkout" - member borrows an item.
"checkin" - member returns back an item.
"request" - member requests an item.
"cancelRequest" - member cancels his request.
"undo" - undo the most recent command on item or member. ("register"/"arrive"/"checkout"/"checkin"/"request"/"cancelRequest")
"redo" - redo the most recent undo command on item or member.
