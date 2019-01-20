package fr.laerce.cinema.service;

import fr.laerce.cinema.dao.PersonDao;
import fr.laerce.cinema.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonManager {

    PersonDao personDao;

    public PersonManager(PersonDao personDao){
        this.personDao = personDao;
        assert (personDao != null);
    }

    public List<Person> getAll(){
        return personDao.findAllByOrderBySurname();
    }

    public Person getById(Long id){
        return personDao.findById(id).get();
    }
}
