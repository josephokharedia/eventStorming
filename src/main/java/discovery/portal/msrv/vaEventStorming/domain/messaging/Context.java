package discovery.portal.msrv.vaEventStorming.domain.messaging;

import lombok.*;
import lombok.experimental.Wither;

import java.util.HashMap;
import java.util.Map;

@Builder(toBuilder = true)
@Getter
@RequiredArgsConstructor(staticName = "of")
class Context {

    @NonNull
    String sessionId;
    String action;
    @Getter(AccessLevel.NONE)
    final Map<String, Object> attributes = new HashMap<>();
    Object payload;
    @Wither(AccessLevel.PACKAGE)
    boolean responseComplete;

    void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    Object getAttribute(String key) {
        return attributes.get(key);
    }

    static void responseComplete(){

    }
}
