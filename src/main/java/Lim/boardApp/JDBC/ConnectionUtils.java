package Lim.boardApp.JDBC;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.CollectionOneToOneMatcher;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
public class ConnectionUtils {
    public DataSource getDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setPassword(ConnectionConst.PASSWORD);
        ds.setUsername(ConnectionConst.USERNAME);
        ds.setJdbcUrl(ConnectionConst.URL);
        return ds;
    }

    public Connection getConnection(DataSource dataSource) throws SQLException{
        Connection con = DataSourceUtils.getConnection(dataSource);
        return con;
    }

    public void close(Connection con, Statement statement, ResultSet resultSet, DataSource dataSource) {
        JdbcUtils.closeResultSet(resultSet);
        JdbcUtils.closeStatement(statement);
        DataSourceUtils.releaseConnection(con, dataSource);
    }


}
