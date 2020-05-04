package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.BoardVO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    BoardService service;

    // 게시물 목록
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void getList(Model model) throws Exception {

        List<BoardVO> list = null;
        list = service.list();

        model.addAttribute("list", list);
    }

    // 게시물 작성
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public void getWrite() throws Exception {

    }

    // 게시물 작성
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String postWrite(BoardVO vo) throws Exception {
        logger.error("WRITER 에 값이 나오는지 확인 : " + vo.getWriter());

        service.write(vo);

        return "redirect:/board/list";
    }
}