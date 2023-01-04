package Lim.boardApp;

import Lim.boardApp.domain.Text;
import Lim.boardApp.repository.TextRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class connectTest {

    @Autowired
    private TextRepository textRepository;

    @DisplayName("DB에 잘 저장이 되고 잘 꺼내지는지 확인하는 테스트")
    @Test
    public void firstTest(){
        Text text = new Text();
        text = makeText();
        textRepository.save(text);
        Text ret = textRepository.getReferenceById(text.getTid());
        assertEquals(text.getTid(), ret.getTid());
    }

    public Text makeText(){
        Text text = new Text();
        text.setTitle("title for test text");
        text.setTid(123L);
        text.setContent("this is content for test text");
        text.setCreatedDate(new Date());
        return text;
    }

}
