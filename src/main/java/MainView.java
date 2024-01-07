import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class MainView extends H1 {

    public MainView() {
        setText(getTranslation("hello.world"));
    }
}
