package io.cgrings.businessdate;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.modules.Main;

@ApplicationPath("/")
public class MyApplication extends Application {

	public static void main(String... args) throws Throwable {
        Main.main(args);
    }

}