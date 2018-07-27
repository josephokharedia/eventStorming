package discovery.portal.msrv.vaEventStorming.domain.messaging;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import lombok.experimental.Wither;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Builder(toBuilder = true)
@RequiredArgsConstructor
@Slf4j
public class MessageOutput {

    @Singular
    List<String> messages;
    List<VisualElement> visualElements;
    @Wither(AccessLevel.PACKAGE)
    boolean responseComplete;


    void passThroughFilters(List<MessageOutputFilter> filters) {
        MessageOutput finalMessageOutput = this;
        for (MessageOutputFilter filter : filters) {
            log.info(">>> {} filtering messageOutput", filter.getClass().getName());
            finalMessageOutput = filter.doFilter(finalMessageOutput);

            merge(finalMessageOutput);

            if (this.responseComplete) {
                log.info(">>> {} with responseComplete", filter.getClass().getName());
                break;
            }
        }
    }

    void merge(MessageOutput messageOutput) {
        this.messages = messageOutput.messages;
        this.visualElements = messageOutput.visualElements;
        this.responseComplete = messageOutput.responseComplete;
    }

    void personalize() {

    }
}
