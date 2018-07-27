package discovery.portal.msrv.vaEventStorming.domain.messaging;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Value
@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class MessageInput {

    @NonNull
    @NonFinal
    String message;

    void passThroughFilters(List<MessageInputFilter> filters) {

        MessageInput finalMessageInput = this;
        for (MessageInputFilter filter : filters) {
            log.info(">>> {} filtering messageInput", filter.getClass().getName());
            finalMessageInput = filter.doFilter(finalMessageInput);
        }

        merge(finalMessageInput);

    }

    void merge(MessageInput messageInput) {
        this.message = messageInput.message;
    }
}
