package org.example.teahouse.tea.observation;

import feign.micrometer.FeignContext;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.Observation;
import org.example.teahouse.core.observation.AbstractMidiObservationHandler;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;

@Component
@Profile("midi")
public class BassObservationHandler extends AbstractMidiObservationHandler<FeignContext> {

    private final MidiChannel bass;

    public BassObservationHandler(MeterRegistry registry) throws MidiUnavailableException {
        super(registry);
        this.bass = synthesizer.getChannels()[0];
        // Slap Bass 1 is bank #0 preset #36
        this.bass.programChange(synthesizer.getAvailableInstruments()[36].getPatch().getProgram());
    }

    @Override
    protected @NonNull Note contextToNote(@NonNull FeignContext context) {
        // C major pentatonic scale from E2 on the bass: E2 (40), G2(43), A2 (45), C3 (48), D3 (50), E3 (52)
        int[] notes = {40, 43, 45, 48, 50, 52};
        return new Note(this.bass, randomNote(notes), 64, duration(context));
    }

    @Override
    public boolean supportsContext(Observation.@NonNull Context context) {
        // Only for water-service, there is also tealeaf-service
        return context instanceof FeignContext feignContext
            && feignContext.getCarrier() != null
            && feignContext.getCarrier().url().contains("/waters/");
    }
}
