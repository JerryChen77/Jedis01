package com.qf.jedis;

import com.qf.jedis.dao.SiteDao;
import com.qf.jedis.entity.Site;
import com.qf.jedis.utils.JedisUtil;
import com.qf.jedis.utils.MyBatisUtils;
import redis.clients.jedis.Jedis;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Cjl
 * @date 2021/8/9 17:58
 */
public class Test {
    @org.junit.Test
    public void insertSite() throws IOException {
        SiteDao siteDao = MyBatisUtils.getMapper(SiteDao.class);
        List<Site> all = siteDao.findAll();
        MyBatisUtils.closeSession();

        Jedis jedis = JedisUtil.getJedis();
        Integer i = 1;
        for (Site site : all) {
            Map<String, String> map = new HashMap();
            map.put("siteId",String.valueOf(site.getSiteId()));
            map.put("siteName",site.getSiteName());
            map.put("userId",String.valueOf(site.getUserId()));
            jedis.hmset("site:"+(i++),map);
        }
    }

    @org.junit.Test
    public void getSite(){
        Jedis jedis = JedisUtil.getJedis();

        for (int i = 1;!jedis.hgetAll("site:"+i).isEmpty();i++){
            System.out.println(jedis.hgetAll("site:"+i));
            i++;
        }
    }

    @org.junit.Test
    public void insertAndPop(){
        Jedis jedis = JedisUtil.getJedis();
        jedis.del("myset");
        Random random = new Random();
        for (int i =0;i<10;i++){
            jedis.sadd("myset", String.valueOf( random.nextInt(100)));
        }
        String s = jedis.spop("myset");
        System.out.println(s);
    }
}
