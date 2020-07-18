package com.themoim.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "reference_file_link")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceFileLink {

    @Column(name = "rfl_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long rflId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_id", referencedColumnName ="r_id")
    private Reference reference;

    private String link;
}
