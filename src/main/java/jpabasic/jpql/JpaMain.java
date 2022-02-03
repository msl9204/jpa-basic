package jpabasic.jpql;

import javax.persistence.*;
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

//            String query = "select concat('a', 'b') from Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();

            String query = "select function('group_concat', m.username) from Member m";

            List<String> result = em.createQuery(query, String.class)
                    .getResultList();


            for (String s : result) {
                System.out.println("s = " + s);
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
