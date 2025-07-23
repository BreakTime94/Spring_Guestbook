package com.climbjava.guestbook.repository;

import com.climbjava.guestbook.entity.GuestBook;
import com.climbjava.guestbook.entity.QGuestBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class GuestbookRepositoryTest {
  @Autowired
  private GuestbookRepository guestbookRepository;

  @Test
  public void testExist(){
    log.info(guestbookRepository);
  }

  @Test
  public void testInsert(){
    //guestRepository.save(GuestBook.builder().title("QueryDSL").content("어렵다").writer("어렵다").build());

    LongStream.rangeClosed(1, 100).forEach( i -> {
      guestbookRepository.save(GuestBook.builder().title("제목" + i % 10).content("어렵다" + (i + 5) % 10).writer("찐으로").build());
    });
  }

  @Test
  public void testQuerydsl() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

    QGuestBook qGuestBook = QGuestBook.guestBook; //1

    String keyword = "0";

    BooleanBuilder builder = new BooleanBuilder(); //2

    BooleanExpression expression = qGuestBook.title.contains(keyword); //3

    builder.and(expression); //4

    Page<GuestBook> result = guestbookRepository.findAll(builder, pageable); //5

    result.stream().forEach(guestbook -> {
      log.info(guestbook);
    });
  }

  @Test
  public void testQuerydsl2() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

    QGuestBook qGuestBook = QGuestBook.guestBook; //1

    String keyword = "1";
    String content = "0";

    BooleanBuilder builder = new BooleanBuilder(); //2

    BooleanExpression expression1 = qGuestBook.title.contains(keyword); //3
    BooleanExpression expression2 = qGuestBook.content.contains(content); //3

    builder.and(expression1).or(expression2); //4

    Page<GuestBook> result = guestbookRepository.findAll(builder, pageable); //5

    result.stream().forEach(guestbook -> {
      log.info(guestbook);
    });
  }

  @Test
  public void testQuerydsl3() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

    QGuestBook qGuestBook = QGuestBook.guestBook; //1

    String keyword = "1";
    String content = "0";

    BooleanBuilder builder = new BooleanBuilder(); //2

    BooleanExpression expression1 = qGuestBook.title.contains(keyword); //3
    BooleanExpression expression2 = qGuestBook.content.contains(content); //3

    builder.andAnyOf(expression1, expression2); //4

    Page<GuestBook> result = guestbookRepository.findAll(builder, pageable); //5

    result.stream().forEach(guestbook -> {
      log.info(guestbook);
    });
  }

  @Test
  public void testQuerydsl4() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

    QGuestBook qGuestBook = QGuestBook.guestBook; //1

    String keyword = "1";
    String content = "0";
    String gno = "50";
    BooleanBuilder builder = new BooleanBuilder(); //2

    BooleanExpression expression1 = qGuestBook.title.contains(keyword); //3
    BooleanExpression expression2 = qGuestBook.content.contains(content); //3
    BooleanExpression expression3 = qGuestBook.gno.gt(75L); //3

    builder.andAnyOf(expression1, expression2).and(expression3); //4

    Page<GuestBook> result = guestbookRepository.findAll(builder, pageable); //5

    result.stream().forEach(guestbook -> {
      log.info(guestbook);
    });
  }
}
