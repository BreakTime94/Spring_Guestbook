package com.climbjava.guestbook.service;

import com.climbjava.guestbook.dto.GuestbookDTO;
import com.climbjava.guestbook.repository.GuestbookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Log4j2
@Transactional
public class GuestbookServiceImpl implements GuestbookService {

  private GuestbookRepository repository;

  public Long write(GuestbookDTO guestbookDTO) {
    return repository.save(toEntity(guestbookDTO)).getGno();
  }

  public GuestbookDTO read(Long gno) {
    GuestbookDTO gDto = toDto(repository.findById(gno).orElseThrow(() -> new RuntimeException("올바른 글번호를 입력하여 주십시오.")));
    return gDto;
  }

  public List<GuestbookDTO> readAll() {
    log.info("repository.findAll() :: {}",repository.findAll());
    log.info("repository.findAll().stream() :: {}", repository.findAll().stream());
    log.info("repository.findAll().stream().map(this::toDto) :: {}", repository.findAll().stream().map(this::toDto));
    log.info("repository.findAll().stream().map(this::toDto).toList() :: {}", repository.findAll().stream().map(this::toDto).toList());
    return repository.findAll().stream().map(this::toDto).toList();
  }

  public int modify(GuestbookDTO guestbookDTO) {
    repository.save(toEntity(guestbookDTO));
    return repository.existsById(guestbookDTO.getGno()) ? 1 : 0;
  }

  public int remove(Long gno) {
    repository.deleteById(gno);
    return repository.existsById(gno) ? 0 : 1;
//    try{
//      repository.deleteById(gno);
//      return 1;
//    } catch (Exception e){
//      return 0;
//    }
  }
}
