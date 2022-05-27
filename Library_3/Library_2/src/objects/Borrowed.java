package objects;

import java.time.LocalDate;

public class Borrowed
{
    private int clientId;
    private Book borrowedBook;
    private LocalDate borrowDate;
    private LocalDate returnBeforeDate;
    public Borrowed(int clientId, Book book, LocalDate borrowDate, LocalDate returnBeforeDate)
    {
        this.clientId = clientId;
        this.borrowedBook = book;
        this.borrowDate = borrowDate;
        this.returnBeforeDate = returnBeforeDate;
    }
    public int getBorrower()
    {
        return this.clientId;
    }
    public String getBook()
    {
        return this.borrowedBook.getTitle();
    }
    public String getBookId()
    {
        return this.borrowedBook.getBookId();
    }
    @Override
    public String toString()
    {
        return "Id-ul clientului " + clientId + " Id-ul cartii " + borrowedBook.getBookId() + " Data imprumut " + borrowDate + " Data returnare " + returnBeforeDate;
    }
}
