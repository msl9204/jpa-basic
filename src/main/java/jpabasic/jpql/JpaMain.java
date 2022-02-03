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

            String query = "select m from Member m join fetch m.team";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            // fetch join을 하지 않을 시, N+1 문제가 발생!

            for (Member m : result) {
                System.out.println("member = " + m.getUsername() + ", " + m.getTeam().getName());
            }

            em.flush();
            em.clear();


            // 컬렉션 페치 조인
            // distinct 사용 시, 애플리케이션 레벨에서 추가로 걸러줌
            String query2 = "select distinct t from Team t join fetch t.members";
            List<Team> result2 = em.createQuery(query2, Team.class)
                    .getResultList();

            System.out.println("result2.size() = " + result2.size());

            for (Team team : result2) {
                System.out.println("team = " + team.getName() + ", " + team.getMembers().size());
            }

            em.flush();
            em.clear();

            // 페치조인과 일반조인의 차이
            // 일반 조인 실행 시 연관된 엔티티를 함께 조회하지 않음

            String query3 = "select t from Team t join t.members m";
            List<Team> result3 = em.createQuery(query3, Team.class)
                    .getResultList();

            for (Team team : result3) {
                System.out.println("team = " + team.getName() + ", " + team.getMembers().size());
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
