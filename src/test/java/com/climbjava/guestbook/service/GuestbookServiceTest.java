package com.climbjava.guestbook.service;

import com.climbjava.guestbook.dto.GuestbookDTO;
import com.climbjava.guestbook.repository.GuestbookRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class GuestbookServiceTest {
  @Autowired
  private GuestbookServiceImpl service;

  @Test
  public void testExist(){
    log.info(service);
  }

  @Test
  public void testWrite(){
    Long gno = service.write(GuestbookDTO.builder().title("제에모옥").content("내애요옹").writer("작성자").build());
    Assertions.assertNotNull(gno);
    log.info(gno);
  }
  @Test
  public void testRead(){
    GuestbookDTO gDto = service.read(104L);
    Assertions.assertNotNull(gDto);
    log.info(gDto);
  }

  @Test
  public void testList() {
    List<GuestbookDTO> gDtds = service.readAll();
    Assertions.assertNotNull(gDtds);
    log.info(gDtds);
  }

  @Test
  public void testModify() {
    int result = service.modify(GuestbookDTO.builder().gno(104L).title("Spring").content("재밌는데").writer("어려워하는사람").build());
    Assertions.assertEquals(1, result);
    log.info(result);
  }

  @Test
  public void testDelete() {
    int result = service.remove(104L);
    Assertions.assertEquals(1, result);
    log.info(result);
  }


}
