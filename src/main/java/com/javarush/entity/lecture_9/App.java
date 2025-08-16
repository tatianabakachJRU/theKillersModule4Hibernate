package com.javarush.entity.lecture_9;

import com.javarush.entity.lecture_10.examplesHQL.HQLExample_1;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()){
            Session session = sessionFactory.openSession();

            Transaction transaction = null;

            try{
                transaction = session.beginTransaction();
                // Создание пользователя
                User karina = new User();
                karina.setUsername("Karina Petrova");
                karina.setEmail("karina.petrova@gmail.com");

                // Создание профиля
                Profile profile = new Profile("С# - разработчик, которая любит игры",
                        LocalDate.of(1999, 8, 10),
                        new BigDecimal("167.7"));
                profile.setIsActive(true);

                karina.setProfile(profile);

                session.persist(karina);

                transaction.commit();
                System.out.println("Пользователь создан в базе");
            } catch (Exception e){
                if(transaction != null) transaction.rollback();
            } finally {
                session.close();
            }

            try(Session session2 = sessionFactory.openSession()){
                User user = session2.get(User.class, 1);
                System.out.println(user);
                if(user.getProfile() != null){
                    System.out.println(user.getProfile());
                }
            }

            HQLExample_1 hqlExample1 = new HQLExample_1(sessionFactory);
            hqlExample1.getAllUsers();
            hqlExample1.getUserNames();
            hqlExample1.processUsersAsStream();
            hqlExample1.activateProfileByEmailDomain("@example.com");
            hqlExample1.deactivateProfileByEmail("sofia.chen@example.com");
            hqlExample1.goToUserByPosition(5);
            hqlExample1.getBioProfilesByUsername("Anna Petrova");
            ArrayList<String> names = new ArrayList<>();
            names.add("Anna Petrova");
            names.add("Alexei Volkov");
            hqlExample1.findUserByUsernameList(names);
            hqlExample1.getUsersOrderByUsername();
            hqlExample1.getMaxHeight();
            hqlExample1.getUserByEmailNativeQuery("anna.petrova@example.com");

        }
    }
}
