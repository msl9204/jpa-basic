package jpabasic.ex1hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaBasicApplication {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");

//            em.persist(member);

//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

//            List<Member> result = em.createQuery("select m from Member as m ", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }


            // 비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            // 영속
//            System.out.println("===========BEFORE===========");
//            em.persist(member);
//            System.out.println("===========AFTER===========");

//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//
//            System.out.println("result = " + (findMember1 == findMember2));


//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("====================================");


//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZZ");

//            Member member = new Member(200L, "member200");
//            em.persist(member);
//
//            em.flush();
//
//            System.out.println("==================================");



            // 영속 상태
//            Member member = em.find(Member.class, 150L);
//            member.setName("AAAAA");

//            em.detach(member);
//            em.clear();
//            Member member2 = em.find(Member.class, 150L);

//            Member member = new Member();
//            member.setId(3L);
//            member.setUsername("C");
//            member.setRoleType(RoleType.GUEST);
//
//            em.persist(member);

            MemberSequence memberSequence1 = new MemberSequence();
            memberSequence1.setUsername("seq1");

//            MemberSequence memberSequence2 = new MemberSequence();
//            memberSequence2.setUsername("seq2");
//            MemberSequence memberSequence3 = new MemberSequence();
//            memberSequence3.setUsername("seq3");

            System.out.println("======================================0");
            em.persist(memberSequence1);
//            em.persist(memberSequence2);
//            em.persist(memberSequence3);

            System.out.println("id1="+memberSequence1.getId());
//            System.out.println("id2="+memberSequence2.getId());
//            System.out.println("id3="+memberSequence3.getId());
            System.out.println("======================================1");


//            MemberTableGenerator memberTableGenerator = new MemberTableGenerator();
//            memberTableGenerator.setUsername("DDD");
//
//            em.persist(memberTableGenerator);


//            MemberIdentity memberIdentity = new MemberIdentity();
//            memberIdentity.setUsername("name");

//            System.out.println("======================================0");
//            em.persist(memberIdentity);
//            System.out.println("id="+memberIdentity.getId());
//            System.out.println("======================================1");



            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(memberLink); // team쪽은 읽기전용이라서 여기서는 member쪽 테이블이 업데이트가 되지 않는다. (member가 연관관계의 주인)
            em.persist(team);

            MemberLink memberLink = new MemberLink();
            memberLink.setUsername("Member1");
            memberLink.changeTeam(team);
            em.persist(memberLink);

//            team.getMembers().add(memberLink); // 1차 캐시 영향받는것을 방지하기 위해서는 team쪽에서 추가해주는게 좋다. => member 객체 setter에서 같이 처리해줘도 좋다.

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<MemberLink> members = findTeam.getMembers();

            for (MemberLink member : members) {
                System.out.println("member = " + member.getUsername());
            }

            tx.commit();
            System.out.println("======================================2");

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
