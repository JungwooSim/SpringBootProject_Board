package com.themoim.board.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReferenceRepositoryTest {
    @Autowired
    ReferenceRepository referenceRepository;

    @Test
    public void create() {
        Reference reference = Reference.builder().title("title").content("content").writtenBy(1L).build();

        Reference referenceSave = referenceRepository.save(reference);

        System.out.println(referenceSave.getModifiedAt());
        System.out.println(referenceSave.getCreatedAt());
    }
}
