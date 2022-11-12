package com.learn.cassandra.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
public class Contribution {

    @PrimaryKey
    private UUID id;
    private String comment;
    private double  amount;
    private String candidate;
    private  UUID contributorId;

    public Contribution(UUID id, String comment, double amount, String candidate, UUID contributorId) {
        this.id = id;
        this.comment = comment;
        this.amount = amount;
        this.candidate = candidate;
        this.contributorId = contributorId;
    }

    public Contribution() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public UUID getContributorId() {
        return contributorId;
    }

    public void setContributorId(UUID contributorId) {
        this.contributorId = contributorId;
    }

    @Override
    public String toString() {
        return "Contribution{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", amount=" + amount +
                ", candidate='" + candidate + '\'' +
                ", contributorId=" + contributorId +
                '}';
    }
}
