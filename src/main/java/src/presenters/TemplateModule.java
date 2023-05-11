package src.presenters;

import com.google.inject.AbstractModule;
import src.cabinet.CabinetService;
import src.cabinet.CabinetServiceImpl;
import views.TemplateController;
import views.TemplateControllerImpl;

public class TemplateModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(CabinetService.class).to(CabinetServiceImpl.class);
        bind(TemplateController.class).to(TemplateControllerImpl.class);
    }
}
