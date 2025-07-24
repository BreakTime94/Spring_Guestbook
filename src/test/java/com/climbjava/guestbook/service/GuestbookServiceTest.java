package com.climbjava.guestbook.service;

import com.climbjava.guestbook.dto.GuestbookDTO;
import com.climbjava.guestbook.dto.PageRequestDTO;
import com.climbjava.guestbook.dto.PageResponseDTO;
import com.climbjava.guestbook.repository.GuestbookRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

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
    Long gno = 105L;
    GuestbookDTO gDto = service.read(gno);
    GuestbookDTO expect = GuestbookDTO.builder().title("제에모옥").content("내애요옹").writer("작성자").build();
    Assertions.assertEquals(gDto.getTitle(), expect.getTitle());
    Assertions.assertEquals(gDto.getContent(), expect.getContent());
    Assertions.assertEquals(gDto.getWriter(), expect.getWriter());
  }

  @Test
  public void testReadAll() {
    service.readAll().forEach(log :: info);
  }

  @Test
  public void testGetList(){
   PageResponseDTO<?, ?> dto = service.getList(PageRequestDTO.builder().page(2).size(5).build());
   log.info(dto);
  }

  @Test
  @Transactional
  @Commit
  public void testModify() {
    Long gno = 105L;
    GuestbookDTO gDto = service.read(gno);
    gDto.setContent("수정내용");
    service.modify(gDto);
  }

  @Test
  public void testDelete() {
    service.remove(103L);
  }

  @Test
  public void querryDSLTest() {
    PageRequestDTO dto = PageRequestDTO.builder().page(1).size(5).keyword("0").type("tc").build();
    service.getList(dto);
  }



}
