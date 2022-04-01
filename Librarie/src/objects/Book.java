package objects;

import objects.Author;

public class Book
{
    private String title;
    private int publicationYear;
    private String category;
    private Author author;
    private String bookId;
    private int count = 1;
    public Book(String title, int year, String category, Author author, String bookid)
    {
        this.title = title;
        this.publicationYear = year;
        this.category = category;
        this.author = author;
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
    public int getYear()
    {
        return this.publicationYear;
    }
    public int getCount()
    {
        return this.count;
    }
    public void addCopy()
    {
        this.count += 1;
    }
    public void removecopy()
    {
        this.count -= 1;
    }
    @Override
    public String toString()
    {
        return "Id: " + this.bookId + " Numar exemplare: " + this.count +" Titlu: "+ this.title + " Anul publicarii: " + this.publicationYear + " Categorie: "+ this.category + " Autor: " + this.author.firstName + " " + this.author.lastName;
    }
}