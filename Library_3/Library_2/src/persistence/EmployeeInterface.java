package persistence;

public interface EmployeeInterface<T>
{
    public void create(T ob);

    public void read(int id);

    public void readAll();

//    public void update(T entity);

    public void delete(int id);

//    public int getSize();

}
