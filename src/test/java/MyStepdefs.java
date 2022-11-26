import com.example.step_definitions.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Test;

import java.sql.SQLException;

public class MyStepdefs extends TestBase {

    @Test
    public void test() throws SQLException {
        resultSet = statement.executeQuery("select name from book_categories");
    }

}
