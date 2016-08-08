package org.ligi.intento.etc

import org.ligi.intento.App

class TestApp : App() {

   override fun createComponent() = DaggerTestComponent.builder().testModule(TestModule(this)).build()

}
