package org.example.teahouse.water.api;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class SimpleWaterModel implements WaterModel {
    private final UUID id;
    private final String size;
    private final String amount;
}
