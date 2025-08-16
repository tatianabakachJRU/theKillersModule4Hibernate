package com.javarush.entity.lecture_10.examplesHQL;

import com.javarush.entity.lecture_9.Profile;
import com.javarush.entity.lecture_9.User;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

public class HQLExample_1 {

    private final SessionFactory sessionFactory;

    public HQLExample_1(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void getAllUsers() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query<User> query = session.createNamedQuery("User.findAllUsers", User.class);

            //Query<User> query = session.createQuery("from User", User.class);
            query.setFirstResult(2);
            query.setMaxResults(4);
            List<User> users = query.list();
            users.forEach(System.out::println);
            session.getTransaction().commit();
        }
    }

    public void getUserNames() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<String> query = session.createQuery(
                    "SELECT DISTINCT u.username from User u", String.class);
            List<String> users = query.list();
            users.forEach(System.out::println);
            session.getTransaction().commit();
        }
    }

    public void processUsersAsStream() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            query.stream()
                    .map(User::getUsername)
                    .forEach(System.out::println);
            session.getTransaction().commit();
        }
    }

    public void activateProfileByEmailDomain(String emailDomain) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(
                    "update Profile p" +
                            " set p.isActive = true where p.user.email LIKE :emailDomain");
            query.setParameter("emailDomain", "%" + emailDomain);
            int executeUpdate = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println(executeUpdate);

        }
    }

    public void deactivateProfileByEmail(String email) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(
                    "UPDATE Profile p" +
                            " SET p.isActive = false WHERE p.user.email = :email");
            query.setParameter("email", email);
            int executeUpdate = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println(executeUpdate);

        }
    }

    public void scrollFirstThreeUsers() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            ScrollableResults<User> scroll = query.scroll();
            scroll.first();
            for (int i = 0; i < 3 && scroll.next(); i++) {
                User user = scroll.get();
                System.out.println(user);
            }
            session.getTransaction().commit();
        }
    }

    public void goToUserByPosition(int position) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            ScrollableResults<User> scroll = query.scroll();
            scroll.position(position);
            if(scroll.get() != null) {
                String user = scroll.get().getUsername();
                System.out.println(user);
            } else {
                System.out.println("User not found");
            }
        }
    }

    public void getBioProfilesByUsername(String username) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Profile> query = session.createQuery(
                    "FROM Profile p WHERE p.user.username = :name", Profile.class);
            query.setParameter("name", username);
            List<Profile> resultList = query.getResultList();
            resultList.forEach(p -> System.out.println(p.getBio()));
        }
    }

    public void findUserByUsernameList(List<String> usernames) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Profile> query = session.createQuery(
                    "FROM Profile p WHERE p.user.username IN(:names)", Profile.class);
            query.setParameterList("names", usernames);
            List<Profile> resultList = query.getResultList();
            resultList.forEach(user -> System.out.println(user));
            session.getTransaction().commit();
        }
    }

    public void getUsersOrderByUsername() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query<User> query = session.createQuery("from User u ORDER BY u.username DESC", User.class);
            query.setFirstResult(2);
            query.setMaxResults(4);
            List<User> users = query.list();
            users.forEach(System.out::println);
            session.getTransaction().commit();
        }
    }

    public void getMaxHeight(){
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<BigDecimal> query = session.createQuery(
                    "SELECT MAX(p.height)FROM Profile p", BigDecimal.class);
            BigDecimal bigDecimal = query.uniqueResult();
            System.out.println(bigDecimal);
            session.getTransaction().commit();
        }
    }

    public void getUserByEmailNativeQuery(String email) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            NativeQuery<User> nativeQuery = session.createNativeQuery(
                    "SELECT * FROM users WHERE email=?", User.class);
            nativeQuery.setParameter(1, email);
            User user = nativeQuery.uniqueResult();
            System.out.println(user);
            session.getTransaction().commit();

        }
    }
}
