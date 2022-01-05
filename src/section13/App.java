package section13;

import section12.BinarySearchTree;

public class App {
    public static void main(String[] args) {
        SumOfTree sumOfTree = new SumOfTree();

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(12);
        bst.insert(4);
        bst.insert(20);
        bst.insert(1);
        bst.insert(8);
        bst.insert(16);
        bst.insert(27);
//        System.out.println(kSmallest.getNumOfTree(bst.getRoot()));
//        System.out.println(sumOfTree.getSumOfTree(bst.getRoot()));
    }
}
