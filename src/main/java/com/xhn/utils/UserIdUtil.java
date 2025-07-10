package com.xhn.utils;

import com.xhn.base.ShortSnowflakeIdGenerator;
import com.xhn.base.SnowflakeIdGenerator;

public class UserIdUtil {

    private static final SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1,1);

    public static long nextId() {
        return generator.nextId();
    }
}
