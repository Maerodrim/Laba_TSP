package com.example.DataRoadsAndWeather.Controller;

import com.example.DataRoadsAndWeather.Dto.View.View;
import com.example.DataRoadsAndWeather.Model.Card;
import com.example.DataRoadsAndWeather.Model.Session;
import com.example.DataRoadsAndWeather.Repo.CardRepo;
import com.example.DataRoadsAndWeather.Repo.SessionRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("SessionController")
public class SessionController {
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private CardRepo cardRepo;
    @PostMapping("addSession")
    public Integer addSession(@RequestParam String nameSession,
                              Map<String, Object> model) {
        Session session = new Session(nameSession, 8,2,3,2);

        sessionRepo.save(session);

        return sessionRepo.findByNameSession(nameSession).get(0).getIdSession();
    }

    @PostMapping("addNullPackCard")
    public String addNullPackSession(@RequestParam Integer idSession,
                                     Map<String, Object> model) {
        for(int i=0;i<36;i++){
            Card card = new Card(
                    cardRepo.findAllByIdCardNotNull().size(),
                    cardRepo.findByIdCard(i).get(0).getNameCard(),
                    cardRepo.findByIdCard(i).get(0).getDataBegSession(),0,0,
                    cardRepo.findByIdCard(i).get(0).getAllDevelopment(),0,
                    cardRepo.findByIdCard(i).get(0).getAllAnalysis(),0,
                    cardRepo.findByIdCard(i).get(0).getAllTesting(),
                    cardRepo.findByIdCard(i).get(0).getMoney(),
                    cardRepo.findByIdCard(i).get(0).getSubs(),
                    cardRepo.findByIdCard(i).get(0).getColorCard(),
                    cardRepo.findByIdCard(i).get(0).getStatus(),
                    cardRepo.findByIdCard(i).get(0).getPriority());
            cardRepo.save(card);
        sessionRepo.findByIdSession(idSession).get(0).addCard(card);
        sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
            }

        return "Ok";
    }

    @PostMapping(path = "/allSession")
    public String delete() {
        sessionRepo.deleteAll();
        return "Ok";
    }

    @DeleteMapping(path = "DeleteSession")
    public String deleteSession(@RequestParam Integer idSession) {
        sessionRepo.delete(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }

    @PostMapping("filterCard")
    public String filter(@RequestParam Integer filter, Map<String, Object> model) {
        Iterable<Session> card;

        if (filter != null && filter != 0) {
            card = sessionRepo.findByIdSession(filter);
        } else {
            card = sessionRepo.findAll();
        }

        model.put("card", card);

        return "Ok";
    }
    @JsonView(View.UI.class)
    @GetMapping("getSession")
    public Session getSession(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0);
    }

    @JsonView(View.UI.class)
    @GetMapping("getCard")
    public Set<Card> getCard(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0).getCard();
    }
    @GetMapping("getDate")
    public Integer getDate(@RequestParam Integer idSession, Map<String, Object> model) {

        return sessionRepo.findByIdSession(idSession).get(0).getDataSession();
    }

    @GetMapping("getAnalysis")
    public Integer getAnalysis(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0).getAnal();
    }

    @GetMapping("getDevelopment")
    public Integer getDevelopment(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0).getDev();
    }

    @GetMapping("getTesting")
    public Integer getTesting(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0).getTest();
    }

    @PostMapping("setTesting")
    public String setTesting(@RequestParam Integer idSession, @RequestParam Integer value, Map<String, Object> model) {
        sessionRepo.findByIdSession(idSession).get(0).setTest(value);
        sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }

    @PostMapping("setAnalysis")
    public String setAnalysis(@RequestParam Integer idSession, @RequestParam Integer value, Map<String, Object> model) {
       sessionRepo.findByIdSession(idSession).get(0).setAnal(value);
       sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }

    @PostMapping("setDevelopment")
    public String setDevelopment(@RequestParam Integer idSession, @RequestParam Integer value, Map<String, Object> model) {
        sessionRepo.findByIdSession(idSession).get(0).setDev(value);
        sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }
    @PostMapping("setData")
    public String setData(@RequestParam Integer idSession, @RequestParam Integer value, Map<String, Object> model) {
        sessionRepo.findByIdSession(idSession).get(0).setDataSession(value);
        sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }
}
