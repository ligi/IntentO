package org.ligi.intento.etc;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {TestModule.class})
public interface TestComponent extends AppComponent {


}
