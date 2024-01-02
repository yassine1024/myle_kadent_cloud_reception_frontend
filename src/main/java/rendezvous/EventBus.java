package rendezvous;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private final List<RendezvousListener> listeners = new ArrayList<>();

    public void addListener(RendezvousListener listener) {
        listeners.add(listener);
    }

    public void removeListener(RendezvousListener listener) {
        listeners.remove(listener);
    }

    public void fireEvent(RendezvousEvent event) {

        for (RendezvousListener listener : listeners) {
            System.out.println(event.getDate());
            listener.handleRendezvousEvent(event);
        }
    }
}
