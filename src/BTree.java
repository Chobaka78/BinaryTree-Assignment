public class BTree{
    private BNode root;

    public BTree (){
        root = null;
    }

    public void add(int n){
        if(root == null){
            root = new BNode(n);
        }
        else{
            add(n,root);
        }
    }

    public void add(int n , BNode branch){
        if(n > branch.getVal()){
            if(branch.getRight() == null){
                branch.setRight(new BNode(n));
            }
            else{
                add(n,branch.getRight());
            }
        }
        else if (n < branch.getVal()) {
            if(branch.getLeft() == null){
                branch.setLeft(new BNode(n));
            }
            else{
                add(n,branch.getLeft());
            }
        }
    }

    @Override
    public String toString(){
        return ("<" + stringify(root) + ">").replace(", >", ">");
    }

    public String stringify(BNode branch){
        if(branch == null){
            return "";
        }
        return stringify(branch.getLeft()) + branch.getVal() + ", " + stringify(branch.getRight());
    }
}