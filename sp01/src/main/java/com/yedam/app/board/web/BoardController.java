package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;

	// 전체조회
	@GetMapping("boardList")
	public String getBoardList(Model model) {
		List<BoardVO> list = boardService.getBoardALL();
		// System.out.println(list);
		model.addAttribute("boardList", list);
		return "board/boardList"; // > 페이지를 요청함 ViewResolver로 이동 타일즈로 처리됨 <definition name="board/boardList"

	}

	// 단건조회
	@GetMapping("boardInfo")
	public String getBoardInfo(BoardVO boardVO, Model model) { // @RequestParam 써도됨
		BoardVO findVO = boardService.getBoardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		return "board/boardInfo";

	}

	// 등록 페이지 요청
	@GetMapping("boardInsert")
	public String BoardInsertForm() {
		return "board/boardInsert";
	}

	// 등록처리
	@PostMapping("boardInsert") // <form action="boardInsert" 으로부터 받아온 값들을
	public String BoardInsertProcess(BoardVO boardVO) {
		boardService.insertBoardInfo(boardVO); // bno= getBno();

//		String path = null;
//
//		if (bno > -1) {
//			path = "redirect:boardInfo?bno=" + bno; // 등록에 성공했다면 단건조회페이지
//		} else {
//			path = "redirect:boardList";
//		}
//		return path; // 컨트롤러 재호출

		return "redirect:boardList";

	}

	// 수정페이지 요청
	@GetMapping("boardUpdate")
	public String updateBoardInfoForm() {
		return "board/boardUpdate";
	}

	// 수정처리
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdateProcess(BoardVO boardVO) {
		return boardService.updateBoardInfo(boardVO); // >map이 리턴됨
	}

}
