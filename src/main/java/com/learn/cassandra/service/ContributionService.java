package com.learn.cassandra.service;

import com.learn.cassandra.model.Contribution;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContributionService {

    List<Contribution> getAllContributions();

    List<Contribution> findByCandidateContaining(String candidate);

    Optional<Contribution> findContributionById(UUID id);

    Contribution saveContribution(Contribution contribution);

    Object updateContribution(Contribution contribution);

    void deleteContributionById(UUID id);

}
