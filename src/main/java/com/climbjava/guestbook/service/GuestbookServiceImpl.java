package com.climbjava.guestbook.service;

import com.climbjava.guestbook.dto.GuestbookDTO;
import com.climbjava.guestbook.dto.PageRequestDTO;
import com.climbjava.guestbook.dto.PageResponseDTO;
import com.climbjava.guestbook.entity.GuestBook;
import com.climbjava.guestbook.entity.QGuestBook;
import com.climbjava.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Log4j2
@Transactional
public class GuestbookServiceImpl implements GuestbookService {

  private final GuestbookRepository repository; // 유일변수

  public Long write(GuestbookDTO guestbookDTO) {
    return repository.save(toEntity(guestbookDTO)).getGno();
  }

  public GuestbookDTO read(Long gno) {
    GuestbookDTO gDto = toDto(repository.findById(gno).orElseThrow(() -> new RuntimeException("올바른 글번호를 입력하여 주십시오.")));
    return gDto;
  }

  @Transactional(readOnly = true)
  public List<GuestbookDTO> readAll() {
    log.info("repository.findAll() :: {}",repository.findAll());
    log.info("repository.findAll().stream() :: {}", repository.findAll().stream());
    log.info("repository.findAll().stream().map(this::toDto) :: {}", repository.findAll().stream().map(this::toDto));
    log.info("repository.findAll().stream().map(this::toDto).toList() :: {}", repository.findAll().stream().map(this::toDto).toList());
    return repository.findAll(Sort.by(Sort.Direction.DESC, "gno")).stream().map(this::toDto).toList();
  }

  @Override
  public PageResponseDTO<GuestbookDTO, GuestBook> getList(PageRequestDTO pageRequestDTO) {
    BooleanBuilder getSearch = getSearch(pageRequestDTO);
    PageResponseDTO<GuestbookDTO, GuestBook> responseDTO = null;
    return new PageResponseDTO<>(repository.findAll(getSearch, pageRequestDTO.getPageable(Sort.by(Sort.Direction.DESC, "gno"))), this :: toDto);
  }

  public void modify(GuestbookDTO guestbookDTO) {
    repository.save(toEntity(guestbookDTO));
  }

  public void remove(Long gno) {
    repository.deleteById(gno);
  }

  public BooleanBuilder getSearch(PageRequestDTO dto) {
    String type = dto.getType();
    BooleanBuilder builder = new BooleanBuilder();
    QGuestBook guestBook = QGuestBook.guestBook;
    String keyword = dto.getKeyword();
    BooleanExpression expression = guestBook.gno.gt(0L);
    builder.and(expression);

    if(type == null || type.trim().isEmpty()) {
      return builder;
    }
    BooleanBuilder condition = new BooleanBuilder();

    if(type.contains("t")){
      condition.or(guestBook.title.contains(keyword));
    }
    if(type.contains("c")){
      condition.or(guestBook.content.contains(keyword));
    }
    if(type.contains("w")){
      condition.or(guestBook.writer.contains(keyword));
    }
    builder.and(condition);
    return builder;
  }
}
