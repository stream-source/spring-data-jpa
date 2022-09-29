package com.itwx.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description  contact
 * @Author  zzh
 * @Date 2022-09-28 
 */

@Data
@Entity(name = "ContactDO") //定义实体类
@Table(name ="contact")//定义数据库表名
public class ContactDO {

	/**
	 * 联系人关系id
	 */
	@Id
   	@Column(name = "contact_id" )
	private Long contactId;

	/**
	 * 联系人基本信息id
	 */
   	@Column(name = "contact_info_id" )
	private Long contactInfoId;

	/**
	 * 客户id
	 */
   	@Column(name = "customer_id" )
	private Long customerId;

	/**
	 * 企业id
	 */
   	@Column(name = "tenant_id" )
	private Long tenantId;

	/**
	 * 商机id
	 */
   	@Column(name = "commerce_opportunity_id" )
	private Long commerceOpportunityId;

	/**
	 * 模块类型：客户；商机；
	 */
   	@Column(name = "module_type" )
	private String moduleType;

	/**
	 * 是否首要联系人1：是，0：否
	 */
   	@Column(name = "chiefly_flag" )
	private Integer chieflyFlag;

	/**
	 * 删除标记；0：删除；1：未删除
	 */
   	@Column(name = "has_deleted" )
	private Integer hasDeleted;

	/**
	 * 备注
	 */
   	@Column(name = "remark" )
	private String remark;

	/**
	 * 创建人id
	 */
   	@Column(name = "creator_id" )
	private Long creatorId;

	/**
	 * 创建人
	 */
   	@Column(name = "creator" )
	private String creator;

	/**
	 * 更新人id
	 */
   	@Column(name = "updater_id" )
	private Long updaterId;

	/**
	 * 更新人
	 */
   	@Column(name = "updater" )
	private String updater;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
   	@Column(name = "update_time" )
	private LocalDateTime updateTime;
}
