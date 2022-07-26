package ru.redcollar.summer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;
import ru.redcollar.summer.entity.Guest;
import ru.redcollar.summer.entity.Speaker;
import ru.redcollar.summer.entity.SpeakerToTalk;
import ru.redcollar.summer.entity.Talk;
import ru.redcollar.summer.repository.GuestRepository;
import ru.redcollar.summer.repository.SpeakerRepository;
import ru.redcollar.summer.repository.spec.GuestSpecificationBuilder;

import java.util.List;

@SpringBootTest
class RCSummerApplicationTests {

    @Autowired
    private SpeakerRepository speakerRepository;

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnAllSpeakers() {
        Iterable<Speaker> speakers = speakerRepository.findAll();

        speakers.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnAllSpeakersSorted() {
        Sort sortByName = Sort.by(Sort.Direction.ASC, "name");
        Iterable<Speaker> speakers = speakerRepository.findAll(sortByName);

        speakers.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnAllSpeakersPaged() {
        Sort sortByName = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(0, 2, sortByName);
        Page<Speaker> speakers = speakerRepository.findAll(pageable);

        System.out.println("TOTAL ELEMENTS = " + speakers.getTotalElements());

        speakers.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnAllSpeakersSliced() {
        Sort sortByName = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(0, 2, sortByName);
        Slice<Speaker> speakers = speakerRepository.findAll(pageable);

        System.out.println("PREVIOUS = " + speakers.hasPrevious());
        System.out.println("NEXT = " + speakers.hasNext());

        speakers.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnSpeakersByName() {
        List<Speaker> speakers = speakerRepository.findAllByName("Gleb Akkuratnov");

        speakers.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnSpeakersByNameLike() {
        List<Speaker> speakers = speakerRepository.findПожалуйстаAllByNameLikeIgnoreCase("%gleb%");

        speakers.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnSpeakersByExample() {
        Speaker speaker = new Speaker();
        speaker.setName("gleb akkuratnov");
        Example<Speaker> speakerExample = Example.of(speaker, ExampleMatcher.matchingAny().withIgnoreCase());

        Iterable<Speaker> speakers = speakerRepository.findAll(speakerExample);

        speakers.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnSpeakersByQuery() {
        speakerRepository.findById(1L).ifPresent(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnSpeakersByIdIn() {
        List<Speaker> speakers = speakerRepository.findByIdIn(List.of(1L, 2L));
        speakers.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnSpeakersStrings() {
        List<String> speakers = speakerRepository.findAllToString();
        speakers.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnSpeakersTalks() {
        List<Talk> speakers = speakerRepository.findSpeakersTalks();
        speakers.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void shouldReturnSpeakersToTalks() {
        List<SpeakerToTalk> speakers = speakerRepository.findSpeakersToTalk();
        speakers.forEach(System.out::println);
    }

    @Test
    public void shouldCustom() {
        speakerRepository.doSomeCool();
    }

    @Autowired
    private GuestRepository guestRepository;

    @Test
    @Sql("classpath:db/guests.sql")
    public void shouldReturnGuests() {
        Specification<Guest> guestSpecification = GuestSpecificationBuilder.build("%jon%", null, null, null);
        List<Guest> speakers = guestRepository.findAll(guestSpecification);
        speakers.forEach(System.out::println);
    }
}
