/**
 * 
 */
package com.huobi.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.huobi.security.rbac.domain.Admin;

@Repository
public interface AdminRepository extends HuobiRepository<Admin> {

	Admin findByUsername(String username);

}
