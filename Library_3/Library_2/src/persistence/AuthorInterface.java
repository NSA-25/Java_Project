package persistence;

import objects.Book;

import java.util.Map;

public interface AuthorInterface<T>
{
    public void create(T ob);

    public void read(int id);

    public void readAll();

//    public void update(T entity);

    public void delete(int id, Map<String, Book> books);

//    public int getSize();

}
