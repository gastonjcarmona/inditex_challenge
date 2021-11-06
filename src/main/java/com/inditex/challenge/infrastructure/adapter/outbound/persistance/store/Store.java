package com.inditex.challenge.infrastructure.adapter.outbound.persistance.store;

import com.inditex.challenge.infrastructure.adapter.outbound.persistance.entity.BaseEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Layer used to abstract the Spring Data framework to the domain.
 */
@Transactional
public abstract class Store<E extends BaseEntity> {

    protected static Predicate safe(final Object valueToQuery, final Predicate predicate) {
        return shouldIgnore(valueToQuery) ? null : predicate;
    }

    protected static boolean shouldIgnore(final Object filterValue) {
        return Objects.isNull(filterValue) || (filterValue instanceof String) && isEmpty((String) filterValue) || (filterValue instanceof Collection) && CollectionUtils.isEmpty((Collection) filterValue);
    }

    protected Specification<E> equals(final String field, final Object value) {
        return (root, cq, cb) -> safe(value, cb.equal(root.get(field), value));
    }

    protected <T extends Comparable<? super T>> Specification<E> greaterThanOrEqualTo(final String field, final T value) {
        return (root, cq, cb) -> safe(value, cb.greaterThanOrEqualTo(root.get(field), value));
    }

    protected <T extends Comparable<? super T>> Specification<E> lessThanOrEqualTo(final String field, final T value) {
        return (root, cq, cb) -> safe(value, cb.lessThanOrEqualTo(root.get(field), value));
    }
}
