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
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

//            em.detach(member);
            em.clear();
            Member member2 = em.find(Member.class, 150L);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
