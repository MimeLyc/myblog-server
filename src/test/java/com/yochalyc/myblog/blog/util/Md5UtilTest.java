package com.yochalyc.myblog.blog.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Md5UtilTest {

    @Test
    public void testMd5() {
        Assert.assertEquals(32, Md5Util.md5_32("test").length());
    }

}
