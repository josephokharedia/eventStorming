package discovery.portal.msrv.vaEventStorming.application.events;

import discovery.portal.msrv.vaEventStorming.domain.nlp.NlpResponse;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class NlpResponseReceivedEvent {

    NlpResponse nlpResponse;
}
