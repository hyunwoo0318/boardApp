package Lim.boardApp.Controller;

import Lim.boardApp.domain.Text;
import Lim.boardApp.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class textController {

    @Autowired
    TextRepository textRepository;

    //글 리스트 전체를 보여주는 페이지
    @GetMapping
    public String showTextList(Model model){
        List<Text> textList = textRepository.findAll();
        model.addAttribute("textList", textList);
        return "board/textlist";
    }

    //선택한 글의 정보를 보여줌
    @GetMapping("/{tid}")
    public String showText(@PathVariable("tid") Long tid, Model model) {
        Text text = textRepository.getReferenceById(tid);
        model.addAttribute(text);
        return "board/showtext";
    }

    //글을 추가하는 메서드
    @GetMapping("/new")
    public String goToMakeNewText(Model model) {
        Text text = new Text();
        model.addAttribute("text", text);
        return "board/makeText";
    }


    @PostMapping("/new")
    public String makeNewText(@ModelAttribute Text text) {
        textRepository.save(text);
        return "/board/textlist";
    }


    //TODO: 글 수정 컨트롤러 구현 -> new와 같은데 ? 파라미터로 받아서 사용 tid만 구성하면됨.
}
