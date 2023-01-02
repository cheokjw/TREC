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

        val que6: Question = Question(6, "About how long does it take a Styrofoam cup to decompose?",
            "10 years",
            "2 months",
            "400 years",
            "200 years",
            3,
            "It only takes an orange peel six months to decompose"
        )
        questionsList.add(que6)

        val que7: Question = Question(7, "According to the World Health Organization, what is the most polluted city in the world?",
            "L.A California",
            "Mexico City",
            "New Delhi India",
            "Shanghai China",
            2,
            "Mexico City has held the dubious distinction for at least four years in a row"
        )
        questionsList.add(que7)

        val que8: Question = Question(8, "What is the most common type of debris that litters our oceans?",
            "Bags",
            "Plastic beverage bottles",
            "Cigarettes",
            "Food packaging",
            3,
            "Volunteers for the International Coastal Cleanup removed nearly two million cigarettes"
        )
        questionsList.add(que8)

        val que9: Question = Question(9, "Three Mile Island was the site of what disaster?",
            "The worst nuclear explosion in the world",
            "The worst forest fires in US history",
            "The worst accident in US nuclear reactor history",
            "The worst oil spill in US history",
            3,
            "coolant (the fluid that keeps a machine cool) escaped from the reactor core"
        )
        questionsList.add(que9)

        val que10: Question = Question(10, "What is the most common type of trash thrown away by Americans?",
            "Paper products and cardboard",
            "Metals",
            "Glass",
            "Plastics",
            4,
            "Paper products and cardboard account for about 39% of all trash."
        )
        questionsList.add(que10)






        return questionsList
    }
}