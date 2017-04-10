package com.grouk.services.dao;

import com.grouk.services.model.UserProfile;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alena on 10.04.2017.
 */
public class UserProfileDaoTest extends DatabaseTestCase {
    private static final String DB_URL;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        DB_URL = resourceBundle.getString("db.url");
    }

    private Connection jdbcConnection;
    private FlatXmlDataSet loadedDataSet;
    private UserProfileDao dao = new UserProfileDao();

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        jdbcConnection = DriverManager.getConnection(DB_URL);
        return new DatabaseConnection(jdbcConnection);
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        loadedDataSet = new FlatXmlDataSet(this.getClass().getClassLoader()
                .getResourceAsStream("dbunitUserData.xml"));
        return loadedDataSet;
    }

    @Test
    public void testGetUserList() throws Exception {
        List<UserProfile> userProfileList = dao.getUserList();
        assertEquals(2, userProfileList.size());

        UserProfile userProfile = userProfileList.get(0);
        assertEquals(1, userProfile.getId().longValue());
        assertEquals("first_name1", userProfile.getFirstName());
        assertEquals("last_name1", userProfile.getLastName());
        assertEquals(1, userProfile.getAvatarId().longValue());

        userProfile = userProfileList.get(1);
        assertEquals(2, userProfile.getId().longValue());
        assertEquals("first_name2", userProfile.getFirstName());
        assertEquals("last_name2", userProfile.getLastName());
        assertEquals(2, userProfile.getAvatarId().longValue());
    }

    @Test
    public void testGetUserProfile() throws Exception {
        UserProfile userProfile = dao.getUserProfile(1L);
        assertNotNull(userProfile);
        assertEquals(1, userProfile.getId().longValue());
        assertEquals("first_name1", userProfile.getFirstName());
        assertEquals("last_name1", userProfile.getLastName());
        assertEquals(1, userProfile.getAvatarId().longValue());
    }

    @Test
    public void testCreateUserProfile() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("first_name3");
        userProfile.setLastName("last_name3");
        userProfile.setAvatarId(1L);

        Long id = dao.createUserProfile(userProfile);
        assertNotNull(id);

        UserProfile created = dao.getUserProfile(id);
        assertNotNull(created);
        assertEquals(id, created.getId());
        assertEquals(userProfile.getFirstName(), created.getFirstName());
        assertEquals(userProfile.getLastName(), created.getLastName());
        assertEquals(userProfile.getAvatarId(), created.getAvatarId());
    }

    @Test
    public void testCreateUserProfileList() throws Exception {
        List<UserProfile> userProfiles = new ArrayList<>(2);

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("first_name4");
        userProfile.setLastName("last_name4");
        userProfile.setAvatarId(1L);
        userProfiles.add(userProfile);

        userProfile = new UserProfile();
        userProfile.setFirstName("first_name5");
        userProfile.setLastName("last_name5");
        userProfile.setAvatarId(2L);
        userProfiles.add(userProfile);

        dao.createUserProfile(userProfiles);
        List<UserProfile> userProfileList = dao.getUserList();
        assertEquals(4, userProfileList.size());
    }

    @Test
    public void testUpdateUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setFirstName("first_name_updated");
        userProfile.setLastName("last_name_updated");
        userProfile.setAvatarId(2L);

        dao.updateUserProfile(userProfile);
        UserProfile updated = dao.getUserProfile(1L);
        assertNotNull(userProfile);
        assertEquals(userProfile.getId(), updated.getId());
        assertEquals(userProfile.getFirstName(), updated.getFirstName());
        assertEquals(userProfile.getLastName(), updated.getLastName());
        assertEquals(userProfile.getAvatarId(), updated.getAvatarId());
    }

    @Test
    public void testUpdateUserProfileList() {
        List<UserProfile> userProfiles = new ArrayList<>(2);

        UserProfile userProfile1 = new UserProfile();
        userProfile1.setId(1L);
        userProfile1.setFirstName("first_name1_updated");
        userProfile1.setLastName("last_name1_updated");
        userProfile1.setAvatarId(2L);
        userProfiles.add(userProfile1);

        UserProfile userProfile2 = new UserProfile();
        userProfile2.setId(2L);
        userProfile2.setFirstName("first_name2_updated");
        userProfile2.setLastName("last_name2_updated");
        userProfile2.setAvatarId(1L);
        userProfiles.add(userProfile2);

        dao.updateUserProfile(userProfiles);

        List<UserProfile> userProfileList = dao.getUserList();
        assertEquals(2, userProfileList.size());

        UserProfile userProfile = userProfileList.get(0);
        assertEquals(userProfile1.getId(), userProfile.getId());
        assertEquals(userProfile1.getFirstName(), userProfile.getFirstName());
        assertEquals(userProfile1.getLastName(), userProfile.getLastName());
        assertEquals(userProfile1.getAvatarId(), userProfile.getAvatarId());

        userProfile = userProfileList.get(1);
        assertEquals(userProfile2.getId(), userProfile.getId());
        assertEquals(userProfile2.getFirstName(), userProfile.getFirstName());
        assertEquals(userProfile2.getLastName(), userProfile.getLastName());
        assertEquals(userProfile2.getAvatarId(), userProfile.getAvatarId());
    }

    @Test
    public void testDeleteUserProfile() {
        dao.deleteUserProfile(1L);

        List<UserProfile> userProfileList = dao.getUserList();
        assertEquals(1, userProfileList.size());

        UserProfile userProfile = userProfileList.get(0);
        assertEquals(2, userProfile.getId().longValue());
    }

}