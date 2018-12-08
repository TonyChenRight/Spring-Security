/**
 * 
 */
package com.huobi.security.rbac.repository.spec;

import com.huobi.security.rbac.domain.Admin;
import com.huobi.security.rbac.dto.AdminCondition;
import com.huobi.security.rbac.repository.support.HuobiSpecification;
import com.huobi.security.rbac.repository.support.QueryWraper;


public class AdminSpec extends HuobiSpecification<Admin, AdminCondition> {

	public AdminSpec(AdminCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Admin> queryWraper) {
		addLikeCondition(queryWraper, "username");
	}

}
