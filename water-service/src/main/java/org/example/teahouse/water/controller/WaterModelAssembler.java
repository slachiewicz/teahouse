package org.example.teahouse.water.controller;

import org.example.teahouse.water.api.WaterModel;
import org.example.teahouse.water.repo.Water;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;
import static org.springframework.hateoas.IanaLinkRelations.COLLECTION;
import static org.springframework.hateoas.IanaLinkRelations.SEARCH;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WaterModelAssembler extends RepresentationModelAssemblerSupport<Water, RepresentationWaterModel> {
    private final EntityLinks links;

    public WaterModelAssembler(EntityLinks entityLinks) {
        super(WaterController.class, RepresentationWaterModel.class);
        this.links = entityLinks;
    }

    @Override
    protected RepresentationWaterModel instantiateModel(Water water) {
        return water.toRepresentationWaterModel();
    }

    @Override
    public RepresentationWaterModel toModel(Water water) {
        return createModelWithId(requireNonNull(water.getId()), water)
            .add(linkTo(methodOn(WaterController.class).findBySize(water.getSize())).withRel(SEARCH))
            .add(links.linkToCollectionResource(WaterModel.class).withRel(COLLECTION));
    }
}
