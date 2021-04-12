package com.utwente.ratefy.QuestionnaireService.models;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionnaireMapper {
    QuestionnaireMapper INSTANCE = Mappers.getMapper(QuestionnaireMapper.class);

    QuestionnaireDto toDto(Questionnaire questionnaire);

    List<QuestionnaireDto> toDTOs(List<Questionnaire> questionnaires);

    Questionnaire toEntity(QuestionnaireDto questionnaireDto);
}