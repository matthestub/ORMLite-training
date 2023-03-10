package pl.ormlite.example.Main;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example.Model.Book;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MainTest {

    public static void main(String[] args) throws Exception {

        String sqlURLDb = "jdbc:sqlite:database.db";
        String h2URLDb = "jdbc:h2:./h2db";

        ConnectionSource connectionSource = new JdbcConnectionSource(sqlURLDb);
        //ConnectionSource connectionSource = new JdbcConnectionSource(h2URLDb);


        TableUtils.dropTable(connectionSource, Book.class, true);
        //TableUtils.createTableIfNotExists(connectionSource, Book.class);
        TableUtils.createTable(connectionSource, Book.class);

        Dao<Book, Integer> bookDaoManager = DaoManager.createDao(connectionSource, Book.class);

        Book book1 = new Book();
        book1.setTitle("The Bat");
        //book1.setAuthor("Jo Nesbo");
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
        //book2.setAuthor("Jo Nesbo");
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
        //book3.setAuthor("Jo Nesbo");
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
        book1 = bookDaoManager.queryForId(book1.getId()); // zwroci null je??li w bazie nie b??dzie obiektu o podanym ID
        System.out.println("After query: "+book1);
        */


        /* ******** RAW QUERIES ********
        Kwerendy podawane bezpo??rednio do daoManagera, zapytanie zwraca list?? zawieraj??c?? tablice String??w, w kt??rej ka??dy element tablicy jest
        osobn?? warto??ci?? w kolumnie wyci??gni??t?? z pojedynczego wiersza
        */

        GenericRawResults<String[]> queryRawResult1 = bookDaoManager.queryRaw("SELECT * FROM books");
        List<String[]> results1 = queryRawResult1.getResults();
        results1.forEach(e -> {
            for (String s : e) {
                System.out.println();
                System.out.println(s);
            }
        });

        GenericRawResults<String[]> queryRawResult2 = bookDaoManager.queryRaw("SELECT count(*) FROM books WHERE author = 'Jo Nesbo'");
        List<String[]> results2 = queryRawResult2.getResults();
        results2.forEach(e -> {
            for (String s : e) {
                System.out.println();
                System.out.println(s);
            }
        });

        GenericRawResults<String[]> queryRawResult3 = bookDaoManager.queryRaw("SELECT MIN(price), MAX(price) FROM books WHERE author = 'Jo Nesbo'");
        List<String[]> results3 = queryRawResult3.getResults();
        results3.forEach(e -> {
            for (String s : e) {
                System.out.println();
                System.out.println(s);
            }
        });

        /*
        Przy r????nych typach baz danych zapytania mog?? mie?? inn?? form?? np. w h2 nie zadzia??a borrowed = 1, a w sqlite zadzia??a zar??wno = 1 jak i = true.
        Warto??ci podawane jako ??rednia cena tak??e b??d?? r????ne w zale??no??ci od baz. Trzeba zapoznawa?? si?? z dokumentacja i upewni?? si?? czy np. zaokraglanie
        jest w d???? czy w g??r??.
         */

        GenericRawResults<String[]> queryRawResult4 = bookDaoManager.queryRaw("SELECT count(*) FROM books WHERE borrowed = true");
        List<String[]> results4 = queryRawResult4.getResults();
        results4.forEach(e -> {
            for (String s : e) {
                System.out.println();
                System.out.println(s);
            }
        });

        double avgPriceOfBooks = bookDaoManager.queryRawValue("SELECT AVG(price) from books");
        System.out.println(avgPriceOfBooks);


        QueryBuilder<Book, Integer> booksQueryBuilder = bookDaoManager.queryBuilder();
        booksQueryBuilder.where().eq("TITLE", "The Bat").query();
        PreparedQuery<Book> forTitleQuery = booksQueryBuilder.prepare();
        List<Book> results = bookDaoManager.query(forTitleQuery);
        results.forEach(e -> {
            System.out.println(e);
        });


        List<Book> result2 = bookDaoManager.query(bookDaoManager.queryBuilder().where().eq("AUTHOR", "Jo Nesbo").prepare());
        result2.forEach(book -> {
            System.out.println();
            System.out.println(book.getTitle());
        });

        UpdateBuilder<Book, Integer> updateBuilder = bookDaoManager.updateBuilder();
        updateBuilder.updateColumnValue("DESCRIPTION", "Harry Hole needs to solve murder of a Norwegian women in Australia");
        updateBuilder.where().eq("DESCRIPTION", "First book from series about Harry Hole");

        PreparedUpdate<Book> preparedUpdate = updateBuilder.prepare();
        int rowsAffected = bookDaoManager.update(preparedUpdate);
        System.out.println();
        System.out.println(rowsAffected);

        connectionSource.close();

    }

}
