package pl.ormlite.example.Dao;

import com.j256.ormlite.support.ConnectionSource;

public class AuthorDao extends UniversalDao {
    public AuthorDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }
}
