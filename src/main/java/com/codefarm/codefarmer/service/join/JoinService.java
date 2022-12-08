package com.codefarm.codefarmer.service.join;

import com.codefarm.codefarmer.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository memberRepository;

    public Integer checkUserNick(String nickname) {return memberRepository.checkNick(nickname);}

}