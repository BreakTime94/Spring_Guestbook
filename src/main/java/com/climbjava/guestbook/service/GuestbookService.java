package com.climbjava.guestbook.service;

import com.climbjava.guestbook.dto.GuestbookDTO;
import com.climbjava.guestbook.entity.GuestBook;

import java.util.List;

public interface GuestbookService {
  Long write(GuestbookDTO guestbookDTO); // 등록
  GuestbookDTO read(Long gno); //단일 조회
  List<GuestbookDTO> readAll(); //전체 조회
  int modify(GuestbookDTO guestbookDTO); //수정
  int remove (Long gno); // 삭제

  default GuestBook toEntity(GuestbookDTO guestbookDTO) {
    return GuestBook.builder()
            .gno(guestbookDTO.getGno())
            .title(guestbookDTO.getTitle())
            .content(guestbookDTO.getContent())
            .writer(guestbookDTO.getWriter())
            .build();

  }
  default GuestbookDTO toDto(GuestBook guestBook) {
    return GuestbookDTO.builder()
            .gno(guestBook.getGno())
            .title(guestBook.getTitle())
            .content(guestBook.getContent())
            .writer(guestBook.getWriter())
            .regDate(guestBook.getRegDate())
            .modDate(guestBook.getModDate())
            .build();
  }
}
