public class Hashtable {

    private HashNode[] buckets = new HashNode[314527]; //create the maximum number of buckets in our array

    public Hashtable(){
        for(HashNode bucket: buckets) //instantiate all of the buckets to null
            bucket = null;
    }

    public int getHashCode(String key){ //function to return the array index for a given key
        return Math.abs(key.hashCode())%314527;
    }

    public Boolean containsKey(String key) {
        int index = getHashCode(key); //get the index to look in
        HashNode curr = buckets[index];
        while(curr!=null){ //while there are still nodes to check
            if(curr.key==key) //if the current node's key matches the key return true
                return true;
            curr = curr.next;
        }
        return false; //return false if none of the nodes have a matching key
    }

    public String get(String key) {
        int index = getHashCode(key);
        HashNode curr = buckets[index];
        while(curr!=null){
            if(curr.key.equals(key)) //if the keys match
                return curr.value; //return the value of that key
            curr = curr.next;
        }
        return null; //return null if there were no matches
    }

    public void put(String key, String value){
        int index = getHashCode(key);
        if(buckets[index]==null){ //if there's nothing in that bucket to start
            buckets[index] = new HashNode(key,value); //create a new hashnode to be inserted at that location
        }
        else{
            HashNode curr = buckets[index];
            while(curr!=null){ //otherwise, check through all of the nodes at that index
                if(curr.key.equals(key)) { //if their keys match, replace the old value with the new one
                    curr.value = value;
                    return;
                }
                curr = curr.next;
            }
            HashNode oldHead = buckets[index]; //set the previous head of that linked list to be the next node after the new node
            HashNode newNode = new HashNode(key, value);
            newNode.next = oldHead;
            buckets[index] = newNode; //set the new node as the head of the linked list
        }
    }

    public String remove(String key){
        int index = getHashCode(key);
        HashNode curr = buckets[index];
        try {
            if (curr.key.equals(key)) { //if you find matching keys
                String returnValue = curr.value; //save the value to return before erasing it
                buckets[index] = curr.next; //if it's the head item being removed, set the head to be the second item in the list
                return returnValue;
            }
            while (curr.next != null) {
                if (curr.next.key.equals(key)) {
                    String returnValue = curr.next.value;
                    curr.next = curr.next.next; //for non-head members, link the nodes on either side of the removed node
                    return returnValue;
                } else {
                    curr = curr.next;
                }
            }
        }
        catch(Exception e){
            System.out.println("The key you're looking for isn't here"); //catch the exception if no node is found to remove
        }
        return null;
    }
}
