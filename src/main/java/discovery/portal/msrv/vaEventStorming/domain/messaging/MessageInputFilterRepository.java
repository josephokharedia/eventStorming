package discovery.portal.msrv.vaEventStorming.domain.messaging;

import java.util.List;

interface MessageInputFilterRepository {

   List<MessageInputFilter> getMessageInputFiltersForAction(String action);
}
