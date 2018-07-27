package discovery.portal.msrv.vaEventStorming.application;

import discovery.portal.msrv.vaEventStorming.application.events.MessageInputCommandReceivedEvent;
import discovery.portal.msrv.vaEventStorming.application.events.NlpResponseReceivedEvent;

public interface ApplicationEvent {

    void messageInputCommandReceived(MessageInputCommandReceivedEvent event);
    void nlpResponseReceived(NlpResponseReceivedEvent event);
}
