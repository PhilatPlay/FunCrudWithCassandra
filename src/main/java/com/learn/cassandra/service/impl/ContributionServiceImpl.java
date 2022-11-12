package com.learn.cassandra.service.impl;

import com.learn.cassandra.model.Contribution;
import com.learn.cassandra.repository.ContributionRepository;
import com.learn.cassandra.service.ContributionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContributionServiceImpl implements ContributionService {

    public ContributionRepository repository;

    @Override
    public List<Contribution> getAllContributions() {
        return repository.findAll();
    }

    @Override
    public List<Contribution> findByCandidateContaining(String candidate) {
        return repository.findByCandidateContaining(candidate);
    }

    @Override
    public Optional<Contribution> findContributionById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Contribution saveContribution(Contribution contribution) {
        return repository.save(contribution);
    }

    @Override
    public Object updateContribution(Contribution contribution) {
        return repository.save(contribution);
    }

    @Override
    public void deleteContributionById(UUID id) {
        repository.deleteById(id);
    }
}
