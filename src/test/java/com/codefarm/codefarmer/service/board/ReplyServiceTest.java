package com.codefarm.codefarmer.service.board;

import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.Reply;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;



//    해당 보드에 댓글 작성하기
    @Test
    public void replyAddTest(){
        ReplyDTO replyDTO = new ReplyDTO();
        Optional<Farmer> findFarmer = farmerRepository.findById(14L);
        Optional<Board> findBoard = boardRepository.findById(18L);

        replyDTO.setReplyContent("김장 하라고해도 안하고싶다!!");
        replyDTO.setMemberId(findFarmer.get());
        replyDTO.setBoardId(findBoard.get());

        replyService.replyAdd(replyDTO);
    }

//    댓글 수정하기
    @Test
    public void replyUpdateTest(){
        ReplyDTO replyDTO = new ReplyDTO();
        Optional<Farmer> findFarmer = farmerRepository.findById(14L);
        Optional<Reply> findReply = replyRepository.findById(19L);

        replyDTO.setReplyContent("수정된 댓글");
        replyDTO.setMemberId(findFarmer.get());
        replyDTO.setReplyId(findReply.get().getReplyId());

        replyService.replyUpdate(replyDTO);
    }

//    댓글 단 사람의 닉네임 갖고오기
    @Test
    public void showReplyNickNameTest(){
        log.info("댓글 단 사람의 닉네임 : " + replyService.showReplyNickName(20L));
    }

//    내가 등록한 댓글 총 수 가져오기
    @Test
    public void showReplyAllCountTest(){
        log.info("내가 남긴 댓글 : " + replyService.showReplyAllCount(14L));
    }

//    댓글 목록 가져오기



//  내가 등록한 댓글 삭제하기
    @Test
    public void removeReplyTest(){
        replyService.removeReply(19L);
    }


}
