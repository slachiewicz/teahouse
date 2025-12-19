package org.example.teahouse.water.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.teahouse.water.api.WaterModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
@Relation(collectionRelation = "waters", itemRelation = "water")
public class RepresentationWaterModel extends RepresentationModel<RepresentationWaterModel> implements WaterModel {
    private final UUID id;
    private final String size;
    private final String amount;
}
