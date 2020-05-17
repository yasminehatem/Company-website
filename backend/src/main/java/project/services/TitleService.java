package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Title;
import project.repositories.TitleRepository;

import java.util.List;

@Service
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;




    public List<String> getAllTitles() {



        List<String> listOfTitles = titleRepository.findAllTitleNames();



         return  listOfTitles;

        }
    public List<Title> getAll(){

        return titleRepository.findAll();

    }


    }





