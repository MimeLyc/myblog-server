package com.yochalyc.myblog.blog.service;

import com.yochalyc.myblog.blog.core.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @Transactional
    public void testAdd() {
        String uid = categoryService.add("test1", true);
        Assert.assertNotNull(uid);
        String dupUid = categoryService.add("test1", true);
        Assert.assertEquals(uid, dupUid);
    }

}
