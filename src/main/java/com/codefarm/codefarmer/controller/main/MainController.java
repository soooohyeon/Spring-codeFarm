package com.codefarm.codefarmer.controller.main;

import com.codefarm.codefarmer.service.alba.AlbaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainController {

    private final AlbaService albaService;

    @GetMapping("main")
    public void getAlbaList(Model model) {
        model.addAttribute("albas", albaService.showAlbaList());
    }


}

