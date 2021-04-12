package com.utwente.ratefy.RewardService.services;

import com.utwente.ratefy.RewardService.models.Reward;

import java.util.List;
import java.util.Optional;

public interface IRewardService {

    List<Reward> findAll();

    Optional<Reward> findById(Integer id);

    Reward save(Reward reward);

    void deleteById(Integer id);
}
