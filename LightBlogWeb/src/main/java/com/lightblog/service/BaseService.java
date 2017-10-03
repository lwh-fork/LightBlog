package com.lightblog.service;

import java.util.List;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 16:11 2017/10/3
 * @Modified By:
 */
public interface BaseService<T> {
	T findById(long id);

	List<T> findAll();

	long insert(T model);
	
	long insertSelective(T model);
	
	T selectByUniqueFiled(T model);
	
	long update(T model);
	
	long delete(long id);
}
