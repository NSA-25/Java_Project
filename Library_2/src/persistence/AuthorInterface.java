package persistence;

public interface AuthorInterface<T>
{
    public void create(T ob);

    public void read(String first, String last);

    public void readAll();

//    public void update(T entity);

    public void delete(String first, String last);

//    public int getSize();

}
