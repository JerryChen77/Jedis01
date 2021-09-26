package com.qf.jedis.dao;

import com.qf.jedis.entity.Site;

import java.util.List;

/**
 * @author Cjl
 * @date 2021/8/9 19:04
 */
public interface SiteDao {

   List<Site> findAll();
}
