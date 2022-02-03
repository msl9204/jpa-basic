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

            // 일대다 구조에서 페이징은 뻥튀기가 일어나서 페이징하면 안된다.
            // 이럴때는 뒤집어서 다대일 구조로 바꾸고 페이징을 해야한다.
            // 또는 페치 조인 없이 해당 컬렉션에 BatchSize 어노테이션 붙여서 한번에 가져올 수도 있다.
//            String query = "select t from Team t join fetch t.members m"; -> 일대다 페이징 쿼리
            String query = "select t from Team t";

            List<Team> result = em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

            System.out.println("result.size() = " + result.size());

            for (Team team : result) {
                System.out.println("team = " + team + " ," + team.getName());
                for (Member member1 : team.getMembers()) {
                    System.out.println("-> member = " + member1);
                }
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
