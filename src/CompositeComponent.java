
import java.util.ArrayList;
import java.util.List;


public class CompositeComponent extends Component {
    private List<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public void login() {
        // Implement the login behavior for the CompositeComponent
        // Iterate through the components and invoke their login methods
        for (Component component : components) {
            component.login();
        }
    }
} 

