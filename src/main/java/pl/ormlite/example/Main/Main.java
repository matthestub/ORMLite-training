package pl.ormlite.example.Main;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import pl.ormlite.example.Dao.AuthorDao;
import pl.ormlite.example.Dao.BookDao;
import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Model.Book;
import pl.ormlite.example.Utils.DataCreator;
import pl.ormlite.example.Utils.DbManager;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        DbManager.initDatabase();
        Author author1 = DataCreator.getNesbo();
        Author author2 = DataCreator.getLarsson();
        AuthorDao authorDao = new AuthorDao(DbManager.getConnectionSource());
        authorDao.universalCreateOrUpdate(author1);
        authorDao.universalCreateOrUpdate(author2);

        Book book1 = DataCreator.NesboFirstBook();
        book1.setAuthor(author1);
        Book book2 = DataCreator.NesboSecondBook();
        book2.setAuthor(author1);
        Book book3 = DataCreator.NesboThirdBook();
        book3.setAuthor(author1);

        Book book4 = DataCreator.LarssonFirstBook();
        book4.setAuthor(author2);
        Book book5 = DataCreator.LarssonSecondBook();
        book5.setAuthor(author2);

        BookDao bookDao = new BookDao(DbManager.getConnectionSource());
        bookDao.universalCreateOrUpdate(book1);
        bookDao.universalCreateOrUpdate(book2);
        bookDao.universalCreateOrUpdate(book3);
        bookDao.universalCreateOrUpdate(book4);
        bookDao.universalCreateOrUpdate(book5);

        bookDao.universalRefresh(book1);
        bookDao.universalRefresh(book2);
        bookDao.universalRefresh(book3);
        bookDao.universalRefresh(book4);
        bookDao.universalRefresh(book5);


        bookDao.queryForBooksQueryBuilder("TITLE", "The Bat");

        DbManager.closeConnectionSource();
    }
}
