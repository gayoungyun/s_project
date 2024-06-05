package com.care.root.mybatis.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardMapper {
	public List<BoardDTO> boardAllList( @Param("start") int s, @Param("e") int e);
	//두개이상넘길때 파람으로 변수이름 각각써야함
	public int writeSave(BoardDTO dto);
	public BoardDTO contentView(int writeNo);
	public void upHit(int writeNo);
	public int modify(BoardDTO dto);
	public int delete(int writeNo);
	public void addRep(Map<String, String>map);
	public List<BoardRepDTO> getData(@Param("wg") int wg);
	public int getCounter();
}
