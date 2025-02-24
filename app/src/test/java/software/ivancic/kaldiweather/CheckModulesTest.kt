package software.ivancic.kaldiweather

import org.junit.Test

class CheckModulesTest : KoinTest {

    @Test
    fun checkAllModules() {
        appModule.verify()
    }
}
