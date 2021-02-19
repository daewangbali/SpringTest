package kr.co.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.domain.BoardVO;
import kr.co.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	
	final private BoardService boardService;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list..............");
		model.addAttribute("list", boardService.getList());
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes ra) {
		log.info("register..............");
		
		long bno = boardService.registerSelectKey(board);
		ra.addAttribute("result", bno);
		ra.addFlashAttribute("result", bno);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/get")
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("get................");
		model.addAttribute("board",boardService.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes ra){
		log.info("modify..............");
		
		int count = boardService.modify(board);
		
		if(count==0) {
			ra.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/get";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes ra) {
		log.info("remove..............");
		int count = boardService.remove(bno);
		
		if(count==0) {
			ra.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
		
	}
	

}
