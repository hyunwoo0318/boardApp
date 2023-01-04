package Lim.boardApp.repository;

import Lim.boardApp.domain.Text;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/* TEST DATA

TID  	CONTENT  	CREATED_DATE  	    TITLE
1	    content1	2022-01-21 00:00:00	title1
2	    content2	2022-03-24 00:00:00	title2
*/

@SpringBootTest
class TextRepositoryTest {

    @Autowired
    TextRepository textRepository;

    @DisplayName("기존데이터에대한 select 기능 확인")
    @Test
    @Transactional
    public void 기존데이터_SELECT(){
        Calendar cal = Calendar.getInstance();
        cal.set(2022, 01, 21, 00, 00, 00);

        Text outputText = textRepository.getReferenceById(1L);

        assertEquals(outputText.getTid(), 1L);
        assertEquals(outputText.getContent(), "content1");
        assertEquals(outputText.getTitle(), "title1");
        //TODO : 시간 캐스팅해서 맞추기 (2022-01-21 00:00:00.0)으로 맞춰야함.
        assertEquals(outputText.getCreatedDate(), cal.getTime());
    }

    @DisplayName("기존데이터에 대한 UPDATE 기능 확인")
    @Test
    public void 기존데이터_UPDATE(){

    }

    @DisplayName("기존데이터에 대한 DELETE 기능 확인")
    @Test
    public void 기존데이터_DELETE(){

    }

    @DisplayName("save()함수를 이용해서 데이터삽입 확인")
    @Test
    public void 데이터삽입(){
        //확인은 JDBC를 이용해 직접 쿼리를 날려서 확인.
    }





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