package idwall.desafio.crawler.controllers;

import idwall.desafio.crawler.models.RedditThread;
import idwall.desafio.crawler.models.RequestSubreddits;
import idwall.desafio.crawler.services.RedditService;
import idwall.desafio.crawler.services.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class InputController {

    private RedditService reddit;
    private TelegramService telegram;

    @Autowired
    public InputController(RedditService reddit, TelegramService telegram){
        this.reddit = reddit;
        this.telegram = telegram;
    }

    @RequestMapping("/input")
    public String getInput(Model model){
        model.addAttribute(new RequestSubreddits());
        return "input";
    }

    @RequestMapping(value = "/processinput", method = RequestMethod.POST)
    public ModelAndView processInput(@ModelAttribute RequestSubreddits req, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("result");

        if (bindingResult.hasErrors()) {
            mv.setViewName("error");
            return mv;
        }

        List<String> subreddits = Arrays.asList(req.getSubreddits().split(";"));
        List<RedditThread> threads = reddit.getPopularThreads(subreddits);

        if(req.getSendTelegram()){
            telegram.send(threads, req.getPhoneNumber());
        }

        mv.addObject("threads", threads);

        return mv;
    }
}
