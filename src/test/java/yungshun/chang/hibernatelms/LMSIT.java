package yungshun.chang.hibernatelms;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.fail;

public class LMSIT {

    @Test
    public void givenMySQLIsRunningThenNoException() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String jdbcUrl = "jdbc:mysql://localhost:3306/hibernate_lms";
        String user = "yungshun";
        String password = "lucifer317";

        // Run `mvn clean compiler:testCompile failsafe:integration-test`
        Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
        assert(true);
    }
}
