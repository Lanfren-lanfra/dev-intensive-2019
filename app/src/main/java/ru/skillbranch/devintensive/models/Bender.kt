package ru.skillbranch.devintensive.models

class Bender(
    var status: Status = Status.NORMAL,
    var question: Question = Question.NAME,
    var failCounter: Int = 0
) {
    private val FAILS_MAX = 3


    fun askQuestion():String  = when (question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {

        //валидация ответа перед проверкой
        val (isValidAnswer, invalidMsg) = validateAnswer(answer)
        if (!isValidAnswer){
            return "${invalidMsg}\n${question.question}" to status.color
        }


        return if (question.answers.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            if (++failCounter > FAILS_MAX){
                question = Question.NAME
                status = Status.NORMAL
                failCounter = 0
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }
        }
    }

    private fun validateAnswer(answer: String): Pair<Boolean, String?> {

        val concreteValidation = when (question) {
            Question.NAME -> AnswerValidation.VNAME
            Question.PROFESSION -> AnswerValidation.VPROFESSION
            Question.MATERIAL -> AnswerValidation.VMATERIAL
            Question.BDAY -> AnswerValidation.VBDAY
            Question.SERIAL -> AnswerValidation.VSERIAL
            else -> null
        }

        if (concreteValidation == null){  //т.е. случай question == Question.IDLE
            return true to null
        }

        val isValid = Regex(concreteValidation.regex).matches(answer)

        return if (isValid)
            true to null
        else
            false to concreteValidation.msg
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)) {
            override fun nextStatus(): Status = WARNING
        },
        WARNING(Triple(255, 120, 0)) {
            override fun nextStatus(): Status = DANGER
        },
        DANGER(Triple(255, 60, 60)) {
            override fun nextStatus(): Status = CRITICAL
        },
        CRITICAL(Triple(255, 0, 0)) {
            override fun nextStatus(): Status = CRITICAL
        };

        abstract fun nextStatus():Status
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "Bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        },;

        abstract fun  nextQuestion():Question
    }

    enum class AnswerValidation( val regex:String, val msg:String ){
        VNAME("[A-Z|А-Я]{1}.*", "Имя должно начинаться с заглавной буквы"),
        VPROFESSION("[a-z|а-я]{1}.*", "Профессия должна начинаться со строчной буквы"),
        VMATERIAL("[^0-9]+", "Материал не должен содержать цифр"),
        VBDAY("[0-9]+", "Год моего рождения должен содержать только цифры"),
        VSERIAL("[0-9]{7}", "Серийный номер содержит только цифры, и их 7");

    }
}

