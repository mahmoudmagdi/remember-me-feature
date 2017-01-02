package com.example;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

/**
 * @author Alejandro Duarte.
 */
public class PublicComponent extends CustomComponent {

    public PublicComponent() {
        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        Button button = new Button("Login", e -> onLogin(username.getValue(), password.getValue()));
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        FormLayout formLayout = new FormLayout(username, password, button);
        formLayout.setSizeUndefined();

        VerticalLayout layout = new VerticalLayout(formLayout);
        layout.setSizeFull();
        layout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
        setCompositionRoot(layout);
        setSizeFull();
    }

    private void onLogin(String username, String password) {
        if (Backend.login(username, password)) {
            VaadinUI ui = (VaadinUI) UI.getCurrent();
            VaadinSession.getCurrent().setAttribute("username", username);
            ui.showPrivateComponent();
        } else {
            Notification.show("Invalid credentials (for demo use: admin/password)", Notification.Type.ERROR_MESSAGE);
        }
    }

}
