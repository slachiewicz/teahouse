package org.example.teahouse.tea.service;

import io.micrometer.common.KeyValues;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class DefaultMakeTeaConvention implements MakeTeaConvention {

    @Override
    public @Nullable String getName() {
        return "make.tea";
    }

    @Override
    public @NonNull KeyValues getLowCardinalityKeyValues(MakeTeaContext context) {
//        return KeyValues.of(
//            "tea.name", context.getTeaName(),
//            "water.size", context.getWaterSize()
//        );

        return KeyValues.of(
            MakeTeaDocumentation.LowCardinalityKeyNames.TEA_LEAF_NAME.withValue(context.getTeaName()),
            MakeTeaDocumentation.LowCardinalityKeyNames.WATER_SIZE.withValue(context.getWaterSize())
        );
    }
}
