package kr.co.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.domain.BoardVO;
import kr.co.domain.CommentVO;
import kr.co.domain.Criteria;
import kr.co.domain.PageDTO;
import kr.co.service.BoardService;
import kr.co.service.Common;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@Autowired
	private Common common;
	/*
	 * @GetMapping("/list") public void list(Model model) {
	 * log.info("list.............."); model.addAttribute("list",
	 * boardService.getList()); }
	 */

	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list..............");
		model.addAttribute("list", boardService.getList(cri));
		model.addAttribute("page", new PageDTO(cri, boardService.getTotalCount(cri))); // 추가
	}

	@PostMapping(value = "/register", consumes = "multipart/form-data")
	public String register(BoardVO board, RedirectAttributes ra, @RequestParam ("file") MultipartFile file,
			 HttpSession session) {
		log.info("register.............");
		log.info("register : " + file.getOriginalFilename() + "..............");
		if (file.getSize() > 0) { // 파일을 가지고 있다면
			board.setFilename(file.getOriginalFilename());
			board.setFilepath(common.upload("board", file, session));
		}

		// boardService.register(baord);
		long bno = boardService.registerSelectKey(board);
		// ra.addAttribute("result", bno); //데이터가 전달이 되면 리셋, 데이터기록 안남음

		ra.addFlashAttribute("result", bno); // 데이터고정으로 넘어감

		return "redirect:/board/get?bno=" + bno;
	}

	@GetMapping("/register")
	public void register() {
		log.info("register get................");
	}

	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("get................");
		model.addAttribute("board", boardService.get(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes ra,@ModelAttribute("file") MultipartFile file, HttpSession session,@RequestParam("attach") String attach) {
		log.info("modify..............");

		log.info("modify : " + board.getBno());

		//0309 추가	
		//	String uuid = session.getServletContext().getRealPath("resources") + board.getFilepath();
			String uuid = "D:\\jjjung\\JavaEE\\Testgj\\src\\main\\webapp\\resources" + board.getFilepath(); //절대경로 쓸 때 사용

			if(file.getSize() > 0) {
				board.setFilename(file.getOriginalFilename());
				board.setFilepath(common.upload("board", file, session));
				
				File f = new File(uuid); //기존파일 -> 지워줘야함
				if(f.exists()) {
					f.delete();
				}
			}
			if(attach.equals("y")) {
				File f = new File(uuid); //기존파일 -> 지워줘야함
				if(f.exists()) {
					f.delete();
				}
			}
		
		int count = boardService.modify(board);

		
		if (count == 1) {
			ra.addFlashAttribute("result", "success");
		}

		ra.addAttribute("pageNum", cri.getPageNum());
		ra.addAttribute("amount", cri.getAmount());
		ra.addAttribute("type", cri.getType());
		ra.addAttribute("keyword", cri.getKeyword());
	
		
		return "redirect:/board/get?bno=" + board.getBno();
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes ra) {
		log.info("remove..............");
		
		File file = new File("D:\\jjjung\\JavaEE\\Testgj\\src\\main\\webapp\\resources" + boardService.get(bno).getFilepath()); //파일경로
		
		if(file.exists()) {
			file.delete();
		}
		

		int count = boardService.remove(bno);

		if (count == 1) {
			ra.addFlashAttribute("result", "success");
		}

		ra.addAttribute("pageNum", cri.getPageNum());
		ra.addAttribute("amount", cri.getAmount());
		ra.addAttribute("type", cri.getType());
		ra.addAttribute("keyword", cri.getKeyword());

		return "redirect:/board/list";

	}

	// 20210303 아래 추가
	@GetMapping("/comment/comment_get_list")
	public void comment_get_list(@RequestParam("bno") Long bno, Model model) {
		log.info("comment_get_list.............");
		model.addAttribute("comment", boardService.comment_get_list(bno));

	}

	@ResponseBody
	@PostMapping(value = "/comment/comment_register", produces = "application/json")
	public void comment_register(@RequestBody CommentVO comment) {
		log.info("comment_register............." + comment.getContent());
		boardService.comment_register(comment);

	}

	@ResponseBody
	@PostMapping(value = "/comment/comment_modify", produces = "application/json")
	public void comment_modify(@RequestBody CommentVO comment) {
		log.info("comment_modify.............");
		log.info("comment_modify............." + comment.getContent());
		boardService.comment_modify(comment);

	}

	@ResponseBody
	@PostMapping("/comment/comment_remove")
	public void comment_remove(@RequestBody CommentVO comment) {
		log.info("comment_remove..............");
		boardService.comment_remove(comment.getId());

	}
	
	//0309추가
	@ResponseBody // 데이터 자체를 보낼때?
	@GetMapping("/download")
	public File download(@RequestParam("bno") Long bno, HttpSession session, HttpServletResponse response) {
		
		return common.download(boardService.get(bno).getFilepath(),boardService.get(bno).getFilename() , session, response);
	}

}
