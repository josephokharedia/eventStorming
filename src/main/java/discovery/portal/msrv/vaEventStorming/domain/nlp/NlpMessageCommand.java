package discovery.portal.msrv.vaEventStorming.domain.nlp;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class NlpMessageCommand {

    String message;
    Object context;
}
