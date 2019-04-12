/*
*BTree.java
* Usman Farooqi
* This is the Btree class with the following methods:
* add - this adds an element to the tree
* depth - takes an integer and returns the depth of where that element was found, returns -1 if it isn't found
* countLeaves - returns number of leaves that have no children in the tree
* height - returns the height of the tree
* isAncestor Takes two integers and checks if the first is an ancestor of the second
* delete - this removes an element (node) from the tree
* isBalanced - this will return either true or false depending on if the tree is balanced or not
* add - this overloads the in - class add method and makes a copied tree
 */
public class BTree{ // BTree class contains all the functions
    private BNode root;
    public static final int IN = 1; //Inorder
    public static final int PRE = 2; //Preorder
    public static final int POST = 3; //Postorder

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

// Method 1  add
    private int depth (int n, BNode branch){
        if(branch == null){
            return -1; // returns -1 if nothing is in the tree / not found
        }
        if (branch.getVal() == n){
            return 1; // returns 1 as the depth is there is only 1 element in the tree
        }
        if(n < branch.getVal()){ // if the element is to the left
            int L = depth(n, branch.getLeft());
            if(branch.getLeft() != null) {
//                return L + 1;
                return L == -1? -1: L + 1; // ternary operator (if l is -1 then return -1 else return L + 1 which is the height)
            }
            else{
                return -1; // return -1
            }
        }
        if (n > branch.getVal()){ // if element is to the left
            int R = depth(n, branch.getRight());
            if(branch.getRight() !=null) {
//                return R + 1;
                return R == -1? -1: R + 1; // ternary operator (if R is -1 then return -1 else return R + 1 which is the height
            }
            else{
                return -1;
            }
        }
        else{
            return -1;
        }
    }

    public int depth(int n){
        return depth(n, root);
    }

// Method 2 Display
    public void display() { // displays the tree in given order takes no permameters
        System.out.println(display(IN,root));
    }

    public void display(int type) {
        System.out.println(display(type,root));
    }

    public String display(int n, BNode branch) {
        if (branch == null) { // if empty (base case)
            return "";
        }
        else {
            if (n == IN) { // this will be in order left then root then right
                return display(n,branch.getLeft()) + Integer.toString(branch.getVal()) + display(n,branch.getRight());
            }
            else if (n == PRE) { // this will be preorder root then left then right
                return Integer.toString(branch.getVal()) + display(n,branch.getLeft()) + display(n,branch.getRight());
            }
            else if (n == POST) { // this will be postorder left then right then root
                return Integer.toString(branch.getVal())  + display(n,branch.getRight()) + display(n,branch.getLeft());
            }
            else {
                return "";
            }
        }
    }

// Method 3 countLeaves
    public int countLeaves(){
        return countLeaves(root);
    }

    public int countLeaves(BNode branch){
        if(branch == null){
            return 0;
        }
        else if(branch.getLeft() == null && branch.getRight() == null){
            return 1; // if the root is the only element in the Btree
        }
        return countLeaves(branch.getLeft()) + countLeaves(branch.getRight()); // this will recursively check both the left and right side until it gets to leaves
    }

// Method 4 Height
    public int height(){
        return height(root);
    }
    public int height(BNode branch){
        if(branch == null){
            return 0; // if there is no element in the Btree
        }
        else if(branch.getLeft() == null && branch.getRight() == null){
            return 1; // if root is no node to the left or the right
        }
        else{
            int left = 1 + height(branch.getLeft()); // height of the left side of the tree
            int right = 1 + height(branch.getRight()); // height of the right side of the tree
            if(left > right){ // if the height of left is greater
                return left + 1; // return the left height + 1
            }
            else{ // if the right height is greater
                return right + 1; // return the right height + 1
            }
        }
    }

// Method 5 isAncestor
    public BNode find(int n){ // helper method that will be used in isAncestor method
        return find(n, root);
    }
    public BNode find(int n, BNode branch){
        if(branch == null || branch.getVal() == n){
            return branch;
        }
        else{
            return n < branch.getVal() ? find(n, branch.getLeft()) : find(n, branch.getRight());
        }
    }
    public boolean isAncestor(int p, int c){
        if(find(c,find(p)) != null){ // setting c (child) as root in the find method to see if it can be an ancestor
            return true;
        }
        else{
            return false;
        }
    }

// Method 6 delete
    public void delete(int n){
        delete(n, root);
    }
    public void delete(int n, BNode branch){
        if(branch == null){
            return;
        }
        else if(root.getVal() == n){ // if the root is being deleted
            BNode rightNode = branch.getRight();
            root = root.getLeft();
            moveN(rightNode, root);
        }
        else if(branch.getRight() != null && branch.getRight().getVal() == n){ // if the element (node) to delete is to the right
            BNode rightNode = branch.getRight().getRight();
            branch.setRight(branch.getRight().getLeft());
            moveN(rightNode, branch.getLeft());
        }
        else if(branch.getLeft() != null && branch.getLeft().getVal() == n){ // if the element (node) to delete is to the left
            BNode leftNode = branch.getLeft().getRight();
            branch.setLeft(branch.getLeft().getLeft());
            moveN(leftNode, branch.getLeft());
        }
        else{
            if(n > branch.getVal()){ // if its somewhere on the right
                delete(n, branch.getRight());
            }
            else{
                delete(n, branch.getLeft());
            }
        }
    }
    // this is a method that helps in the delete method
    public void moveN(BNode v, BNode branch){
        if(v == null){
            return;
        }
        if(v.getVal() <= branch.getVal()){
            if(branch.getLeft() == null){
                branch.setLeft(v);
            }
            else{
                moveN(v, branch.getLeft());
            }
        }
        else if(v.getVal() > branch.getVal()){
            if(branch.getRight() == null){
                branch.setRight(v);
            }
            else{
                moveN(v, branch.getRight());
            }
        }
    }

// Method 7 Balanced
    public boolean isBalanced(){
        return (isBalanced(root));
    }
    public boolean isBalanced(BNode branch){
        int leftH, rightH;
        if(branch == null){
            return true;
        }
        leftH = height(branch.getLeft()); // gets the height of the left side of the Btree
        rightH = height(branch.getRight()); // gets the height of the right side of the Btree

        if(java.lang.Math.abs(leftH - rightH) <= 1 && isBalanced(branch.getLeft()) && isBalanced(branch.getRight())){ // checks if the difference between two sides is <= 1
            return true; // Btree is balanced
        }
        else{
            return false; // Btree isn't balanced
        }
    }
// Method 8 add
    public void add(BTree t){
        add(t.root);
    }
    public void add(BNode b){
        if(b.getLeft() == null && b.getRight() == null){ //if the tree being added is just the root
            add(b.getVal()); // add normally
        }
        else{
            if(b.getLeft() == null){ //if there is only an element to the right of it (nothing on left)
                add(b.getRight());
            }
            else if(b.getRight() == null){ //if there is only an element to the left of it (nothing on the right)
                add(b.getLeft());
            }
            else{ //if there are elements on both sides (right and left)
                add(b.getRight());
                add(b.getLeft());
            }
            add(b.getVal());
        }
    }

//    public void sprout(){
//        BNode tmp = null;
//        sprout(root, tmp);
//    }
//
//    public void sprout(BNode branch, BNode addbranch){
//        if(branch.getLeft() == null || branch.getRight() == null){
//            add((addbranch.getVal() + branch.getVal())/2);
//        }
//        else if(branch.getLeft().getLeft() == null){
//            addbranch = branch;
//        }
//        else if (branch.getLeft().getRight() == null){
//            addbranch = branch;
//        }
//        else if(branch.getRight().getRight() == null){
//            addbranch = branch;
//        }
//        else {
//            sprout(branch.getLeft(), addbranch);
//            sprout(branch.getRight(), addbranch);
//        }
//    }

    public void sprout(){
        sprout(root);
    }

    public void sprout(BNode temp) {
        int val;
        if (temp == null) {
            return;
        }
        else if (temp.getRight().getLeft() == null && temp.getRight().getRight() == null || temp.getLeft().getLeft() == null && temp.getLeft().getRight() == null) {
            if (temp.getRight().getLeft() == null && temp.getRight().getRight() == null) {
                val = (temp.getVal() + temp.getRight().getVal()) / 2;
                temp.getRight().setLeft(new BNode(val));
            }
            if (temp.getLeft().getLeft() == null && temp.getLeft().getRight() == null){
                val = (temp.getVal() + temp.getLeft().getVal()) /2;
                temp.getLeft().setRight(new BNode(val));
            }
        }
        else {
            sprout(temp.getRight());
            sprout(temp.getLeft());
        }
    }

    @Override
    public String toString(){ // this method will print the Btree in a nice (see - able way)
        return ("<" + stringify(root) + ">").replace(", >", ">");
    }

    public String stringify(BNode branch){
        if(branch == null){
            return "";
        }
        return stringify(branch.getLeft()) + branch.getVal() + ", " + stringify(branch.getRight());
    }
}