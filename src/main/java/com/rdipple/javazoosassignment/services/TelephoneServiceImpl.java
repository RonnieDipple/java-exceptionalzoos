package com.rdipple.javazoosassignment.services;

import com.rdipple.javazoosassignment.exceptions.ResourceFoundException;
import com.rdipple.javazoosassignment.exceptions.ResourceNotFoundException;
import com.rdipple.javazoosassignment.models.Telephone;
import com.rdipple.javazoosassignment.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("telephoneService")
public class TelephoneServiceImpl implements TelephoneService {

    private TelephoneRepository telephoneRepository;

    public TelephoneServiceImpl(TelephoneRepository telephoneRepository) {
        this.telephoneRepository = telephoneRepository;
    }

    @Override
    public List<Telephone> findAll() {
        List<Telephone> list = new ArrayList<>();
        telephoneRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Telephone findTelephoneById(long id) {
        return telephoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not available"));
    }

    @Override
    public List<Telephone> findByZooId(long id) {
        return telephoneRepository.findAllByZooZooid(id);
    }

    @Override
    public void delete(long id) {
        if (telephoneRepository.findById(id).isPresent()) {
            telephoneRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("telephone delete error");
        }
    }

    @Override
    public Telephone update(long phoneid, String phonenumber) {
        if (telephoneRepository.findById(phoneid).isPresent()) {
            Telephone telephone = findTelephoneById(phoneid);
            telephone.setPhonenumber(phonenumber);
            return telephoneRepository.save(telephone);
        } else {
            throw new ResourceFoundException("telephone update error");
        }
    }



}
