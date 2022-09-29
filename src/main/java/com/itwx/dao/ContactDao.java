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
     * @param contractInfoId
     * @return
     */
    @Query("select contract from ContactDO contract where contract.contactInfoId = ?1")
    ContactDO findContract(Long contractInfoId);

    /**
     * 使用原生SQL
     */
    @Query(nativeQuery = true, value = "select * from contact where module_type like ?1% and contact_info_id = ?2")
    ContactDO findContactNativeSql(String moduleType, Long contractInfoId);

    /**
     * 使用原生SQL+@Param
     * 使用@Param，即将？转化为：
     */
    @Query(nativeQuery = true, value = "select * from contact where module_type like :1% and contact_info_id = :2")
    ContactDO findContactNativeSqlParam(@Param("moduleType") String moduleType, @Param("contractInfoId") Long contractInfoId);

    /**
     * 使用JPQL+@Param
     */
    @Query(nativeQuery = true, value = "select * from contact where module_type like :1% and contact_info_id = :2")
    ContactDO findContactJpqlParam(@Param("moduleType") String moduleType, @Param("contractInfoId") Long contractInfoId);


}
