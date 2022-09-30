package com.itwx.dao;

import com.itwx.entity.ContactInfoDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactInfoRepository extends BaseJpaRepository<ContactInfoDO, Long>{

    @Query(value = "select contactInfoDO from ContactInfoDO contactInfoDO where contactInfoDO.contactName like ?1%")
    List<ContactInfoDO> findJpqlSort(String contractName, Sort sort);

    @Query(nativeQuery = true, value = "select * from contact_info where contact_name like ?1% order by create_time desc ")
    List<ContactInfoDO> findNativeSql(String contractName);

    @Query(value = "select contactInfoDO from ContactInfoDO contactInfoDO where contactInfoDO.phone like ?1%")
    Page<ContactInfoDO> findJpqlPage(String phone, Pageable of);

    @Query(value = "select * from contact_info where contact_name like ?1%", nativeQuery = true)
    Page<ContactInfoDO> findContactByName(String contactName, Pageable of);
}
