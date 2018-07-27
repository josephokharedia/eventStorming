package discovery.portal.msrv.vaEventStorming.domain.messaging;

interface MessageOutputFilter {

    MessageOutput doFilter(MessageOutput messageOutput);
}
