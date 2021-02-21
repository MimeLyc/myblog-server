package com.yochalyc.myblog.blog.dal.dao;

import com.yochalyc.myblog.blog.dal.model.BaseDO;
import com.yochalyc.myblog.blog.util.Md5Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 使用软删除重写CrudRepository中的方法
 *
 * @param <T>  do-class
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseDAO<T extends BaseDO, ID extends Long> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDeleted = false")
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1 and e.isDeleted = false")
    List<T> findAllById(Iterable<ID> ids);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.isDeleted = false")
    Optional<T> findById(ID id);

    @Query("select e from #{#entityName} e where e.isDeleted = true ")
    @Transactional(readOnly = true)
    List<T> findDeleted();

    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.isDeleted = true")
    long count();

    @Override
    @Transactional(readOnly = true)
    default boolean existsById(ID id) {
        return findById(id).isPresent();
    }

    @Override
    @Query("update #{#entityName} e set e.isDeleted=true where e.id = ?1")
    @Transactional
    @Modifying
    void deleteById(Long id);


    @Override
    @Transactional
    default void delete(T entity) {
        deleteById(entity.getId());
    }

    @Override
    @Transactional
    default void deleteAll(Iterable<? extends T> entities) {
        entities.forEach(entitiy -> deleteById(entitiy.getId()));
    }

    @Override
    @Query("update #{#entityName} e set e.isDeleted=true")
    @Transactional
    @Modifying
    void deleteAll();

    default String saveWithoutId(T entity) {
        String uid = Md5Util.randomToken_16();

        entity.setUid(uid);
        save(entity);

        return uid;
    }

    public T findByUid(String uid);

    @Transactional(readOnly = true)
    @Query(value = "select e from #{#entityName} e " +
            "where e.isDeleted = false and e.uid <> -1"
    )
    Page<T> findByPage(Pageable pageable);

}
