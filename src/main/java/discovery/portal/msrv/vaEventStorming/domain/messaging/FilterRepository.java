package discovery.portal.msrv.vaEventStorming.domain.messaging;

import java.util.List;

interface FilterRepository {

    List<MessageInputFilter> getMessageInputFiltersByAction(String action);

    List<MessageOutputFilter> getMessageOutputFiltersByAction(String action);
}
