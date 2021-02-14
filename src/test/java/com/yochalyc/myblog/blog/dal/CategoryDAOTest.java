package com.yochalyc.myblog.blog.dal;

import com.yochalyc.myblog.blog.dal.dao.CategoryDAO;
import com.yochalyc.myblog.blog.dal.model.CategoryDO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryDAO categoryDAO;

    @Test
    @Transactional
    public void testFindByName() {
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setUid("test_uid");
        categoryDO.setName("test");
        categoryDO.setCanDelete(true);

        entityManager.persist(categoryDO);

        CategoryDO result = categoryDAO.findByName("test");

        Assert.assertEquals(result.getUid(), "test_uid");

    }

    @Test
    @Transactional
    public void testSaveWithoutId(){
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setName("test");
        categoryDO.setCanDelete(true);
        String uid = categoryDAO.saveWithoutId(categoryDO);
        Assert.assertNotNull(uid);
    }
}
