package ru.redcollar.summer.repository.spec;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import ru.redcollar.summer.entity.Guest;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Data
public class GuestSpecificationBuilder {

    public static Specification<Guest> build(String name, Long age, Boolean isStudent, String jobName) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(name)) {
                Expression<String> nameExpression = root.get("name");
                Expression<String> nameUpper = criteriaBuilder.upper(nameExpression);
                Predicate namePredicate = criteriaBuilder.like(nameUpper, "%" + name.toUpperCase() + "%");
                predicates.add(namePredicate);
            }

            if (age != null) {
                Predicate ageLessThanPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("age"), age);
//                Predicate ageGreaterThanPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("age"), age);
                predicates.add(ageLessThanPredicate);
            }

            if (isStudent != null) {
                Predicate isStudentPredicate = criteriaBuilder.equal(root.get("isStudent"), isStudent);
                predicates.add(isStudentPredicate);
            }

            if (StringUtils.hasLength(jobName)) {
                Expression<String> jobNameExpression = root.get("jobName");
                Expression<String> jobNameUpper = criteriaBuilder.upper(jobNameExpression);
                Predicate jobNamePredicate = criteriaBuilder.like(jobNameUpper, "%" + jobName.toUpperCase() + "%");
                predicates.add(jobNamePredicate);
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
