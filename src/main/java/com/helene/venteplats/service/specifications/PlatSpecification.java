package com.helene.venteplats.service.specifications;

import com.helene.venteplats.model.Plat;
import com.helene.venteplats.service.critere.SearchCriteria;
import org.hibernate.Criteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PlatSpecification implements Specification<Plat> {
    private SearchCriteria searchCriteria;

    public PlatSpecification(SearchCriteria searchCriteria){
        this.searchCriteria = searchCriteria;
    }


    @Override
    public Predicate toPredicate(Root<Plat> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(searchCriteria.getKey()),searchCriteria.getValue());
    }
}
