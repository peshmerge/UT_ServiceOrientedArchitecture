package com.utwente.ratefy.RewardService.repositories;

import com.utwente.ratefy.RewardService.models.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RewardRepository extends JpaRepository<Reward, Integer> {
}
