package org.example.teahouse.tea.service;

import org.example.teahouse.tea.api.TeaResponse;
import org.example.teahouse.tealeaf.api.SimpleTealeafModel;
import org.example.teahouse.water.api.SimpleWaterModel;

import java.util.Collection;

public interface TeaService {
    TeaResponse make(String name, String size);

    Collection<SimpleTealeafModel> tealeaves();

    Collection<SimpleWaterModel> waters();
}
