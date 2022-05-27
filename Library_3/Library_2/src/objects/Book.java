package objects;

import objects.Author;

public class Book
{
    private String bookId;
    private int publicationYear;
    private String title;
    private String category;

    private int authorId;
    private int count = 1;
    public Book(String title, int year, String category, int authorId, String bookid)
    {
        this.title = title;
        this.publicationYear = year;
        this.category = category;
        this.authorId = authorId;
        this.bookId = bookid;
    }
    public String getBookId()
    {
        return this.bookId;
    }
    public String getTitle()
    {
        return this.title;
    }
    public void setTitle(String title){this.title = title;}

    public int getYear()
    {
        return this.publicationYear;
    }
    public void setYear(int year){this.publicationYear = year;}
    public int getId()
    {
        return this.authorId;
    }

    public String getCategory() {return this.category;}
    public void setCategory(String category){this.category = category;}
    public int getCount()
    {
        return this.count;
    }
    public void setCount(int count){this.count = count;}
    public void addCopy()
    {
        this.count += 1;
    }
    public void removecopy()
    {
        this.count -= 1;
    }
    public int getAuthor() {return this.authorId;}

    public void changeCount(int n) {this.count = n;}
    @Override
    public String toString()
    {
        return "Id: " + this.bookId + " Numar exemplare: " + this.count +" Titlu: "+ this.title + " Anul publicarii: " + this.publicationYear + " Categorie: "+ this.category + " Autor: " + this.authorId;
    }
}