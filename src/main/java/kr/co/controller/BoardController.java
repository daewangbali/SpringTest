package kr.co.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import kr.co.domain.BoardVO;
import kr.co.domain.Criteria;
import kr.co.domain.PageDTO;
import kr.co.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Log4j2
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	/*
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list..............");
		model.addAttribute("list", boardService.getList());
	}
	*/
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list..............");
		model.addAttribute("list", boardService.getList(cri));
		model.addAttribute("page", new PageDTO(cri,boardService.getTotalCount(cri))); //추가
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes ra) {
		log.info("register.............");
		log.info("register : "+board.getTitle()+"..............");
		
		//boardService.register(baord);
		long bno = boardService.registerSelectKey(board);
		ra.addAttribute("result", bno);  //데이터가 전달이 되면 리셋, 데이터기록 안남음
		ra.addFlashAttribute("result", bno); //데이터고정으로 넘어감 
		
		return "redirect:/board/get?bno="+bno;
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("register get................");
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("get................");
		model.addAttribute("board",boardService.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes ra) { 
		log.info("modify..............");
		
		log.info("modify : " + board.getBno());
		
		int count = boardService.modify(board);
		
		if(count==1) {
			ra.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/get?bno="+board.getBno();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno ,RedirectAttributes ra) {
		log.info("remove..............");
		
		int count = boardService.remove(bno);
		
		if(count==1) {
			ra.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
		
	}
	

}
