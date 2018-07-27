package discovery.portal.msrv.vaEventStorming.application.events;

import discovery.portal.msrv.vaEventStorming.interfaces.commands.MessageInputCommand;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class MessageInputCommandReceivedEvent {

    MessageInputCommand command;
}
