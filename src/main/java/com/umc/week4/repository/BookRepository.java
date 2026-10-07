package com.umc.week4.repository;

import com.umc.week4.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByBookIdDesc();

    // 제목에 keyword가 포함된 책을 최신순으로 조회 (WHERE title LIKE '%keyword%' ORDER BY book_id DESC)
    List<Book> findByTitleContainingOrderByBookIdDesc(String keyword);
}
