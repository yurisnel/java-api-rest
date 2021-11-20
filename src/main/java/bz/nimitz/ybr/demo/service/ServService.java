package bz.nimitz.ybr.demo.service;

import bz.nimitz.ybr.demo.model.History;
import bz.nimitz.ybr.demo.model.Serv;
import bz.nimitz.ybr.demo.model.State;
import bz.nimitz.ybr.demo.repository.HistoryRepository;
import bz.nimitz.ybr.demo.repository.ServRepository;
import bz.nimitz.ybr.demo.repository.StateRepository;
import org.springframework.data.domain.Sort;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServService {

    @Autowired
    private ServRepository servRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Value("${spring.app.urldata}")
    private String urldata;

    public List<History> getStatusCurrentTest() {
        return historyRepository.findAll(PageRequest.of(0, 8, Sort.by(Sort.Direction.ASC, "createdAt"))).toList();
    }

    public List<State> getStatusCurrent() {
        /*Iterable<State> findAllIterable = stateRepository.findAll();
        for (State state : findAllIterable) {
            state.setServices();
        }*/
        return stateRepository.findAll();
    }
    public List<Serv> getStatusCurrent(String stateName) {
        return servRepository.findAll();
    }

    public void loadData() {
        Document doc;
        try {
            doc = Jsoup.connect(this.urldata).get();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            List<Serv> listServ = new ArrayList<>();
            Serv servCurrent = null;
            State stateCurrent = null;
            Elements tr_list = doc.select("table#ctl00_ContentPlaceHolder1_gdvDisponibilidade2 tr");
            int i = 0; // Row Services
            for (Element tr : tr_list) {
                Elements td_list = tr.select("th,td");
                int j = 0; // Col States
                for (Element td : td_list) {
                    // Insert Services or get id
                    if (i == 0 && j > 0) {
                        List<Serv> r = servRepository.findByName(td.text());
                        if (r.size() > 0) {
                            servCurrent = r.get(0);
                        } else {
                            servCurrent = new Serv(td.text());
                            servCurrent = servRepository.save(servCurrent);
                        }
                        listServ.add(servCurrent);
                    }
                    // Insert State or get id
                    else if (i > 0 && j == 0) {
                        List<State> r = stateRepository.findByName(td.text());
                        if (r.size() > 0) {
                            stateCurrent = r.get(0);
                        } else {
                            stateCurrent = new State(td.text());
                            stateCurrent = stateRepository.save(stateCurrent);
                        }
                    } else if (i > 0 && j > 0) {
                        servCurrent = listServ.get(j - 1);
                        String src = td.select("img").attr("src");
                        int availability = 0;
                        if (src.contains("verde")) {
                            availability = 1;
                        } else if (src.contains("amarillo")) {
                            availability = 2;
                        } else if (src.contains("rojo")) {
                            availability = 3;
                        }
                        History newStateSer = new History(servCurrent, stateCurrent, availability, currentTime);
                        historyRepository.save(newStateSer);
                    }

                    j++;
                }

                i++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Serv> getAllService() {
        return servRepository.findAll();
    }

    /*
     * public Serv saveService(Serv service) { return servRepository.save(service);
     * }
     * 
     * public Serv getService(Long id) { return
     * servRepository.findById(id).orElseThrow(() -> new
     * ObjectNotFoundException(id)); }
     * 
     * public boolean deleteService(Long id) { servRepository.deleteById(id); return
     * true; }
     */

}
