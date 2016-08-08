package org.ligi.intento.etc;

import dagger.Component;
import javax.inject.Singleton;
import org.jetbrains.annotations.NotNull;
import org.ligi.intento.ChooserActivity;
import org.ligi.intento.IntroActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(@NotNull final IntroActivity introActivity);

    void inject(@NotNull final ChooserActivity chooserActivity);
}
