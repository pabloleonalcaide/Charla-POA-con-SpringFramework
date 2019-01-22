package persistence.entities.library;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;

    private String isbn;

    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Theme> themeList;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Author> authorList;

    public Book() {
    }

    public Book(String isbn, String title, List<Theme> themeList, List<Author> authorList) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.themeList = themeList;
        this.authorList = authorList;
    }

    @Override
    public String toString() {
        return "\nBook [id=" + id + ", isbn=" + isbn + ", title=" + title + ",\n    themeList=" + themeList + ",\n    authorList=" + authorList + "]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            return id == ((Book) obj).id;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public List<Theme> getThemeList() {
        return themeList;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

}
