package org.ligi.intento.etc

import org.ligi.intento.App
import org.ligi.intento.DaggerTestComponent

class TestApp : App() {

   override fun createComponent() = DaggerTestComponent.builder().testModule(TestModule(this)).build()

}
