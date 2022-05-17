package com.juliano.provaerp.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.querydsl.core.types.Predicate;
import java.util.List;

@NoRepositoryBean
public interface JoinCapableRepository<T, UUID> extends JpaRepository<T, UUID>, QuerydslPredicateExecutor<T> {
    Page<T> findAll(Predicate var1, Pageable var2);

    List<T> findAll();
}