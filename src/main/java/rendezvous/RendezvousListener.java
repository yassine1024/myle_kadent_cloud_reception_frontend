package rendezvous;

import java.util.EventListener;

public interface RendezvousListener extends EventListener {
    void handleRendezvousEvent(RendezvousEvent event);
}