package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

    // 에러 로그 출력
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    BoardService service;

    // 게시물 목록
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void getList(Model model) throws Exception {

        List<BoardVO> list = null;
        list = service.list();

        model.addAttribute("list", list);
        // Model은 컨트롤러와 뷰를 연결해주는 역할을 함.
    }

    // 게시물 작성
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    // get 방식은 서버 -> 사용자로 데이터가 이동하는 것.
    // 게시물 작성처럼 사용자 -> 서버로 데이터가 이동하려면 post 메서드 필요.
    public void getWrite() throws Exception {

    }

    // 게시물 작성
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    // post 방식의 메서드를 만들고 컨트롤러가 데이터를 받은 후, service에 넘기고 이걸 다시 DAO에 넘기고
    // 마지막으로 데이터 베이스 에옮기면서 데이터가 입력됨.
    public String postWrite(BoardVO vo) throws Exception {

        // 에러 로그 출력
        logger.error("WRITER 에 값이 나오는지 확인 : " + vo.getWriter());

        service.write(vo);

        return "redirect:/board/list";
        // 작업 후 /board/list 페이지로 돌아가겠다.
    }

    // 게시물 조회
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public void getView(@RequestParam("bno") int bno, Model model) throws Exception {

        BoardVO vo = service.view(bno);

        model.addAttribute("view", vo);
    }

    // 게시물 수정
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {

        BoardVO vo = service.view(bno);

        model.addAttribute("view", vo);
    }

    // 게시물 수정
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String postModify(BoardVO vo) throws Exception {

        service.modify(vo);

        return "redirect:/board/view?bno=" + vo.getBno();
    }

    // 게시물 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("bno") int bno) throws Exception {

        service.delete(bno);

        return "redirect:/board/list";
    }

    // 게시물 목록 + 페이징 추가
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public void getListPage(Model model, @RequestParam("num") int num) throws Exception {

        Page page = new Page();

        page.setNum(num);
        page.setCount(service.count());

        List list = null;
        list = service.listPage(page.getDisplayPost(), page.getPostNum());

        model.addAttribute("list", list);
//        model.addAttribute("pageNum", page.getPageNum());
//
//        model.addAttribute("startPageNum", page.getStartPageNum());
//        model.addAttribute("endPageNum", page.getEndPageNum());
//
//        model.addAttribute("prev", page.isPrev());
//        model.addAttribute("next", page.isNext());

        model.addAttribute("page", page);

        model.addAttribute("select", num);

//        
//        // 게시물 총 갯수
//        int count = service.count();
//
//        // 한 페이지에 출력할 게시물 갯수
//        int postNum = 10;
//
//        // 하단 페이징 번호 ( [게시물 총 갯수 / 한 페이지에 출력할 갯수]의 올림)
//        int pageNum = (int) Math.ceil((double) count / postNum);
//
//        // 출력할 게시물
//        int displayPost = (num - 1) * postNum;
//
//        // 한번에 표시할 페이징 번호의 갯수
//        int pageNum_cnt = 10;
//
//        // 표시되는 페이지 번호 중 마지막 번호
//        int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);
//
//        // 표시되는 페이지 번호 중 첫번째 번호
//        int startPageNum = endPageNum - (pageNum_cnt - 1);
//
//        // 마지막 번호 재계산
//        int endPageNum_tmp = (int) (Math.ceil((double) count / (double) pageNum_cnt));
//
//        if (endPageNum > endPageNum_tmp) {
//            endPageNum = endPageNum_tmp;
//        }
//
//        boolean prev = startPageNum == 1 ? false : true;
//        boolean next = endPageNum * pageNum_cnt >= count ? false : true;
//
//        List<BoardVO> list = null;
//        list = service.listPage(displayPost, postNum);
//        model.addAttribute("list", list);
//        model.addAttribute("pageNum", pageNum);
//
//        // 시작 및 끝 번호
//        model.addAttribute("startPageNum", startPageNum);
//        model.addAttribute("endPageNum", endPageNum);
//
//        // 이전 및 다음
//        model.addAttribute("prev", prev);
//        model.addAttribute("next", next);
//
//        // 현재 페이지
//        model.addAttribute("select", num);
//        
    }

    // 게시물 목록 + 페이징 추가 + 검색
    @RequestMapping(value = "/listPageSearch", method = RequestMethod.GET)
    public void getListPageSearch(Model model, @RequestParam("num") int num,
            @RequestParam(value = "searchType", required = false, defaultValue = "title") String searchType,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) throws Exception {

        Page page = new Page();

        page.setNum(num);
        page.setCount(service.count());

        List<BoardVO> list = null;
        // list = service.listPage(page.getDisplayPost(), page.getPostNum());
        list = service.listPageSearch(page.getDisplayPost(), page.getPostNum(), searchType, keyword);

        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("select", num);

    }

}