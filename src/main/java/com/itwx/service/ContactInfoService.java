package com.itwx.service;

import com.itwx.dao.ContactInfoRepository;
import com.itwx.entity.ContactInfoDO;
import com.itwx.query.ContactInfoQry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ContactInfoService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    /**
     * 分页查询
     * 1、普通分页：分页+排序
     * 2、单表：分页+排序+动态查询
     * 3、多表：分页+排序+动态查询
     * 思路：
     * 3.1：选择主表；
     *  一般选择带”排序字段”的表作为主表，最终目的是”分页+排序”只存在于主表；
     *  主辅表都可以存在”动态查询”；
     * 3.2：先操作辅表；
     *  对辅表进行动态查询等操作，获取逻辑外键的set集合；
     *  把辅表查询得到的逻辑外键的set集合，返回给主表；
     * 3.3:再操作主表；
     *  把辅表返回的逻辑外键set集合，返回给主表；
     * 3.4：完成分页和排序操作
     *
     * 3.5：填充辅表字段；
     *  对分页结果进行stream操作，补上辅表字段信息；
     *  最终的pageSize数量不会很大，补充字段的操作性能消耗不会很大
     */

    /**
     * 使用JPQL查询、排序，不支持limit
     */
    public List<ContactInfoDO> listInfoJpql(String contractName) {
//        Sort sort = new Sort(Sort.Direction.DESC, List.of("createTime"));
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        return contactInfoRepository.findJpqlSort(contractName, sort);
    }

    /**
     * 原生SQL查询，排序，不支持Sort,需要原生SQL中排序
     */
    public List<ContactInfoDO> listInfoNativeSql(String contractName) {
        return contactInfoRepository.findNativeSql(contractName);
    }

    /**
     * 分页查询,普通查询
     */
    public List<ContactInfoDO> listPageJpql(ContactInfoQry contactInfoQry) {
        //排序+分页查询
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable of = PageRequest.of(contactInfoQry.getPageIndex() - 1, contactInfoQry.getPageSize(), sort);
        Page<ContactInfoDO> page = contactInfoRepository.findJpqlPage(contactInfoQry.getPhone(), of);
        return page.getContent();
    }

    /**
     * 分页查询：单表+分页+动态条件
     */
}
