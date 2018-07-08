package examples.jpashop.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class OrderSpec {

    public static Specification<examples.jpashop.domain.Order> memberNameLike(final String memberName) {
        return new Specification<examples.jpashop.domain.Order>() {
            public Predicate toPredicate(Root<examples.jpashop.domain.Order> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                if (StringUtils.isEmpty(memberName)) return null;

                Join<examples.jpashop.domain.Order, Member> m = root.join("member", JoinType.INNER); //회원과 조인
                return builder.like(m.<String>get("name"), "%" + memberName + "%");
            }
        };
    }

    public static Specification<examples.jpashop.domain.Order> orderStatusEq(final OrderStatus orderStatus) {
        return new Specification<examples.jpashop.domain.Order>() {
            public Predicate toPredicate(Root<examples.jpashop.domain.Order> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                if (orderStatus == null) return null;

                return builder.equal(root.get("status"), orderStatus);
            }
        };
    }
}