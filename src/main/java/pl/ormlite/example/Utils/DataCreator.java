package pl.ormlite.example.Utils;

import org.h2.command.dml.Insert;
import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Model.Book;
import pl.ormlite.example.Model.BookFF;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataCreator {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//    public static final Author nesbo = getNesbo();
//    public static final Author larsson = getLarsson();

    public static Book NesboFirstBook() {
        Book book1 = new Book();
        book1.setTitle("The Bat");
//        book1.setAuthor(nesbo);
        book1.setDescription("First book from series about Harry Hole");
        book1.setIsbn("9727");
        book1.setAddedDate(new Date());

        String book1ReleaseDateString = "11/11/1997";
        Date book1ReleaseDate = null;
        try {
            book1ReleaseDate = SDF.parse(book1ReleaseDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book1.setDateRelease(book1ReleaseDate);

        book1.setRating("5");
        book1.setBorrowed(true);
        book1.setPrice(28.99);

        return book1;
    }

    public static Book NesboSecondBook() {
        Book book2 = new Book();
        book2.setTitle("Cockroaches");
//        book2.setAuthor(nesbo);
        book2.setDescription("Second book from series about Harry Hole");
        book2.setIsbn("9867");
        book2.setAddedDate(new Date());

        String book2ReleaseDateString = "30/9/1998";
        Date book2ReleaseDate = null;
        try {
            book2ReleaseDate = SDF.parse(book2ReleaseDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book2.setDateRelease(book2ReleaseDate);

        book2.setRating("4");
        book2.setBorrowed(true);
        book2.setPrice(31.99);

        return book2;
    }

    public static Book NesboThirdBook() {
        Book book3 = new Book();
        book3.setTitle("The Redbreast");
 //       book3.setAuthor(nesbo);
        book3.setDescription("Third book from series about Harry Hole");
        book3.setIsbn("7841");
        book3.setAddedDate(new Date());

        String book3ReleaseDateString = "5/6/2000";
        Date book3ReleaseDate = null;
        try {
            book3ReleaseDate = SDF.parse(book3ReleaseDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book3.setDateRelease(book3ReleaseDate);

        book3.setRating("5");
        book3.setBorrowed(true);
        book3.setPrice(35.00);

        return book3;
    }

    public static Book LarssonFirstBook() {
        Book book4 = new Book();
        book4.setTitle("The Girl with the dragon tattoo");
 //       book4.setAuthor(larsson);
        book4.setDescription("First part of the Millennium Trilogy");
        book4.setIsbn("9120");
        book4.setAddedDate(new Date());

        String book4ReleaseDateString = "26/2/2005";
        Date book4ReleaseDate = null;
        try {
            book4ReleaseDate = SDF.parse(book4ReleaseDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book4.setDateRelease(book4ReleaseDate);

        book4.setRating("5");
        book4.setBorrowed(false);
        book4.setPrice(52.99);

        return book4;
    }

    public static Book LarssonSecondBook() {
        Book book5 = new Book();
        book5.setTitle("The Girl who played with fire");
//        book5.setAuthor(larsson);
        book5.setDescription("Second part of the Millennium Trilogy");
        book5.setIsbn("7123");
        book5.setAddedDate(new Date());

        String book5ReleaseDateString = "15/3/2006";
        Date book5ReleaseDate = null;
        try {
            book5ReleaseDate = SDF.parse(book5ReleaseDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book5.setDateRelease(book5ReleaseDate);

        book5.setRating("5");
        book5.setBorrowed(false);
        book5.setPrice(58.99);

        return book5;
    }


    public static Author getNesbo() {
        Author joNesbo = new Author();
        joNesbo.setAuthorName("Jo");
        joNesbo.setAuthorSurname("Nesbo");
        return joNesbo;
    }

    public static Author getLarsson() {
        Author stiegLarsson = new Author();
        stiegLarsson.setAuthorName("Stieg");
        stiegLarsson.setAuthorSurname("Larsson");
        return stiegLarsson;
    }
}
