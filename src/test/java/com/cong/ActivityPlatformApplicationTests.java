package com.cong;

import com.cong.dao.ActivityDao;
import com.cong.pojo.Activity;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;

@SpringBootTest
@MapperScan("com.cong.mapper")
class ActivityPlatformApplicationTests {

    @Resource
    private ActivityDao activityDao;

    @Test
    void contextLoads() {
        //System.out.println(userDao.getUserByEmail("1360169584@qq.com").getCreateTime());
        activityDao.save(new Activity(null, "11", "11111", new Timestamp(123, 2, 8, 12, 0, 0, 0), new Timestamp(123, 2, 8, 23, 0, 0, 0), 5, null, null));
    }

}
