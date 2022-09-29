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

    /**
     * JPQL查询
     * @param contactInfoId
     * @return
     */
    public ContactDO getContact(Long contactInfoId) {
        ContactDO contactDO = contactDao.findContract(contactInfoId);
        return contactDO;
    }

    /**
     * 使用JPQL+param
     * @param moduleType
     * @param contactInfoId
     * @return
     */
    public ContactDO findContactJpqlParam(String moduleType, Long contactInfoId) {

        return contactDao.findContactJpqlParam(moduleType, contactInfoId);
    }
    /**
     * 使用原生SQL模糊查询
     * @param moduleType
     * @param contactInfoId
     * @return
     */
    public ContactDO findContactNativeSql(String moduleType, Long contactInfoId) {
        return contactDao.findContactNativeSql(moduleType, contactInfoId);
    }

    /**
     * 使用原生SQL+param
     * @param moduleType
     * @param contactInfoId
     * @return
     */
    public ContactDO findContactNativeSqlParam(String moduleType, Long contactInfoId) {

        return contactDao.findContactNativeSqlParam(moduleType, contactInfoId);
    }






}
