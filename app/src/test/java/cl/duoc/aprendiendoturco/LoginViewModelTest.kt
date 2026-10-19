package cl.duoc.aprendiendoturco

import cl.duoc.aprendiendoturco.viewmodel.LoginViewModel
import org.junit.Assert.*
import org.junit.Test

class LoginViewModelTest {
    @Test
    fun aceptaLasDosCuentasDePrueba() {
        for (correo in listOf("ro.callealta@duocuc.cl", "ed.bray@duocuc.cl")) {
            val viewModel = LoginViewModel()
            viewModel.onCorreoChange(correo)
            viewModel.onClaveChange("hola1234")
            assertTrue(viewModel.validarLogin())
            assertEquals("", viewModel.estado.value.clave)
        }
    }

    @Test
    fun rechazaCamposVaciosYDatosIncorrectos() {
        val viewModel = LoginViewModel()
        assertFalse(viewModel.validarLogin())
        assertNotNull(viewModel.estado.value.error)
        viewModel.onCorreoChange("otro@duocuc.cl")
        viewModel.onClaveChange("hola1234")
        assertFalse(viewModel.validarLogin())
        viewModel.onCorreoChange("ro.callealta@duocuc.cl")
        viewModel.onClaveChange("incorrecta")
        assertFalse(viewModel.validarLogin())
    }

    @Test
    fun permiteCorregirElCorreoYLimpiarElError() {
        val viewModel = LoginViewModel()
        viewModel.validarLogin()
        viewModel.onCorreoChange(" RO.CALLEALTA@DUOCUC.CL ")
        assertNull(viewModel.estado.value.error)
        viewModel.onClaveChange("hola1234")
        assertTrue(viewModel.validarLogin())
    }
}
