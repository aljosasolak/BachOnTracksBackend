package com.aljosasolak.BachOnTracks.repository;

import com.aljosasolak.BachOnTracks.model.Participant;
import com.aljosasolak.BachOnTracks.model.Project;
import com.aljosasolak.BachOnTracks.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

}
