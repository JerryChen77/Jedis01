import com.alibaba.fastjson.JSONObject;
import com.qf.jedis.entity.Student;
import com.qf.jedis.utils.JedisUtil;
import com.sun.xml.internal.ws.developer.Serialization;
import org.junit.Test;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cjl
 * @date 2021/8/9 17:17
 */
public class RedisTest {

    @Test
    public void testHashObjectToRedis(){
        Jedis jedis = JedisUtil.getJedis();
        Student stu = new Student(01,"张三",18);
        Map map = new HashMap();
        map.put("id",String.valueOf(stu.getId()));
        map.put("name",stu.getName());
        map.put("age",String.valueOf(stu.getAge()));

        jedis.hmset("Student",map);


    }
    //serialize存对象
    @Test
    public void testObjectToRedis(){
        Jedis jedis = JedisUtil.getJedis();
        Student stu = new Student(02,"李四",20);
        String key = "Student002";
        byte[] byteStu = SerializationUtils.serialize(stu);
        byte[] byteKey = SerializationUtils.serialize(key);
        jedis.set(byteKey,byteStu);
    }
    //deserialize取对象
    @Test
    public void testObjectFromRedis(){
        Jedis jedis = JedisUtil.getJedis();
        String key = "Student002";
        byte[] byteKey = SerializationUtils.serialize(key);
        byte[] byteValue = jedis.get(byteKey);
        Object stu = SerializationUtils.deserialize(byteValue);
        if (stu instanceof Student){
            Student student = (Student) stu;
            System.out.println(student);
        }else{
            System.out.println("解析错误");
        }
    }


    @Test
    public void testObjectToRedisWithJson(){
        Jedis jedis = JedisUtil.getJedis();
        Student stu = new Student(1004,"王五",23);
        String s = JSONObject.toJSONString(stu);
        jedis.set("student:"+stu.getId(),s);
    }

    @Test
    public void testObjectFromRedisWithJson(){
        Jedis jedis = JedisUtil.getJedis();
        String s1 = jedis.get("student:1004");
        Student student = JSONObject.parseObject(s1, Student.class);
        System.out.println(student);
    }
}
