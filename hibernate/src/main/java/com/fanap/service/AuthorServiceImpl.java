package com.fanap.service;

import com.fanap.model.Author;
import com.fanap.util.SFUtil;
import org.hibernate.Hibernate;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    @Override
    public List<Author> findAll() {
        return SFUtil.runHibernateCode(s -> s.createQuery("from Author au join fetch au.books", Author.class).list(), true);
    }

    @Override
    public List<Author> findAll(int from, int size) {
        return SFUtil.runHibernateCode(s -> {
            List<Author> authorList = s.createQuery("from Author", Author.class)
                    .setFirstResult(from)
                    .setMaxResults(size)
                    .list();
            authorList.forEach(a -> Hibernate.initialize(a.getBooks()));
            return authorList;
        }, true);
    }


    @Override
    public List<String> findAllHavingMoreThanOneBook() {
        return SFUtil.runHibernateCode(s -> s.createQuery("select au.firstName from Author au join au.books group by au.id having count(1) > 1", String.class).list(), true);
    }

    @Override
    public List<String> findAllHavingAtMostOneBook() {
        return SFUtil.runHibernateCode(s -> s.createQuery("select au.firstName from Author au left join au.books group by au.id having count(1) <= 1", String.class).list(), true);
    }

    @Override
    public Author findById(long id) {
        return SFUtil.runHibernateCode(s -> s.get(Author.class, id), true);
    }

    @Override
    public List<Author> findByFirstName(String firstName) {
        String query = "from Author au join fetch au.books b where au.firstName = :firstName";
        return SFUtil.runHibernateCode(s -> s.createQuery(query, Author.class)
                .setParameter("firstName", firstName)
                .list(), true);
    }

    @Override
    public List<Author> findByLastName(String lastName) {
        String query = "from Author au join fetch au.books b where au.lastName = ?1";
        return SFUtil.runHibernateCode(s -> s.createQuery(query, Author.class)
                .setParameter(1, lastName)
                .list(), true);
    }

    @Override
    public void save(Author author) {
        SFUtil.runHibernateCode(s -> {
            s.persist(author);
            return null;
        }, true);
    }

    @Override
    public void update(Author author) {
        SFUtil.runHibernateCode(s -> s.merge(author), true);
    }

    @Override
    public void delete(Author author) {
        SFUtil.runHibernateCode(s -> {
            s.remove(author);
            return null;
        }, true);
    }


}
