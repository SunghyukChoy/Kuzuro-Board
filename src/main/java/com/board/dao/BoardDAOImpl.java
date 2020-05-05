package com.board.dao;

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

    // 게시물 목록
    @Override
    public List<BoardVO> list() throws Exception {

        return sql.selectList(namespace + ".list");
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

}
