package jpabasic.jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("회원1");
            member.setTeam(teamA);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            // FLUSH 자동 호출
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            // 벌크 연산 수행 후 영속성 컨텍스트를 초기화 해야한다. (데이터 정합성이 안맞으므로)
            em.clear();

            Member findMember = em.find(Member.class, member.getId());


            System.out.println("resultCount = " + resultCount);


            System.out.println("findMember.getAge() = " + findMember.getAge());


            tx.commit();
            System.out.println("======================================2");

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
