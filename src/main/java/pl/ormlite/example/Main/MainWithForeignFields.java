package pl.ormlite.example.Main;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example.Model.BookFF;
import pl.ormlite.example.Model.Author;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainWithForeignFields {

    public static void main(String[] args) throws Exception {

        String sqlURLDb2 = "jdbc:sqlite:database2.db";
        String h2URLDb = "jdbc:h2:./h2db";

        ConnectionSource connectionSource = new JdbcConnectionSource(sqlURLDb2);
        Dao<BookFF, Integer> bookDaoManager = DaoManager.createDao(connectionSource, BookFF.class);
        Dao<Author, Integer> authorDaoManager = DaoManager.createDao(connectionSource, Author.class);

        TableUtils.dropTable(connectionSource, BookFF.class, true);
        TableUtils.dropTable(connectionSource, Author.class, true);
        TableUtils.createTable(connectionSource, BookFF.class);
        TableUtils.createTable(connectionSource, Author.class);

        Author joNesbo = new Author();
        joNesbo.setAuthorName("Jo");
        joNesbo.setAuthorSurname("Nesbo");

       // authorDaoManager.create(joNesbo); nie muszę manualnie dodawać do tabelii Authors gdy mam ustawione foreignAutoCreate = true

        BookFF book1 = new BookFF();
        book1.setTitle("The Bat");
        book1.setAuthor(joNesbo);
        book1.setDescription("First book from series about Harry Hole");
        book1.setIsbn("1234");
        book1.setAddedDate(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String book1ReleaseDateString = "11/11/1997";
        Date book1ReleaseDate = sdf.parse(book1ReleaseDateString);
        book1.setDateRelease(book1ReleaseDate);

        book1.setRating("5");
        book1.setBorrowed(true);
        book1.setPrice(28.99);

        bookDaoManager.create(book1);
        //authorDaoManager.refresh(joNesbo); //nie muszę manulanie odświeżać bo mam ustawione foreignAutoRefresh = true
        //AKTUALIZUJE TYLKO DANE W TABELI - NIE DODAJE NOWYCH ELEMENTÓW DO KOLEKCJI FOREIGNCOLLECTION !!!
        System.out.println(book1);

        BookFF book2 = new BookFF();
        book2.setTitle("Cockroaches");
        book2.setAuthor(joNesbo);
        book2.setDescription("Second book from series about Harry Hole");
        book2.setIsbn("3467");
        book2.setAddedDate(new Date());

        String book2ReleaseDateString = "30/9/1998";
        Date book2ReleaseDate = sdf.parse(book2ReleaseDateString);
        book2.setDateRelease(book2ReleaseDate);

        book2.setRating("4");
        book2.setBorrowed(true);
        book2.setPrice(31.99);

        bookDaoManager.create(book2);
        //authorDaoManager.refresh(joNesbo);
        System.out.println(book2);

        BookFF book3 = new BookFF();
        book3.setTitle("The Redbreast");
        book3.setAuthor(joNesbo);
        book3.setDescription("Third book from series about Harry Hole");
        book3.setIsbn("7841");
        book3.setAddedDate(new Date());

        String book3ReleaseDateString = "5/6/2000";
        Date book3ReleaseDate = sdf.parse(book3ReleaseDateString);
        book3.setDateRelease(book3ReleaseDate);

        book3.setRating("5");
        book3.setBorrowed(true);
        book3.setPrice(35.00);

        bookDaoManager.create(book3);
        System.out.println(book3);

        /*
        Tutaj muszę manulnie wykonać refresh bo taka funckja odświeży stan danego autora a co najwazniejsze w tym wypadku to uaktualnie jego pole
        ForeignCollection pobierając przypisane mu książki z tabeli. Jeśli nie odświeżę to poniższa kolekcja będzie null i zostanie zgłoszony wyjątek
         */

        authorDaoManager.refresh(joNesbo);
        System.out.println("\n*****************");
        ForeignCollection<BookFF> nesboBooks = joNesbo.getBooksCollection();
        nesboBooks.forEach(book -> {
            System.out.println(book.getTitle());
        });
        System.out.println("*****************\n");

        Author stiegLarsson = new Author();
        stiegLarsson.setAuthorName("Stieg");
        stiegLarsson.setAuthorSurname("Larsson");
        //nie dodaję do tabeli authors - doda się automatycznie bo ustawiłem foreignAutoCreate

        BookFF book4 = new BookFF();
        book4.setTitle("The Girl with the dragon tattoo");
        book4.setAuthor(stiegLarsson);
        book4.setDescription("First part of the Millennium Trilogy");
        book4.setIsbn("9120");
        book4.setAddedDate(new Date());

        String book4ReleaseDateString = "26/2/2005";
        Date book4ReleaseDate = sdf.parse(book4ReleaseDateString);
        book4.setDateRelease(book4ReleaseDate);

        book4.setRating("5");
        book4.setBorrowed(false);
        book4.setPrice(52.99);

        bookDaoManager.create(book4);
        //authorDaoManager.refresh(stiegLarsson);
        System.out.println(book4);

        BookFF book5 = new BookFF();
        book5.setTitle("The Girl who played with fire");
        book5.setAuthor(stiegLarsson);
        book5.setDescription("Second part of the Millennium Trilogy");
        book5.setIsbn("7123");
        book5.setAddedDate(new Date());

        String book5ReleaseDateString = "15/3/2006";
        Date book5ReleaseDate = sdf.parse(book5ReleaseDateString);
        book5.setDateRelease(book5ReleaseDate);

        book5.setRating("5");
        book5.setBorrowed(false);
        book5.setPrice(58.99);

        bookDaoManager.create(book5);
        //authorDaoManager.refresh(stiegLarsson);
        System.out.println(book5);

        authorDaoManager.refresh(stiegLarsson);
        System.out.println("\n*****************");
        ForeignCollection<BookFF> larssonsBooks = stiegLarsson.getBooksCollection();
        larssonsBooks.forEach(book -> {
            System.out.println(book.getTitle());
        });
        System.out.println("*****************\n");





        connectionSource.close();

    }
}
