package org.example.teahouse.tea.service;

import io.micrometer.common.docs.KeyName;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;
import io.micrometer.observation.docs.ObservationDocumentation;
import org.jspecify.annotations.NonNull;

public enum MakeTeaDocumentation implements ObservationDocumentation {
    /**
     * Make some tea.
     */
    MAKE_TEA {
        @Override
        public Class<? extends ObservationConvention<? extends Observation.Context>> getDefaultConvention() {
            return DefaultMakeTeaConvention.class;
        }

        @Override
        public @NonNull KeyName @NonNull [] getLowCardinalityKeyNames() {
            return LowCardinalityKeyNames.values();
        }
    };

    enum LowCardinalityKeyNames implements KeyName {
        /**
         * Name that uniquely identifies a tea-leaf.
         */
        TEA_LEAF_NAME {
            @Override
            public @NonNull String asString() {
                return "tea.name";
            }
        },
        /**
         * Water size (small, medium, large).
         */
        WATER_SIZE {
            @Override
            public @NonNull String asString() {
                return "water.size";
            }
        }
    }
}
