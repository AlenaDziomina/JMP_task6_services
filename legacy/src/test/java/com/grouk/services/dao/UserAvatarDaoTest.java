package com.grouk.services.dao;

import com.grouk.services.exception.SqlDaoException;
import com.grouk.services.model.UserAvatar;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

/**
 * Created by Alena on 10.04.2017.
 */
public class UserAvatarDaoTest extends DatabaseTestCase {
    private static final String DB_URL;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        DB_URL = resourceBundle.getString("db.url");
    }

    private Connection jdbcConnection;
    private FlatXmlDataSet loadedDataSet;
    private UserAvatarDao dao = new UserAvatarDao();

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        jdbcConnection = DriverManager.getConnection(DB_URL);
        return new DatabaseConnection(jdbcConnection);
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        loadedDataSet = new FlatXmlDataSet(this.getClass().getClassLoader()
                .getResourceAsStream("dbunitAvatarData.xml"));
        return loadedDataSet;
    }

    @Test
    public void testGetUserAvatar() throws Exception {
        UserAvatar userAvatar = dao.getUserAvatar(1L);
        assertNotNull(userAvatar);
        assertNotNull(userAvatar.getAvatar());
    }

    @Test
    public void testCreateUserAvatar() throws Exception {
        UserAvatar userAvatar = new UserAvatar();
        userAvatar.setAvatar(new byte[]{});
        Long id = dao.createUserAvatar(userAvatar);

        UserAvatar created = dao.getUserAvatar(id);
        assertNotNull(created);
        assertNotNull(created.getAvatar());
    }

    @Test
    public void testUpdateUserAvatar() throws Exception {
        UserAvatar userAvatar = new UserAvatar();
        userAvatar.setId(2L);
        userAvatar.setAvatar(new byte[]{});

        dao.updateUserAvatar(userAvatar);
        UserAvatar updated = dao.getUserAvatar(2L);
        assertNotNull(updated);
        assertNotNull(updated.getAvatar());
    }

    @Test
    public void testDeleteUserAvatar() throws Exception {
        dao.deleteUserAvatar(2L);
        try {
            dao.getUserAvatar(2L);
        } catch (SqlDaoException e) {
            assertEquals("There are NO such entity in database.", e.getMessage());
        }
    }

}