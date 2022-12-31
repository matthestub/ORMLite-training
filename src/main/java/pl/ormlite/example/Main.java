package pl.ormlite.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.h2.table.Table;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.MonthDay;
import java.util.Date;


public class Main {

    public static void main(String[] args) throws Exception {

        String sqlURLDb = "jdbc:sqlite:database.db";

        ConnectionSource connectionSource = new JdbcConnectionSource(sqlURLDb);

        TableUtils.dropTable(connectionSource, Book.class, true);
        //TableUtils.createTableIfNotExists(connectionSource, Book.class);
        TableUtils.createTable(connectionSource, Book.class);

        Dao<Book, Integer> bookDaoManager = DaoManager.createDao(connectionSource, Book.class);

        Book book1 = new Book();
        book1.setTitle("The Bat");
        book1.setAuthor("Jo Nesbo");
        book1.setDescription("First book from series about Harry Hole");
        book1.setIsbn("1234");
        book1.setAddedDate(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String book1ReleaseDateString = "11/11/1997";
        Date book1ReleaseDate = sdf.parse(book1ReleaseDateString);
        book1.setDateRelease(book1ReleaseDate);

        book1.setRating("5");
        book1.setBorrowed(true);
        book1.setPrice(28.99);

        bookDaoManager.create(book1);
        System.out.println(book1);

        Book book2 = new Book();
        book2.setTitle("Cockroaches");
        book2.setAuthor("Jo Nesbo");
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
        System.out.println(book2);

        Book book3 = new Book();
        book3.setTitle("The Redbreast");
        book3.setAuthor("Jo Nesbo");
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
        book1.setTitle("Hobbit");
        bookDaoManager.update(book1);
        System.out.println("After update: "+book1);
        bookDaoManager.delete(book1);
        book1 = bookDaoManager.queryForId(book1.getId()); // zwroci null jeśli w bazie nie będzie obiektu o podanym ID
        System.out.println("After query: "+book1);
        */


        // ******** RAW QUERIES ********

        GenericRawResults<String[]> queryRawResult1 = bookDaoManager.queryRaw("SELECT * FROM books");

        connectionSource.close();

    }

}
