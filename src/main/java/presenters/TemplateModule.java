package presenters;

import cabinet.CabinetService;
import com.google.inject.AbstractModule;
import cabinet.CabinetServiceImpl;
import views.TemplateController;
import views.TemplateControllerImpl;

public class TemplateModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(CabinetService.class).to(CabinetServiceImpl.class);
        bind(TemplateController.class).to(TemplateControllerImpl.class);
    }
}
