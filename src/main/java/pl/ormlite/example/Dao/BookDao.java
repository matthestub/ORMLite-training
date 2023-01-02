package pl.ormlite.example.Dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import pl.ormlite.example.Model.BaseModel;
import pl.ormlite.example.Model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookDao extends UniversalDao {
    public BookDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public List<String[]> queryRawForTitle(String title) throws SQLException {
        GenericRawResults<String[]> results = getDao(Book.class).queryRaw("SELECT * FROM books WHERE" +
                "title = "+title);
        return results.getResults();
    }

    public void queryForBooksQueryBuilder(String columnName, String value) throws SQLException {
        QueryBuilder<Book, Integer> queryBuilder = getQueryBuilder(Book.class);
        queryBuilder.where().eq(columnName, value);
        PreparedQuery<Book> preparedQuery = queryBuilder.prepare();
        List<Book> booksList = getDao(Book.class).query(preparedQuery);
        booksList.forEach(book -> {
            System.out.println("\n"+book.toString()+"\n");
        });
    }

}
