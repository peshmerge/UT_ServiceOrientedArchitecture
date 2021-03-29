package com.utwente.ratefy.RewardService.services;

import com.utwente.ratefy.RewardService.models.Reward;
import com.utwente.ratefy.RewardService.repositories.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardService implements IRewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Override
    public List<Reward> findAll() {
        return rewardRepository.findAll();
    }

    @Override
    public Optional<Reward> findById(Integer id) {
        return rewardRepository.findById(id);
    }

    @Override
    public Reward save(Reward reward) {
        return rewardRepository.save(reward);
    }

    @Override
    public void deleteById(Integer id) {
        rewardRepository.deleteById(id);
    }
}
