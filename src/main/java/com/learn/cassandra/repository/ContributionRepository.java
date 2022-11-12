package com.learn.cassandra.repository;

import com.learn.cassandra.model.Contribution;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface ContributionRepository extends CassandraRepository <Contribution, UUID> {

    List<Contribution> findByCandidateContaining(String candidate);

}
