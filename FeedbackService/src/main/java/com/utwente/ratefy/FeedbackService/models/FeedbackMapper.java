package com.utwente.ratefy.FeedbackService.models;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

    FeedbackDto toDto(Feedback feedback);

    List<FeedbackDto> toDTOs(List<Feedback> feedbacks);

    Feedback toEntity(FeedbackDto feedbackDto);
}