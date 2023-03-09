package fr.wcs.springrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.wcs.springrest.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
   List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
}
