package Lim.boardApp;

import Lim.boardApp.JDBC.ConnectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static jdk.dynalink.linker.support.Guards.isNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
public class JDBCTest {

    private ConnectionUtils cu;
    private DataSource dataSource;
    private Connection con;

    @BeforeEach
    public void create()
    {
        cu = new ConnectionUtils();
    }

    @Test
    public void DataSource생성여부(){

        dataSource = cu.getDataSource();

        assertThat(dataSource).isNotNull();
    }

    @Test
    public void Connection생성여부() throws SQLException {
        dataSource = cu.getDataSource();

        con = cu.getConnection(dataSource);

        assertThat(con).isNotNull();
    }
}
