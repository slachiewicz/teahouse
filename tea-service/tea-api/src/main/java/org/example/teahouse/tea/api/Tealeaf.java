package org.example.teahouse.tea.api;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class Tealeaf {
    String name;
    String type;
    String amount;
}
