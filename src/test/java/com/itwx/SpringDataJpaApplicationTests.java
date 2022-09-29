package com.itwx;

import com.itwx.entity.ContactDO;
import com.itwx.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataJpaApplicationTests {

    @Autowired
    private ContactService contactService;

    @Test
    void getContact() {
        Long contactInfoId = 1563349047965229056L;
        ContactDO contact = contactService.getContact(contactInfoId);
        contact.getContactId();
    }

    @Test
    void findContactJpqlParam() {
        Long contactInfoId = 1563349047965229056L;
        String moduleType = "custom";
        ContactDO contact = contactService.findContactJpqlParam(moduleType, contactInfoId);
        System.out.println(contact);
    }
    @Test
    void findContactNativeSql() {
        Long contactInfoId = 1563349047965229056L;
        String moduleType = "custom";
        ContactDO contact = contactService.findContactNativeSql(moduleType, contactInfoId);
        System.out.println(contact);
    }

    @Test
    void findContactNativeSqlParam() {
        Long contactInfoId = 1563349047965229056L;
        String moduleType = "custom";
        ContactDO contact = contactService.findContactNativeSqlParam(moduleType, contactInfoId);
        System.out.println(contact);
    }
}
