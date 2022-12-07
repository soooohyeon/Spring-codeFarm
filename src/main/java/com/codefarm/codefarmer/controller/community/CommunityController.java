package com.codefarm.codefarmer.controller.community;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.repository.board.BoardCustomRepository;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.service.board.BoardService;
import com.codefarm.codefarmer.service.board.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value={"/community/*"})
public class CommunityController {
    private final BoardService boardService;
    private final ReplyService replyService;
    private final BoardRepository boardRepository;
    private final BoardCustomRepository boardCustomRepository;
    private final MemberRepository memberRepository;

//    @GetMapping("/community")
//    public void communityPage(Model model, HttpServletRequest request,@PageableDefault(size = 10, sort = "BoardId", direction = Sort.Direction.DESC) Pageable pageable){
//           /* HttpSession session = (HttpSession)request.getSession();
//            Long memberId = (Long)session.getAttribute("")*/
//            Long memberId = 14l;
//        List<BoardDTO> lists =  boardService.getBoardList();
//
//        lists.stream().map(t -> t.toString()).forEach(t -> log.info("아이디: " + t));
//        List<Long> listsBoardId = new ArrayList<>();
//            lists.stream().map(t -> t.getBoardId()).forEach(listsBoardId::add);
//            List<Long> listsReplyTotal = new ArrayList<>();
//        listsBoardId.stream().map(t -> boardService.showBoardReplyCount(t)).forEach(listsReplyTotal::add);
//        Long boardCount = boardService.showBoardCountMine(memberId);
//        Long replyCount = replyService.showReplyAllCount(memberId);
////        String farmerNickName = boardService.showFarmerNickName(memberId);
////        String userNickName = boardService.showUserNickName(memberId);
//
//
//        model.addAttribute("lists" , lists);
//        model.addAttribute("listsReplyTotal", listsReplyTotal);
//        model.addAttribute("boardCount", boardCount);
//        model.addAttribute("replyCount", replyCount);
//        if(memberId==null){
//            model.addAttribute("nickName",boardService.getNickNameNologin());
//        }else{
//            model.addAttribute("nickName",boardService.showNickName(memberId));
//        }
//
////        if(!farmerNickName.equals("닉넴")){
////            model.addAttribute("nickName", userNickName);
////        }else if (userNickName != null){
////            model.addAttribute("nickName", farmerNickName);
////        }else{
////            model.addAttribute("nickName","닉네임");
////        }
//
//    }

    @GetMapping("/community")
    public void pagingList(Model model/*HttpServletRequest request*/){
//        Page<Board> boards = boardRepository.findAll(pageable);

//        model.addAttribute("lists", boards);
//         HttpSession session = (HttpSession)request.getSession();
//            Long memberId = (Long)session.getAttribute("")

            Long memberId = 14l;
        Long boardCount = boardService.showBoardCountMine(memberId);
        Long replyCount = replyService.showReplyAllCount(memberId);
//        String farmerNickName = boardService.showFarmerNickName(memberId);
//        String userNickName = boardService.showUserNickName(memberId);
        model.addAttribute("boardCount", boardCount);
        model.addAttribute("replyCount", replyCount);
        if(memberId==null){
            model.addAttribute("nickName",boardService.getNickNameNologin());
        }else{
            model.addAttribute("nickName",boardService.showNickName(memberId));
        }
    }



    @GetMapping("/detail")
    public void detailPage(Model model, Long boardId){
        BoardDTO list = boardService.boardShowDetail(boardId);
        boardService.updateViewCount(boardId);
        model.addAttribute("list", list);
    }

    @GetMapping("/delete")
    public RedirectView detailDelete(Long boardId){
        boardService.removeBoard(boardId);
        return new RedirectView("/community/community");
    }


//    @PostMapping("/delete")
//    public RedirectView detailDelete(Long boardId){
//        boardService.removeBoard(boardId);
//        return new RedirectView("/community/community");
//    }
//
//    @GetMapping("/detail")
//    public void detailPage(Long boardId, Model model){
//        boardService.updateViewCount(boardId);
//        model.addAttribute("board", boardService.showOne(boardId));
//    }

    @GetMapping("/register")
    public void writePage(Model model){
        model.addAttribute("board", new BoardDTO());
    }

    @GetMapping("/update")
    public void updatePage(Model model, @RequestParam Long boardId){
        BoardDTO list = boardService.boardShowDetail(boardId);
        model.addAttribute("list", list);
    }

    @PostMapping("updated")
    public RedirectView updateBoard(BoardDTO boardDTO){
        boardService.boardUpdate(boardDTO, boardDTO.getBoardId());
        return new RedirectView("/community/community");
    }

    @PostMapping("/register")
    public RedirectView write(BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        boardDTO.setMember(memberRepository.findById(14L).get());
        boardService.boardAdd(boardDTO);
        redirectAttributes.addFlashAttribute("boardId", boardDTO.getBoardId());
        return  new RedirectView("/community/community");
    }


}
