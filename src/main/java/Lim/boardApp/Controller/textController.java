package Lim.boardApp.Controller;

import Lim.boardApp.domain.Text;
import Lim.boardApp.repository.TextRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@Slf4j
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

    @PostMapping("/{tid}")
    public String deleteText(@PathVariable Long tid){
        textRepository.deleteById(tid);
        return "redirect:/board";
    }


    //글을 추가 및 수정하는 메서드
    //TODO: update전용 객체를 만들어서 받고 넘기기.(제목하고 내용만 바꾸면됨.)
    @GetMapping("/new")
    public String goToMakeText(@RequestParam(value = "tid",required = false,defaultValue = "") Long tid ,Model model) {
        Text text = new Text();
        text.setTid(tid);
        model.addAttribute("text", text);
        return "board/maketext";
    }

    @PostMapping("/new")
    public String makeText(@ModelAttribute Text text) {
        textRepository.save(text);
        return "redirect:/board";
    }
}
