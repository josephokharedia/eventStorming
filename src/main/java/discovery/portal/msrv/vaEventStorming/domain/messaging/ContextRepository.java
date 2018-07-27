package discovery.portal.msrv.vaEventStorming.domain.messaging;

import java.util.Optional;

interface ContextRepository {

    Optional<Context> findBySessionId(String sessionId);

    void save(Context context);
}
