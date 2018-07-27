package discovery.portal.msrv.vaEventStorming.domain.nlp;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class NlpResponse {

    Object context;
    String action;
    @Singular
    List<String> messages;
}
