package section12;

public class App {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(12);
        bst.insert(4);
        bst.insert(20);
        bst.insert(1);
        bst.insert(8);
        bst.insert(16);
        bst.insert(27);


        bst.traversal();
    }
}
