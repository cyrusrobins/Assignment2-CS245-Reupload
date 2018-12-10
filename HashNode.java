public class HashNode {
    protected String key; //instance data for each node
    protected String value;
    protected HashNode next;

    public HashNode(String key, String value){ //instantiate the instance data
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
