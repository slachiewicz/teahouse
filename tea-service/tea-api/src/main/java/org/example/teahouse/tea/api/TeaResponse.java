package org.example.teahouse.tea.api;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class TeaResponse {
    Water water;
    Tealeaf tealeaf;
    String steepingTime;
}
