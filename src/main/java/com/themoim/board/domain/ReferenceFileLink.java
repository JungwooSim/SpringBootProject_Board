package com.themoim.board.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Table(name = "reference_file_link")
@Entity
public class ReferenceFileLink {

    @Column(name = "rfl_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long rflId;

    private String link;

    @Column(name = "r_id")
    private Long rId;
}
