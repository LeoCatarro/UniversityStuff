public class StringNode {
    public String data;
    public StringNode next;

    public StringNode(String data, StringNode next){
        this.data = data;
        this.next = next;
    }
    public String toString(){
        return data + "";
    }
}
