package com.climbjava.guestbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_guestbook")
@Getter
@Builder()
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GuestBook extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long gno;

  @Column(length = 100, nullable = false)
  private String title;

  @Column(length = 1500, nullable = false)
  private String content;

  @Column(length = 50, nullable = false)
  private String writer;

}
