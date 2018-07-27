package discovery.portal.msrv.vaEventStorming.domain.messaging;

import discovery.portal.msrv.vaEventStorming.application.ApplicationEvent;
import discovery.portal.msrv.vaEventStorming.application.events.NlpResponseReceivedEvent;
import discovery.portal.msrv.vaEventStorming.domain.nlp.NlpMessageCommand;
import discovery.portal.msrv.vaEventStorming.domain.nlp.NlpProvider;
import discovery.portal.msrv.vaEventStorming.domain.nlp.NlpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class MessageService {

    @Autowired
    ContextRepository contextRepository;
    @Autowired
    MessageInputFilterRepository pbRepository;
    @Autowired
    ApplicationEvent applicationEvent;
    @Autowired
    FilterRepository filterRepository;
    @Autowired
    NlpProvider nlpProvider;
    @Autowired
    @Qualifier("session-id")
    String sessionId;

    public MessageOutput sendMessage(MessageInput messageInput) {

        //Get action from context that needs to be processed by messageInputFilters
        String action = Optional.ofNullable(getContext().getAction()).orElse(null);

        //Run messageInput through messageInput filters
        List<MessageInputFilter> msgInputFilters = getMessageInputFiltersForAction(action);
        messageInput.passThroughFilters(msgInputFilters);


        //Send message to nlp
        NlpResponse nlpResponse = nlpProvider.message(NlpMessageCommand.of(messageInput.getMessage(), getContext().getPayload()));

        // Raise event that response is received
        applicationEvent.nlpResponseReceived(NlpResponseReceivedEvent.of(nlpResponse));

        //Save a new context from nlpResponse
        saveContext(getContext().toBuilder()
                .action(nlpResponse.getAction())
                .payload(nlpResponse.getContext())
                .build());

        MessageOutput messageOutput = MessageOutput.builder()
                .messages(nlpResponse.getMessages())
                .build();

        //Get action from context that needs to be processed by messageOutputFilters
        action = Optional.ofNullable(getContext().getAction()).orElse(null);

        //Run messageOutput through messageOutput filters
        List<MessageOutputFilter> msgOutputFilters = getMessageOutputFiltersForAction(action);
        messageOutput.passThroughFilters(msgOutputFilters);

        if (messageOutput.responseComplete) {
            String msg = messageOutput.messages.stream().collect(Collectors.joining(""));

            sendMessage(MessageInput.of(msg));
        }

        //Permission aware

        //Personalize
        messageOutput.personalize();

        return messageOutput;
    }

    List<MessageInputFilter> getMessageInputFiltersForAction(String action) {
        if (action == null) return Collections.emptyList();
        return filterRepository.getMessageInputFiltersByAction(action);
    }

    List<MessageOutputFilter> getMessageOutputFiltersForAction(String action) {
        if (action == null) return Collections.emptyList();
        return filterRepository.getMessageOutputFiltersByAction(action);
    }

    Context getContext() {
        return contextRepository.findBySessionId(sessionId).orElse(Context.of(sessionId));
    }

    void saveContext(Context context) {
        contextRepository.save(context);
    }

}
