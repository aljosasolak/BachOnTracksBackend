package com.aljosasolak.BachOnTracks.repository;

import com.aljosasolak.BachOnTracks.model.Appointment;
import com.aljosasolak.BachOnTracks.model.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends BaseRepository<Appointment, String> {

    @Query(value = "SELECT * FROM bach_on_tracks.appointments WHERE project_id = :id ORDER BY date, starting_time", nativeQuery = true)
    public List<Appointment> filterByProjectId(String id);
}
