package jpabook.jpashop.domain.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository // repository 를 spring bean 으로 등록해줌
public class MemberRepository {

    @PersistenceContext // EntityManager Injection
    private EntityManager em;

//    @PersistenceUnit // EntityManagerFactory 를 주입받고 싶다면 이 어노테이션을 사용하면 됨
//    private EntityManagerFactory emf;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
            .getResultList();
    }

    public List<Member> findByNames(String name) {
        return em.createQuery("select m from Member where m.name = :name", Member.class)
            .setParameter("name", name)
            .getResultList();
    }
}
