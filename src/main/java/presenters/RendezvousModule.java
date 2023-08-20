package presenters;

import cabinet.CabinetService;
import cabinet.CabinetServiceImpl;
import com.google.inject.AbstractModule;
import rendezvous.RendezvousService;
import rendezvous.RendezvousServiceImpl;
import views.RendezvousController;
import views.RendezvousControllerImpl;
import views.addRendezvousController;
import views.addRendezvousControllerImpl;

public class RendezvousModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(RendezvousService.class).to(RendezvousServiceImpl.class);
        bind(RendezvousController.class).to(RendezvousControllerImpl.class);
        bind(RendezvousPresenter.class).to(RendezvousPresenterImpl.class);

        bind(CabinetService.class).to(CabinetServiceImpl.class);
        bind(addRendezvousController.class).to(addRendezvousControllerImpl.class);

    }
}
