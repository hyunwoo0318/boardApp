package Lim.boardApp.JDBC;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class QuerySQL {
    public ResultSet executeQuery (String sql) throws SQLException {
        ConnectionUtils cu = new ConnectionUtils();
        DataSource ds = cu.getDataSource();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs=null;
        ResultSet rt=null;
        try{
            con = cu.getConnection(ds);
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            rt = rs;
            return rt;
        }catch(SQLException e){
            log.info("DB error = {}", e);
            throw e;
        }finally{
            cu.close(con,pstm,rs,ds);
        }
    }
}
