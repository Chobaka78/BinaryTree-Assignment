public class BNode{
    private int val;
    private BNode left, right;

    public BNode (int v){
        val = v;
        left = right = null;
    }

    public int getVal(){
        return val;
    }

    public void setVal(int v){
        val = val;
    }

    public BNode getLeft(){
        return left;
    }

    public void setLeft(BNode lef){
        left = lef;
    }

    public BNode getRight(){
        return right;
    }

    public void setRight(BNode r){
        right = r;
    }
}