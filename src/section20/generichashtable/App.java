package section20.generichashtable;


public class App {
    public static void main(String[] args) {
        HashTable<Integer,Integer> hashTable = new HashTable();
        hashTable.put(1,100);
        hashTable.put(11,200);
        hashTable.put(21,300);
        hashTable.put(31,400);
        hashTable.put(6,500);
        hashTable.put(16,600);
//        hashTable.put(3,12);
//        hashTable.put(4,13);
//        hashTable.put(5,14);
//        hashTable.put(6,15);
//        hashTable.put(7,16);
//        hashTable.put(8,17);

        hashTable.remove(1);
        System.out.println(hashTable.get(11));
        System.out.println(hashTable.get(1));
        System.out.println(hashTable.get(21));
        System.out.println(hashTable.get(6));
        System.out.println(hashTable.get(16));
//        System.out.println(hashTable.get(2));
//        System.out.println(hashTable.get(3));
//        System.out.println(hashTable.get(4));
//        System.out.println(hashTable.get(5));
//        System.out.println(hashTable.get(6));
//        System.out.println(hashTable.get(7));
//        System.out.println(hashTable.get(8));
//        System.out.println(hashTable.get(9));
//        System.out.println(hashTable.get(10));
//        System.out.println(hashTable.get(12));
//        System.out.println(hashTable.get(13));
//        System.out.println(hashTable.get(14));



    }
}
