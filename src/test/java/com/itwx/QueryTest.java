package com.itwx;

import com.itwx.entity.ContactInfoDO;
import com.itwx.query.ContactInfoQry;
import com.itwx.service.ContactInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryTest {

    @Autowired
    private ContactInfoService contactInfoService;


    @Test
    public void listInfoJpql() {
        ContactInfoQry contactInfoQry = new ContactInfoQry();
        contactInfoQry.setContactName("王");
        contactInfoQry.setPhone("150");
        List<ContactInfoDO> list = contactInfoService.listInfoJpql(contactInfoQry.getContactName());
        System.out.println(list);
    }
    @Test
    public void listInfoNativeSql() {
        ContactInfoQry contactInfoQry = new ContactInfoQry();
        contactInfoQry.setContactName("王");
        contactInfoQry.setPhone("150");
        List<ContactInfoDO> list = contactInfoService.listInfoNativeSql(contactInfoQry.getContactName());
        System.out.println(list);
    }

    @Test
    public void listPageJpql() {
        ContactInfoQry contactInfoQry = new ContactInfoQry();
        contactInfoQry.setContactName("王");
        contactInfoQry.setPhone("150");
        contactInfoQry.setPageIndex(1);
        contactInfoQry.setPageSize(10);
        List<ContactInfoDO> list = contactInfoService.listPageJpql(contactInfoQry);
        System.out.println(list);
    }
}
