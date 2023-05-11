package presenters;


import com.google.inject.Inject;
import views.TemplateController;

public class TemplatePresenter {

    private final TemplateController templateController;

    @Inject
    public TemplatePresenter(TemplateController templateController){
        this.templateController= templateController;
    }

}
