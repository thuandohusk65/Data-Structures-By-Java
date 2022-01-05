package section13;


public class KSmallest {

    public Integer getKSmallest(Node node, int k) {
        if (getNumOfTree(node) < k) {
            return null;
        }
        int n = getNumOfTree(node.getLeftNode()) + 1;
        if (n == k) {
            return node.getData();
        } else if (n > k) {
            return getKSmallest(node.getLeftNode(), k);
        } else {
            /*if the number of nodes in the left subtree is smaller then the k-th
            smallest item then we can discard the left subtree and consider the right subtree
            NOW WE ARE NOT LOOKING FOR THE K-TH BUT THE K-Nth SMALLEST ITEM
             */
            return getKSmallest(node.getRightNode(), k - n);
        }
    }

    //get the size of the tree
    public int getNumOfTree(Node node) {
        //this is the base case
        if (node == null) {
            return 0;
        } else if (node.getLeftNode() == null || node.getRightNode() == null) {
            return node.getData();
        }
        //recursively sum up the size of the left subtree + size of right subtree
        //size of tree = size left subtree + size of right subtree + 1 (because if the root)
        return getNumOfTree(node.getLeftNode()) + getNumOfTree(node.getRightNode());
    }

}
