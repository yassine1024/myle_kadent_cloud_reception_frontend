package presenters;

import com.google.inject.AbstractModule;
import rendezvous.RendezvousService;
import rendezvous.RendezvousServiceImpl;
import views.RendezvousController;
import views.RendezvousControllerImpl;

public class RendezvousModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(RendezvousService.class).to(RendezvousServiceImpl.class);
        bind(RendezvousController.class).to(RendezvousControllerImpl.class);
        bind(RendezvousPresenter.class).to(RendezvousPresenterImpl.class);

    }
}
