package section20.hashmaplinenearprobing;


public class HashTable {
    private HashItem[] hashTable;

    public HashTable() {
        hashTable = new HashItem[Constants.TABLE_SIZE];
    }

    public int get(int key) {
        int generatedIndex = hashFuction(key);

        while (hashTable[generatedIndex] != null && hashTable[generatedIndex].getKey() != key) {
            generatedIndex = (generatedIndex + 1) % Constants.TABLE_SIZE;
            System.out.println("Hopping to the next index: " + generatedIndex);
        }
        if (hashTable[generatedIndex] == null) {
            return -1;
        } else {
            return hashTable[generatedIndex].getValue();
        }
    }

    public void put(int key, int value) {
        int count = 0;
        int generatedIndex = hashFuction(key);
        System.out.println("Put() method called with value : " + value + " - generatedIndex: " + generatedIndex);
        while (hashTable[generatedIndex] != null && count < hashTable.length) {
            generatedIndex = (generatedIndex + 1) % Constants.TABLE_SIZE;
            System.out.println("Collision -> nextIndex: " + generatedIndex);

            count++;
        }
        if (count == hashTable.length) {
            return;
        }
        System.out.println("Inserted finnaly with index: " + generatedIndex);
        hashTable[generatedIndex] = new HashItem(key, value);
    }

    private int hashFuction(int key) {
//        return key % Constants.TABLE_SIZE;
        return 1;
    }
}
