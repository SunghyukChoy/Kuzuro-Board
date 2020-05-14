package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;

/**
 * @Autowired 나 @Inject 어노테션을 통해, 형(class, interface) 기반으로 스프링 Bean을 가져올 경우 만약에
 *            구현된 컴포넌트가 2개 이상일 경우 NoUniqueBeanDefinitionException 이 발생하게 된다. 이를
 *            회피하기 위해서는 autowired나 inject로 가져오는 변수명을 Spring Bean id 와 맞추어 줘야 하는데
 * @Component 류 어노테이션 인자로 value 가 그 이름을 강제로 지정해 줄 수 있다.
 * @author likea
 *
 */
@Service(value = "service")
public class BoardServiceImpl implements BoardService {

    @Inject
    private BoardDAO dao;

    // 게시물 목록
    @Override
    public List<BoardVO> list() throws Exception {
        return dao.list();
    }

    // 게시물 작성
    @Override
    public void write(BoardVO vo) throws Exception {
        dao.write(vo);
    }

    // 게시물 조회
    @Override
    public BoardVO view(int bno) throws Exception {

        return dao.view(bno);
    }

    // 게시물 수정
    @Override
    public void modify(BoardVO vo) throws Exception {
        dao.modify(vo);

    }

    // 게시물 삭제
    @Override
    public void delete(int bno) throws Exception {
        dao.delete(bno);

    }

    // 게시물 총 갯수
    @Override
    public int count() throws Exception {

        return dao.count();
    }

    // 게시물 목록 + 페이징
    @Override
    public List listPage(int displayPost, int postNum) throws Exception {

        return dao.listPage(displayPost, postNum);
    }

    // 게시물 목록 + 페이징 + 검색
    @Override
    public List<BoardVO> listPageSearch(int displayPost, int postNum, String searchType, String keyword)
            throws Exception {
        return dao.listPageSearch(displayPost, postNum, searchType, keyword);
    }
}