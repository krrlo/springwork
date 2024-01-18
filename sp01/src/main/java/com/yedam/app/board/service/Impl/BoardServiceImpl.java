package com.yedam.app.board.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;
import com.yedam.app.emp.mapper.EmpMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;

	@Autowired // 여러개의 매퍼 동시 등록가능
	EmpMapper empMapper;

	// 전체조회
	@Override
	public List<BoardVO> getBoardALL() {

		return boardMapper.selectBoardList();
	}

	// 단건조회
	@Override
	public BoardVO getBoardInfo(BoardVO boardVO) {

		return boardMapper.selectBoard(boardVO);
	}

	// 등록
	@Override
	public int insertBoardInfo(BoardVO boardVO) {
		int result = boardMapper.insertBoard(boardVO);

		if (result == 1) {
			return boardVO.getBno();
		} else {
			return -1;
		}

	}

	// 수정
	@Override
	public Map<String, Object> updateBoardInfo(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;

		int result = boardMapper.updateBoard(boardVO);

		if (result == 1) {
			isSuccessed = true;
		}

		map.put("result", isSuccessed); // boolean 타입 >> map이용
		map.put("target", boardVO); // object 타입 >> map이용
		return map;
	}

	// 삭제
	@Override
	public boolean deleteBoardInfo(int boardNo) {
		int result = boardMapper.deleteBoard(boardNo);

		return result == 1 ? true : false;
	}

}
