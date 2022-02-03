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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자A");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.setTeam(team);

            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("관리자B");
            member2.setAge(20);
            member2.setType(MemberType.ADMIN);
            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select m.team from Member m"; // 묵시적 내부 조인이 발생함. (=안티패턴)
            List<Team> result = em.createQuery(query, Team.class)
                    .getResultList();

            for (Team team1 : result) {
                System.out.println("team1 = " + team1);
            }

            String query2 = "select m.username from Team t join t.members m";

            List result2 = em.createQuery(query2, Collection.class)
                    .getResultList();

            for (Object o : result2) {
                System.out.println("o = " + o);
            }

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
