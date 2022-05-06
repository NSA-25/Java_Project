package persistence;

public interface GenericInterface<T>
{

    public void create(T ob);

    public void read(String id);

    public void readAll();

//    public void update(T entity);

    public void delete(String id);

//    public int getSize();

}
