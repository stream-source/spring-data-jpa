package com.itwx.dao;

import com.itwx.entity.ContactDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author particle
 * @date 2022/9/28 3:23 PM
 * @describe
 */
@Repository
public interface ContactDao extends BaseJpaRepository<ContactDO, Long> {

    /**
     * 使用JPQL查询
     * 即JPQL用类名与字段属性名表示，注意别名和入参坐标
     * @param contactInfoId
     * @return
     */
    @Query("select contract from ContactDO contract where contract.contactInfoId = ?1")
    ContactDO findContract(Long contactInfoId);

    /**
     * 使用JPQL+@Param
     */
    @Query(value = "select contract from ContactDO contract where contract.moduleType like :moduleType% and contract.contactInfoId = :contactInfoId")
    ContactDO findContactJpqlParam(@Param("moduleType") String moduleType, @Param("contactInfoId") Long contactInfoId);


    /**
     * 使用原生SQL
     */
    @Query(nativeQuery = true, value = "select * from contact where module_type like ?1% and contact_info_id = ?2")
    ContactDO findContactNativeSql(String moduleType, Long contactInfoId);

    /**
     * 使用原生SQL+@Param
     * 使用@Param，即将“？+入参坐标 “ 转化为 “：+ 变量名“
     */
    @Query(nativeQuery = true, value = "select * from contact where module_type like :moduleType% and contact_info_id = :contactInfoId")
    ContactDO findContactNativeSqlParam(@Param("moduleType") String moduleType, @Param("contactInfoId") Long contactInfoId);



}
