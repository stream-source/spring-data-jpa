package com.itwx.service;

import com.itwx.dao.ContactDao;
import com.itwx.entity.ContactDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author particle
 * @date 2022/9/28 4:37 PM
 * @describe
 */
@Service
public class ContactService {
    @Autowired
    private ContactDao contactDao;

    public ContactDO getContact(Long contractInfoId) {
        ContactDO contactDO = contactDao.findContract(contractInfoId);
        return contactDO;
    }
}
