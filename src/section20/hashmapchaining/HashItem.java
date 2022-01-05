package section20.hashmapchaining;

public class HashItem {
    private int key;
    private int value;
    private HashItem nextHashItem;

    public HashItem(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HashItem getNextHashItem() {
        return nextHashItem;
    }

    public void setNextHashItem(HashItem nextHashItem) {
        this.nextHashItem = nextHashItem;
    }

    @Override
    public String toString() {
        return "HashItem{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
