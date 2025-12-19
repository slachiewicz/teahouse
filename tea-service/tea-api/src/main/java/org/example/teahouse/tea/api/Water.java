package org.example.teahouse.tea.api;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class Water {
    String amount;
    String temperature;
}
