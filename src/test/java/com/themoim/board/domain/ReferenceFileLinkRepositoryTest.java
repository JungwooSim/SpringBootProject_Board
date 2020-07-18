package com.themoim.board.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReferenceFileLinkRepositoryTest {
    @Autowired
    ReferenceFileLinkRepository referenceFileLinkRepository;

    @Autowired
    ReferenceRepository referenceRepository;

    @Test
    public void insert() {
        // given
        Reference reference = Reference
                .builder()
                .title("title")
                .content("content")
                .writtenBy(1L)
                .build();

        List<HashMap<String, String>> files = new ArrayList();

        files.add(
                new HashMap<String, String>() {
                    {
                        put("filePath","/Users/bigpenguin/file/test1.xls");
                    }
                }
        );
        files.add(
                new HashMap<String, String>() {
                    {
                        put("filePath","/Users/bigpenguin/file/test2.xls");
                    }
                }
        );
        files.stream().forEach(file -> {});
        List<ReferenceFileLink> referenceFileLink = new ArrayList<>();

        files.forEach(
                file -> {
                    referenceFileLink.add(
                            ReferenceFileLink.builder().link(file.get("filePath")).reference(reference).build()
                    );
            }
        );

        // when
        referenceRepository.save(reference);
        referenceFileLinkRepository.saveAll(referenceFileLink);

        List<ReferenceFileLink> referenceFileLinks = referenceFileLinkRepository.findAll();

        // then
        assertThat(referenceFileLinks.get(0).getReference().getRId()).isEqualTo(1L);
    }

}
