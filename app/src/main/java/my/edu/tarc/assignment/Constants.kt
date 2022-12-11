package my.edu.tarc.assignment

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1: Question = Question(1, "What country consumes the most energy in the world?",
            "Russia",
            "China",
            "United States",
            "Canada",
            3,
            "The U.S. uses about 5% more energy than the world's second-largest energy consumer, China"
        )
        questionsList.add(que1)


        val que2: Question = Question(2, "What country produces the most energy in the world?",
            "Iraq",
            "China",
            "United States",
            "Russia",
            2,
            "China produces the most, about 79.1 quadrillion Btu of energy a year."
        )
        questionsList.add(que2)

        val que3: Question = Question(3, "What is the leading source of energy in the United States?",
            "Coal",
            "Oil",
            "Nuclear power",
            "Natural gas",
            2,
            "Oil provides the U.S with about 40% of its energy"
        )
        questionsList.add(que3)

        val que4: Question = Question(4, "Which of the following sources of energy in NOT renewable?",
            "Petroleum",
            "Hydropower",
            "Biomass",
            "Solar power",
            1,
            "Petroleum is a fossil fuel. Coal and natural has are other examples of fossil fuels"
        )
        questionsList.add(que4)

        val que5: Question = Question(5, "How much of the world's water is available for human use",
            "97 %",
            "23 %",
            "3 %",
            "Less than 1 %",
            4,
            "Only about one-third of 1% of all water on Earth is available for human use"
        )
        questionsList.add(que5)





        return questionsList
    }
}