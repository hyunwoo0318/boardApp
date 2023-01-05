package Lim.boardApp.repository;

import Lim.boardApp.domain.Text;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.util.DriverDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/* TEST DATA

TID  	CONTENT  	CREATED_DATE  	    TITLE
1	    content1	2022-01-21 00:00:00	title1
2	    content2	2022-03-24 00:00:00	title2
*/

@SpringBootTest
@Transactional
class TextRepositoryTest {

    @Autowired
    TextRepository textRepository;

    private JdbcTemplate template;
    private DataSource dataSource;
    @BeforeEach
    public void connectSet(){
        dataSource = new DriverManagerDataSource();
        template = new JdbcTemplate(dataSource);
    }

    @DisplayName("기존데이터에대한 select 기능 확인")
    @Test
    public void 기존데이터_SELECT(){
        Calendar cal = Calendar.getInstance();
        cal.set(2022, 01, 21, 00, 00, 00);

        Text outputText = textRepository.getReferenceById(1L);

        assertEquals(outputText.getTid(), 1L);
        assertEquals(outputText.getContent(), "content1");
        assertEquals(outputText.getTitle(), "title1");
        //TODO : 시간 캐스팅해서 맞추기 (2022-01-21 00:00:00.0)으로 맞춰야함.
        //assertEquals(outputText.getCreatedDate(), cal.getTime());
    }

    @DisplayName("기존데이터에 대한 UPDATE 기능 확인")
    @Test

    public void 기존데이터_UPDATE(){
        //제목 변경
        String changeTitle = "change title";
        Text changeText = textRepository.getReferenceById(2L);
        changeText.setTitle(changeTitle);
        //디비에 직접 쿼리 날리기
        String sql = "select * from text where tid = 1;";

        Text outputText = template.queryForObject(sql, textRowMapper());

        assertEquals(outputText.getTitle(), changeTitle);
    }

    private RowMapper<Text> textRowMapper() {
        return (rs, rowNum) ->{
            Text text = new Text();
            text.setTitle(rs.getString("title"));
            text.setContent(rs.getString("content"));
            text.setTid(rs.getLong("tid"));
            text.setCreatedDate(rs.getDate("created_date"));
            return text;
        };
    }
/*
    @DisplayName("기존데이터에 대한 DELETE 기능 확인")
    @Test
    public void 기존데이터_DELETE(){

    }

    @DisplayName("save()함수를 이용해서 데이터삽입 확인")
    @Test
    public void 데이터삽입(){
        //확인은 JDBC를 이용해 직접 쿼리를 날려서 확인.
    }
*/
    private Text makeTestText(){
        Calendar cal = Calendar.getInstance();
        cal.set(2022, 02, 12);

        Text inputText = new Text();
        inputText.setContent("content1");
        inputText.setCreatedDate(cal.getTime());
        inputText.setTitle("title1");
        return inputText;
    }


}