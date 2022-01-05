package section20.generichashtable;

@SuppressWarnings("unchecked")
public class HashTable<K, V> {
    private K[] keys;
    private V[] values;
    private int numOfItems;

    public K[] getKeys() {
        return keys;
    }

    public V[] getValues() {
        return values;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public int getCapacity() {
        return capacity;
    }

    private int capacity; //it is the maximum number of items that can be inserted in the table: numOfItems <= capacity

    public HashTable() {
        this.keys = (K[]) new Object[Constants.TABLE_SIZE];
        this.values = (V[]) new Object[Constants.TABLE_SIZE];
        this.capacity = Constants.TABLE_SIZE;
        this.numOfItems = 0;
    }

    public HashTable(int newCapacity) {
        this.keys = (K[]) new Object[newCapacity];
        this.values = (V[]) new Object[newCapacity];
        this.capacity = newCapacity;
        this.numOfItems = 0;
    }

    //O(1)
    public int size() {
        return this.numOfItems;
    }

    //O(1)
    public boolean isEmpty() {
        return this.numOfItems == 0;
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        int index = hash(key);

        while (!keys[index].equals(key)) {
            index = (index + 1) % capacity;
        }
        keys[index] = null;
        values[index] = null;
        //check the next value ...
        index = (index + 1) % capacity;
        while (keys[index] != null) {
            K tempKey = keys[index];
            V tempValue = values[index];
            keys[index] = null;
            values[index] = null;
            //have to numOfItems-- because put have numOfItems++
            numOfItems--;
            put(tempKey, tempValue);

            index = (index + 1) % capacity;
        }

        numOfItems--;
        if (numOfItems <= capacity / 3) {
            resize(capacity / 2);
        }

    }

    public void put(K key, V value) {
        if (key == null || values == null) {
            return;
        }

        if (numOfItems > (capacity * 0.75)) {
            // O(1) -> O(N)
            resize(capacity * 2);
        }
        int index = hash(key);
        while (keys[index] != null) {
            //update since this key has existed
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
            index = (index + 1) % capacity;
        }
        keys[index] = key;
        values[index] = value;
        numOfItems++;

    }

    //O(N) we have to make sure we minimize the number of resize operation!!
    private void resize(int newCapacity) {
        HashTable<K, V> newTable = new HashTable<>(newCapacity);

        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                newTable.put(keys[i], values[i]);
            }
        }
        this.keys = newTable.getKeys();
        this.values = newTable.getValues();
        capacity = newCapacity;
    }

    //need to test if you give a key that isn't exist in the keys
    public V get(K key) {
        if (key == null) {
            return null;
        }
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            index = (index + 1) % capacity;
        }
        return null;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }
}
