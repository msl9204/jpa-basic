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

            em.flush();
            em.clear();

            // 엔티티 직접 사용 가능
            String query = "select m from Member m where m = :member";

            Member findMember = em.createQuery(query, Member.class)
                    .setParameter("member", member)
                    .getSingleResult();

            System.out.println("findMember = " + findMember);

            String query2 = "select m from Member m where m.id = :memberId";

            Member memberId = em.createQuery(query2, Member.class)
                    .setParameter("memberId", member.getId())
                    .getSingleResult();

            System.out.println("memberId = " + memberId);

            // 외래키
            String query3 = "select m from Member m where m.team = :team";

            List<Member> members = em.createQuery(query3, Member.class)
                    .setParameter("team", teamA)
                    .getResultList();

            System.out.println("members = " + members);


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
