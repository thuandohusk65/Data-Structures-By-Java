package section15;

public class App {
    public static void main(String[] args) {
        Tree<Integer> rbt = new RedBlackTree<>();
        rbt.insert(5);
        rbt.insert(4);
        rbt.insert(2);

        rbt.traverse();
    }
}
