package discovery.portal.msrv.vaEventStorming.interfaces.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Getter
@RequiredArgsConstructor
public class MessageInputCommand {

    String label;
    String value;
}
