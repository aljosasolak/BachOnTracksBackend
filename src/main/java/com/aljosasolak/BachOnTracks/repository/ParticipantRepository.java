package com.aljosasolak.BachOnTracks.repository;

import com.aljosasolak.BachOnTracks.model.Participant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends BaseRepository<Participant, String>{

    @Query(value = "SELECT * FROM bach_on_tracks.participants WHERE project_id = :id ORDER BY lastname", nativeQuery = true)
    public List<Participant> filterByProjectId(String id);
}