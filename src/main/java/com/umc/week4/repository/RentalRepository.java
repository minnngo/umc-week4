package com.umc.week4.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RentalRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Map<String, Object> body){
        // rental_id는 AUTO_INCREMENT이므로 생략, returned_at은 반납 전이므로 기본값 NULL
        // rented_at은 현재 시간, due_at은 7일 뒤로 DB가 직접 계산합니다.
        String sql = "INSERT INTO rental (user_id, book_id, rented_at, due_at) VALUES (?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY))";

        jdbcTemplate.update(
                sql,
                body.get("userId"),
                body.get("bookId")
        );
    }

    public void updateReturnedAt(Long rentalId){
        // 해당 대여 기록의 반납일을 현재 시간으로 갱신합니다.
        String sql = "UPDATE rental SET returned_at = NOW() WHERE rental_id = ?";

        jdbcTemplate.update(sql, rentalId);
    }
}
