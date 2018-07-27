package discovery.portal.msrv.vaEventStorming.domain.nlp;

public interface NlpProvider {

    NlpResponse message(NlpMessageCommand command);
}
