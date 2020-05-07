package com.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Inject
    private SqlSession sql;

    private static String namespace = "com.board.mappers.board";

    // namespace는 매퍼의 namespace와 일치하여야 함
    // 게시물 목록
    @Override
    public List<BoardVO> list() throws Exception {

        return sql.selectList(namespace + ".list");
        // tbl_board 1행의 데이터의 형태는 BoardVO와 같음. BoardVO를 List 형태로 만들면 게시물 목록을 받아올 수 있음.
    }

    @Override
    public void write(BoardVO vo) throws Exception {

        sql.insert(namespace + ".write", vo);
    }

    // 게시물 조회
    @Override
    public BoardVO view(int bno) throws Exception {

        return sql.selectOne(namespace + ".view", bno);
    }

    // 게시물 수정
    @Override
    public void modify(BoardVO vo) throws Exception {

        sql.update(namespace + ".modify", vo);
    }

    // 게시물 삭제
    @Override
    public void delete(int bno) throws Exception {
        sql.delete(namespace + ".delete", bno);

    }

    // 게시물 총 갯수
    @Override
    public int count() throws Exception {

        return sql.selectOne(namespace + ".count");
    }

    // 게시물 목록 + 페이징
    @Override
    public List listPage(int displayPost, int postNum) throws Exception {

        HashMap data = new HashMap();

        data.put("displayPost", displayPost);
        data.put("postNum", postNum);

        return sql.selectList(namespace + ".listPage", data);
    }

}
