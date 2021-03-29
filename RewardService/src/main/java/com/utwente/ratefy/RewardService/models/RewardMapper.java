package com.utwente.ratefy.RewardService.models;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RewardMapper {
    RewardMapper INSTANCE = Mappers.getMapper(RewardMapper.class);

    RewardDto toDto(Reward reward);

    List<RewardDto> toDTOs(List<Reward> rewards);

    Reward toEntity(RewardDto rewardDto);
}