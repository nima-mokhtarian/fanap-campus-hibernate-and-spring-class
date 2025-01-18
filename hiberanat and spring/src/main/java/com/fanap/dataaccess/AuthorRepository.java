package com.fanap.dataaccess;

import com.fanap.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    @Query(value = "from Author au where au.firstName like :firstName%")
    List<Author> findAuthorsWithFirstNameLike(String firstName);


    List<Author> findAuthorsByFirstNameStartingWith(String firstName);
}
