/**
 * 
 */
package com.huobi.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.huobi.security.rbac.domain.Resource;


@Repository
public interface ResourceRepository extends HuobiRepository<Resource> {

	Resource findByName(String name);

}
