package com.climbjava.guestbook.repository;

import com.climbjava.guestbook.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestbookRepository extends JpaRepository<GuestBook, Long>, QuerydslPredicateExecutor<GuestBook> {
}
