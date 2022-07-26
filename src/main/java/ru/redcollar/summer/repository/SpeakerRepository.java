package ru.redcollar.summer.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import ru.redcollar.summer.entity.Speaker;
import ru.redcollar.summer.entity.SpeakerToTalk;
import ru.redcollar.summer.entity.Talk;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SpeakerRepository extends
        CrudRepository<Speaker, Long>,
        PagingAndSortingRepository<Speaker, Long>,
        QueryByExampleExecutor<Speaker>,
        CustomRepository {

    List<Speaker> findAllByName(String name);

    List<Speaker> findПожалуйстаAllByNameLikeIgnoreCase(String s);

    List<Speaker> findAllByNameLike(String name, Pageable pageable);

    @Query("select speaker " +
            "from Speaker speaker " +
            "where speaker.id = :id")
    Optional<Speaker> findById(Long id);

    @Query("select speaker " +
            "from Speaker speaker " +
            "where speaker.id in (:ids)")
    List<Speaker> findByIdIn(Collection<Long> ids);

    @Query("select concat(speaker.id, ', ', speaker.name) " +
            "from Speaker speaker")
    List<String> findAllToString();

    @Query("select talks " +
            "from Speaker speaker " +
            "left join speaker.talks talks")
    List<Talk> findSpeakersTalks();

    @Query("select new ru.redcollar.summer.entity.SpeakerToTalk(speaker, count(talks)) " +
            "from Speaker speaker " +
            "left join speaker.talks talks " +
            "group by speaker")
    List<SpeakerToTalk> findSpeakersToTalk();
}
