package persistence.entities.library;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String surname;

    @Embedded
    private Contact contact;

    @ManyToOne
    @JoinColumn
    private Style style;

    public Author() {
    }

    public Author(String name, String surname, Contact contact, Style style) {
        this.name = name;
        this.surname = surname;
        this.contact = contact;
        this.style = style;
    }

    @Override
    public String toString() {
        return "Author [id=" + id + ", name=" + name + ", surname=" + surname + ", contact=" + contact + ", style=" + style + "]";
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
            return id == ((Author) obj).id;
        }
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}