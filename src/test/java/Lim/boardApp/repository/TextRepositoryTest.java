package Lim.boardApp.repository;

import Lim.boardApp.domain.Customer;
import Lim.boardApp.domain.Text;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.util.DriverDataSource;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/* TEST DATA

TID  	CONTENT  	CREATED_DATE  	    TITLE
1	    content1	                	title1
2	    content2	                	title2
*/

@SpringBootTest
@Transactional
class TextRepositoryTest {

    @Autowired
    TextRepository textRepository;
    @Autowired
    CustomerRepository customerRepository;

    private JdbcTemplate template;
    private DataSource dataSource;

    @BeforeEach
    public void init() {
        //jdbc 연결
        dataSource = new DriverManagerDataSource();
        template = new JdbcTemplate(dataSource);

        //테스트 데이터
        Customer customer = Customer.builder()
                .cname("testcname")
                .age(12)
                .password("testpw")
                .loginId("testloginid")
                .build();

        Text text1 = Text.builder()
                .title("title1")
                .content("content1")
                .build();
        Text text2 = Text.builder()
                .title("title2")
                .content("content2")
                .build();

        textRepository.save(text1);
        textRepository.save(text2);
    }

    @AfterEach
    public void deleteAll() {
        textRepository.deleteAll();
    }

    @DisplayName("findByTitle() 테스트")
    @Test
    public void findByTitleTest() {
        List<Text> textList = textRepository.findByTitle("title1");
        Text text = textList.get(0);

        assertEquals(text.getTitle(), "title1");
        assertEquals(text.getContent(), "content1");
    }

    private RowMapper<Text> textRowMapper() {
        return (rs, rowNum) -> {
            Text text = new Text();
            text.setTitle(rs.getString("title"));
            text.setContent(rs.getString("content"));
            text.setTid(rs.getLong("tid"));
            text.setCreatedDate(rs.getDate("created_date"));
            return text;
        };
    }

}