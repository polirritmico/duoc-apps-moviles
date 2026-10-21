package cl.duoc.aprendiendoturco.data

import cl.duoc.aprendiendoturco.data.demo.DemoQuizDataSource
import cl.duoc.aprendiendoturco.model.QuizQuestion

class QuizRepository(
    private val quizDao: DemoQuizDataSource = DemoQuizDataSource()
) {
    // TODO: rusar una db y
    fun obtenerPregunta(numeroPregunta: Int, caminoAnterior: String?): QuizQuestion {
        return quizDao.obtenerPregunta(numeroPregunta, caminoAnterior)
    }
}