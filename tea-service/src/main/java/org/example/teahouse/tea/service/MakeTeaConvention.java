package org.example.teahouse.tea.service;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;
import org.jspecify.annotations.NonNull;

public interface MakeTeaConvention extends ObservationConvention<MakeTeaContext> {
    @Override
    default boolean supportsContext(Observation.@NonNull Context context) {
        return context instanceof MakeTeaContext;
    }
}
