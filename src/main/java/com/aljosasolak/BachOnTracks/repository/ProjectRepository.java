package com.aljosasolak.BachOnTracks.repository;

import com.aljosasolak.BachOnTracks.model.Participant;
import com.aljosasolak.BachOnTracks.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProjectRepository extends BaseRepository<Project, String> {

    @Query(value = "SELECT * FROM bach_on_tracks.projects WHERE user_id = :id", nativeQuery = true)
    public List<Project> filterByUserId(String id);
}
