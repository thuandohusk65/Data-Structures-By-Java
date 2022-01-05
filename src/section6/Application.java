package section6;

public class Application {
    public static void main(String[] args) {
//        LinkedList<Person> people = new LinkedList<>();
//        people.insert(new Person(20, "Thuan Do"));
//        people.insert(new Person(20, "Le Duc"));
//        people.insert(new Person(20, "Thanh Vu"));
//        people.traverse();
//        people.remove(new Person(20, "Thuan Do"));
//        people.traverse();
    List<Integer> myLinkedList = new LinkedList<>();
    myLinkedList.insert(3);
    myLinkedList.insert(2);
    myLinkedList.insert(1);
    myLinkedList.insert(5);
    myLinkedList.insert(9);
    myLinkedList.insert(3);
    myLinkedList.insert(11);
    myLinkedList.insert(119);
        myLinkedList.reverse();
        myLinkedList.traverse();
//    System.out.println(myLinkedList.getMiddleNode());

    }
}
