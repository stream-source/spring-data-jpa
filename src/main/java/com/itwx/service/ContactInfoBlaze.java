package com.itwx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class ContactInfoBlaze {

    @Autowired
    private EntityManager entityManager;
}
