package com.learn.cassandra.controller;


import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.learn.cassandra.model.Contribution;
import com.learn.cassandra.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/contributions")
public class ContributionController {

    @Autowired
    ContributionService service;

    @PostMapping
    public ResponseEntity<Contribution> createContribution(@RequestBody Contribution contribution) {
        try {
            Contribution _contribution = service.saveContribution(
                    new Contribution(
                            Uuids.timeBased(),
                            contribution.getComment(),
                            contribution.getAmount(),
                            contribution.getCandidate(),
                            contribution.getContributorId())
            );
            return new ResponseEntity<>(_contribution, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Contribution>> getAllContributions(@RequestParam(required = false) String candidate){
        try {
            List<Contribution> contributions = new ArrayList<>();

            if (candidate == null) {
                service.getAllContributions().forEach(contributions::add);
            } else {
                service.findByCandidateContaining(candidate).forEach(contributions::add);
            }
            if (contributions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contributions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Contribution> getContributionById(@PathVariable("id") UUID id) {

        Optional<Contribution> contribution = service.findContributionById(id);

        if (contribution.isPresent()) {
            return new ResponseEntity<>(contribution.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Contribution> updateContribution(@PathVariable("id") UUID id,
                                                           @RequestBody Contribution contribution) {
        Optional<Contribution> theContribution = service.findContributionById(id);

        if (theContribution.isPresent()) {
            Contribution _contribution = theContribution.get();
            _contribution.setComment(contribution.getComment());
            _contribution.setAmount(contribution.getAmount());
            _contribution.setCandidate(contribution.getCandidate());
            _contribution.setContributorId(contribution.getContributorId());

            Contribution theNewContribution = (Contribution) service.updateContribution(_contribution);

            return new ResponseEntity<>(theNewContribution, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteContribution(@PathVariable("id") UUID id) {
        try {
            service.deleteContributionById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
