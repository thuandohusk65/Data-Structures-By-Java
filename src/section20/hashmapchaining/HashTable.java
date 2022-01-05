package section20.hashmapchaining;


public class HashTable {
    private HashItem[] hashTable;

    public HashTable(){
        hashTable = new HashItem[Constants.TABLE_SIZE];
    }

    public void put(int key, int value) {
        int hash = hash(key);
        if (hashTable[hash] == null) {
            System.out.println("No collision simple insertion ...");
            hashTable[hash] = new HashItem(key, value);
        } else {
            System.out.println("Collision when inserting with key " + key);
            HashItem hashItem = hashTable[hash];
            while (hashItem.getNextHashItem() != null) {
                hashItem = hashItem.getNextHashItem();
                System.out.println("Considering the next item in linked list " + hashItem.getValue());
            }
            System.out.println("Finally we found the place to insert ..." );
            hashItem.setNextHashItem(new HashItem(key, value));
        }
    }

    public int get(int key) {
        int generateArrayIndex = hash(key);
        HashItem hashItem = hashTable[generateArrayIndex];

        while (hashItem != null && hashItem.getKey() != key) {
            hashItem = hashItem.getNextHashItem();
        }

        if (hashItem == null) {
            return -1;
        }

        return hashItem.getValue();
    }


    private int hash(int key) {
        return key % Constants.TABLE_SIZE;
    }
}
