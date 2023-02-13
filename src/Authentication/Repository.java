package Authentication;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class Repository {
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";


    public Set<Users> findUser() {
        Set<Users> users = new LinkedHashSet<>();
        try (Connection connection = DriverManager.getConnection(PropertiesUtil.get(URL_KEY),
                PropertiesUtil.get(USER_KEY),
                PropertiesUtil.get(PASSWORD_KEY));
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String password = resultSet.getString(3);
                Users user = new Users(id, name, password);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}