package com.umc.week4.service;

import com.umc.week4.domain.Book;
import com.umc.week4.domain.Category;
import com.umc.week4.dto.BookResponse;
import com.umc.week4.dto.CreateBookRequest;
import com.umc.week4.repository.BookJdbcRepository;
import com.umc.week4.repository.BookRepository;
import com.umc.week4.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    // 아직 ORM으로 옮기지 않은 기능(카테고리별 조회)이 사용하는 3주차 Raw SQL Repository
    private final BookJdbcRepository bookJdbcRepository;

    @Transactional(readOnly = true)
    public List<BookResponse> getBooks() {
        return bookRepository.findAllByOrderByBookIdDesc().stream()
                .map(BookResponse::from)
                .toList();
    }

    public List<Map<String, Object>> getBooksByCategory(Long categoryId) {
        return bookJdbcRepository.findByCategoryId(categoryId);
    }

    @Transactional
    public BookResponse createBook(CreateBookRequest request) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        Book book = new Book(category, request.title(), request.description());
        return BookResponse.from(bookRepository.save(book));
    }
}
