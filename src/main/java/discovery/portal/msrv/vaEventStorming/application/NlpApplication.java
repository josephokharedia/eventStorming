package discovery.portal.msrv.vaEventStorming.application;

import discovery.portal.msrv.vaEventStorming.application.events.MessageInputCommandReceivedEvent;
import discovery.portal.msrv.vaEventStorming.domain.messaging.MessageInput;
import discovery.portal.msrv.vaEventStorming.domain.messaging.MessageOutput;
import discovery.portal.msrv.vaEventStorming.domain.messaging.MessageService;
import discovery.portal.msrv.vaEventStorming.interfaces.commands.MessageInputCommand;
import org.springframework.beans.factory.annotation.Autowired;

class NlpApplication {

    @Autowired
    MessageService messageService;
    @Autowired
    ApplicationEvent applicationEvent;


    MessageOutput sendMessage(MessageInputCommand command) {

        //Raise event that message has been received
        //  - interested listeners can pick this up e.g AuditService
        applicationEvent.messageInputCommandReceived(MessageInputCommandReceivedEvent.of(command));

        //Send message to nlp service
        return messageService.sendMessage(MessageInput.of(command.getValue()));
    }
}
