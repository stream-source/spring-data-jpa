package com.itwx.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description  contact_info
 * @Author  zzh
 * @Date 2022-09-28 
 */

@Data
@Entity
@Table(name ="contact_info")
public class ContactInfoDO {

	/**
	 * 客户联系人id
	 */
	@Id
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
	 * 联系人姓名
	 */
   	@Column(name = "contact_name" )
	private String contactName;

	/**
	 * 手机号
	 */
   	@Column(name = "phone" )
	private String phone;

	/**
	 * 电话
	 */
   	@Column(name = "telephone" )
	private String telephone;

	/**
	 * 邮箱
	 */
   	@Column(name = "email" )
	private String email;

	/**
	 * 职位
	 */
   	@Column(name = "position" )
	private String position;

	/**
	 * 直属上级id
	 */
   	@Column(name = "direct_supervisor_id" )
	private Long directSupervisorId;

	/**
	 * 是否决策人1：是，0：否
	 */
   	@Column(name = "policymaker_flag" )
	private Integer policymakerFlag;

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
