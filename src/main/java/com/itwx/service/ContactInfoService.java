package com.itwx.service;

import com.github.wenhao.jpa.Specifications;
import com.itwx.dao.ContactInfoRepository;
import com.itwx.entity.ContactInfoDO;
import com.itwx.query.ContactInfoQry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
     * 使用JPA，JPQL查询
     * 方式一：
     * Root:代表查询的根对象 即实体
     * CriteriaQuery : 顶层查询对象,用于自定义查询方式
     * CriteriaBuilder:查询构造器,封装了很多的查询条件
     * 持久化接口需要继承 JpaSpecificationExecutor<T>
     */
    public List<ContactInfoDO> listDynamicJpqlOne(ContactInfoQry contactInfoQry) {

        //单表使用Specification
        Specification<ContactInfoDO> specification =(root, criteriaQuery, criteriaBuilder)->{

            //处理单个and条件
            Predicate name = criteriaBuilder.like(root.get("contactName"), contactInfoQry.getContactName() + "%");
            Predicate phone = criteriaBuilder.like(root.get("phone"), contactInfoQry.getPhone() + "%");
            return criteriaBuilder.and(name, phone);
        };

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable of = PageRequest.of(contactInfoQry.getPageIndex() - 1, contactInfoQry.getPageSize(), sort);
        Page<ContactInfoDO> doPage = contactInfoRepository.findAll(specification, of);
        List<ContactInfoDO> contentList = doPage.getContent();
        return contentList;
    }

    /**
     * 处理多条件or
     * @param contactInfoQry
     * @return
     */
    public List<ContactInfoDO> listDynamicJpqlOneOr(ContactInfoQry contactInfoQry) {

        //单表使用Specification
        Specification<ContactInfoDO> nameSpeciation =(root, criteriaQuery, criteriaBuilder)->{
            //处理多条件
            Predicate name = criteriaBuilder.like(root.get("contactName"), contactInfoQry.getContactName() + "%");
            return criteriaBuilder.and(name);
        };

        Specification<ContactInfoDO> phoneSpeciation =(root, criteriaQuery, criteriaBuilder)->{
            //处理多条件
            Predicate phone = criteriaBuilder.like(root.get("phone"), contactInfoQry.getPhone() + "%");
            return criteriaBuilder.and(phone);
        };

        Specification<ContactInfoDO> specification = Specification.where(nameSpeciation).or(phoneSpeciation);
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable of = PageRequest.of(contactInfoQry.getPageIndex() - 1, contactInfoQry.getPageSize(), sort);
        Page<ContactInfoDO> doPage = contactInfoRepository.findAll(specification, of);
        List<ContactInfoDO> contentList = doPage.getContent();
        return contentList;
    }


    /**
     * 使用jpa-spec依赖组件，动态查询
     * Specifications
     * @param contactInfoQry
     * @return
     */
    public List<ContactInfoDO> listSpecificationJpql(ContactInfoQry contactInfoQry) {
        Specification<ContactInfoDO> specification = Specifications.<ContactInfoDO>and()
                .like(contactInfoQry.getContactName() != null && !"".equals(contactInfoQry.getContactName()) ,
                        "contactName", contactInfoQry.getContactName() + "%")
                .like("phone", contactInfoQry.getPhone() + "%")
                .build();

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable of = PageRequest.of(contactInfoQry.getPageIndex() - 1, contactInfoQry.getPageSize(), sort);
        Page<ContactInfoDO> page = contactInfoRepository.findAll(specification, of);
        return page.getContent();
    }


    /**
     * and,or条件查询方式
     * @param contactInfoQry
     * @return
     */
    public List<ContactInfoDO> listSpecificationMoreJpql(ContactInfoQry contactInfoQry) {
        Specification<ContactInfoDO> specification = Specifications.<ContactInfoDO>or()
                .like(contactInfoQry.getContactName() != null && !"".equals(contactInfoQry.getContactName()) ,
                        "contactName", contactInfoQry.getContactName() + "%")
                .predicate(Specifications.or().eq("phone", "13333333333").build())
                .build();

        //或者定义两个specification类，or连接
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable of = PageRequest.of(contactInfoQry.getPageIndex() - 1, contactInfoQry.getPageSize(), sort);
        Page<ContactInfoDO> page = contactInfoRepository.findAll(specification, of);
        return page.getContent();
    }

    /**
     * 若自定义查询
     */
    public List<ContactInfoDO> listNativeSql(ContactInfoQry contactInfoQry) {
        Sort sort = Sort.by(Sort.Direction.DESC, "create_time");
        Pageable of = PageRequest.of(contactInfoQry.getPageIndex() - 1, contactInfoQry.getPageSize(), sort);
        Page<ContactInfoDO> page = contactInfoRepository.findContactByName(contactInfoQry.getContactName(), of);
        return page.getContent();
    }


}
