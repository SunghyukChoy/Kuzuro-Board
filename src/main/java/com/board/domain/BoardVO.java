package com.board.domain;

import java.util.Date;

// 데이터 베이스에서 만든 테이블과 같은 형태
public class BoardVO {

    // 테이블과 똑같이 만들 때 확인하기 쉽게 하기 위하여 쿼리문을 복사하여 넣었음.
//    CREATE TABLE tbl_board (
//            bno INT NOT NULL AUTO_INCREMENT,
//            title VARCHAR(50) NOT NULL,
//            content TEXT NOT NULL,
//            writer VARCHAR(50) NOT NULL,
//            regDate TIMESTAMP NOT NULL DEFAULT NOW(),
//            viewCnt INT DEFAULT 0,
//            PRIMARY KEY(bno)
//    );

    // 테이블의 컬럼과 동일한 명칭과 데이터형으로 변수를 만들어 줌
    private int bno;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private int viewCnt;

    // VO의 데이터를 사용하기 위한 필수 작업. Getter/Setter 생성
    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

}
