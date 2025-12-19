package org.example.teahouse.tea.observation;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.Observation;
import org.example.teahouse.core.observation.AbstractMidiObservationHandler;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.http.server.observation.ServerRequestObservationContext;
import org.springframework.stereotype.Component;

@Component
@Profile("midi")
public class PercussionObservationHandler extends AbstractMidiObservationHandler<ServerRequestObservationContext> {

    private final MidiChannel percussion;

    private final MidiChannel frenchHorn;

    public PercussionObservationHandler(MeterRegistry registry) throws MidiUnavailableException {
        super(registry);
        // ch #9 is for percussion
        this.percussion = this.synthesizer.getChannels()[9];
        this.frenchHorn = this.synthesizer.getChannels()[0];
        // French Horn is bank #0 preset #60
        this.frenchHorn.programChange(synthesizer.getAvailableInstruments()[60].getPatch().getProgram());
    }

    @Override
    protected @NonNull Note contextToNote(@NonNull ServerRequestObservationContext context) {
        long duration = duration(context);
        if (context.getError() != null) {
            // play C4 (middle C)
            return new Note(this.frenchHorn, 60, 90, duration);
        }
        else {
            // play something on a random percussion
            // from #35 to #52; not every percussion is available in Java
            return new Note(this.percussion, randomNote(35, 53), 64, duration);
        }
    }

    @Override
    public boolean supportsContext(Observation.@NonNull Context context) {
        return context instanceof ServerRequestObservationContext;
    }
}
