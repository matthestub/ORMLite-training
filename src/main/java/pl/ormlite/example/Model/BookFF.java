package pl.ormlite.example.Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import pl.ormlite.example.Model.Author;

import java.util.Date;

@DatabaseTable(tableName = "books")
public class BookFF {

    public BookFF() {
    }

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "TITLE", canBeNull = false )
    private String title;
    @DatabaseField(foreign = true, columnName = "AUTHOR_ID", foreignAutoCreate = true, foreignAutoRefresh = true) //automatyczne dodanie do tabelii oraz odświeżenie tabelii
    private Author author; //defaultowa nazwa będzie": author_id
    @DatabaseField(columnName = "DESCRIPTION", dataType = DataType.LONG_STRING) //zmapowanie na inny typ niż domyślny - tutaj domyślny to VARCHAR
    private String description;
    @DatabaseField(columnName = "ISBN", unique = true)
    private String isbn;
    @DatabaseField(columnName = "ADDED_DATE")
    private Date addedDate;
    @DatabaseField(columnName = "DATE_RELEASE", dataType = DataType.DATE_STRING, format = "yyyy/MM/dd")
    private Date dateRelease;
    @DatabaseField(columnName = "RATING", width = 1) //ograniczenie do ilości znakó - tutaj tylko jedna ocena od 1 do 5
    private String rating;
    @DatabaseField(columnName = "BORROWED", defaultValue = "false")
    private boolean borrowed;
    @DatabaseField(columnName = "PRICE")
    private double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(Date dateRelease) {
        this.dateRelease = dateRelease;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookFF{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", addedDate=" + addedDate +
                ", dateRelease=" + dateRelease +
                ", rating='" + rating + '\'' +
                ", borrowed=" + borrowed +
                ", price=" + price +
                '}';
    }
}
