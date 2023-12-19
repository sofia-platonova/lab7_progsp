import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Program
{
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        try(InputStream input = new FileInputStream("src/sql.properties")){
            properties.load(input);
        } catch(IOException exeption){
            throw new RuntimeException(exeption);
        }
        Connection connection = DriverManager.getConnection(properties.getProperty("database.url"), properties.getProperty("database.login"), properties.getProperty("database.pass"));
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM patient");
        new PatientTableModel(resultSet, statement);
    }
}
