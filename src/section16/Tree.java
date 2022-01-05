package section16;

public interface Tree<T> {
    public T getRoot();

    public void insert(T data);

    public T find(T data);

    public void traverse();
}
