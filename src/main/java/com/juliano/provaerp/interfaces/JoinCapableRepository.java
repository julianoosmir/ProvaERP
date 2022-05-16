package com.juliano.provaerp.interfaces;

@NoRepositoryBean
public interface JoinCapableRepository<T, UUID> extends JpaRepository<T, I>, QueryDslPredicateExecutor<T> {
    Page<T> findAll(Predicate var1, Pageable var2, JoinDescriptor... var3);

    List<T> findAll(Predicate var1);
}