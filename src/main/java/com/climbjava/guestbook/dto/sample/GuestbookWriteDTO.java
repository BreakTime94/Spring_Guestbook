package com.climbjava.guestbook.dto.sample;

import com.climbjava.guestbook.entity.GuestBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GuestbookWriteDTO {
  private Long gno;
  private String title;
  private String content;
  private String writer;

  public GuestbookWriteDTO(GuestBook guestBook) {
    this.gno = guestBook.getGno();
    this.title = guestBook.getTitle();
    this.content = guestBook.getContent();
    this.writer = guestBook.getWriter();
  }

  public GuestBook toEntity() {
    return GuestBook.builder().content(content).title(title).writer(writer).build();
  }

}
