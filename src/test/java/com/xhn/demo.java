package com.xhn;

import com.xhn.utils.UserIdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xhn
 * @date 2025/7/8 11:31
 * @description
 */
@SpringBootTest
public class demo {

    @Test
    public void test01() {
        long l = UserIdUtil.nextId();
        System.out.println(l);
    }

}
