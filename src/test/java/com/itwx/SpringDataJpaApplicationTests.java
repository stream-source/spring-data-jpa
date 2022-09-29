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
    void contextLoads() {
        Long contractInfoId = 1563349047965229056L;
        ContactDO contact = contactService.getContact(contractInfoId);
        contact.getContactId();
    }


}
