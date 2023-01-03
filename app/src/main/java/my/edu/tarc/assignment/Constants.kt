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

        val que11: Question = Question(11, "Which of the following is NOT a greenhouse gas?",
        "Carbon dioxide",
            "Water vapor",
            "Methane",
            "Chlorofluorocarbons",
            4,
            "Greenhouse gases are gases that trap heat in the Earth's atmosphere"
        )
        questionsList.add(que11)

        val que12: Question = Question(12, "What is the primary cause of ocean acidification?",
        "Overfishing",
        "Climate change",
        "Pollution",
        "Deforestation",
        2,
        "Ocean acidification is the process by which the ocean's pH decreases")
        questionsList.add(que12)

        val que13: Question = Question(13, "Which of the following is NOT a renewable energy source?",
        "Solar power",
        "Hydroelectric power",
        "Wind power",
        "Nuclear power",
        4,
            "Nuclear power, on the other hand, is generated through the use of uranium, which is a finite resource that cannot be replenished."
        )
        questionsList.add(que13)

        val que14: Question = Question(14, "What is the primary cause of deforestation?",
        "Urbanization",
        "Agriculture",
        "Logging",
        "Natural disasters",
        2,
        "Agriculture is the primary cause of deforestation, as forests are often cleared to create farmland")
        questionsList.add(que14)

        val que15:Question = Question(15, "Which of the following is NOT a way to reduce your carbon footprint",
        "Using public transportation",
        "Planting trees",
        "Using energy-efficient appliances",
        "Flying in airplanes",
        2,
        "Some ways to reduce your carbon footprint include using public transportation, using energy-efficient appliances")
        questionsList.add(que15)

        val que16:Question = Question(16, "Which of the following is NOT a cause of soil erosion?",
        "Overgrazing",
        "Deforestation",
        "Urbanization",
        "Lack of precipitation",
        4,
        "Soil erosion is the process by which soil is worn away or removed from the surface of the Earth")
        questionsList.add(que16)

        val que17:Question = Question(17, "What is the main cause of climate change?",
        "Deforestation",
        "Overpopulation",
        "Pollution",
        "The sun's activity",
        3,
        "Climate change is primarily caused by the burning of fossil fuels, such as coal, oil, and gas")
        questionsList.add(que17)

        val que18:Question = Question(18, "What is the process called when rainwater is not absorbed by the ground and instead runs off into streams and rivers?",
        "Flooding",
        "Erosion",
        "Runoff",
        "Siltation",
        3,
        "Runoff occurs when rainwater is not absorbed by the ground and instead flows over the surface, carrying soil and other contaminants")
        questionsList.add(que18)

        val que19: Question = Question(19, "What is the main cause of air pollution?",
        "Natural disasters",
        "The use of fossil fuels",
        "Pesticides and herbicides",
        "All of the above",
        2,
        "It releases a variety of harmful pollutants into the air, including carbon dioxide, nitrogen oxides")
        questionsList.add(que19)

        val que20: Question = Question(20, "What is the process called when plants and animals become extinct?",
        "Extinction",
        "Endangered",
        "Threatened",
        "All of the above",
        1,
        "Extinction is the process by which plants and animals become extinct, meaning they are no longer found on Earth")
        questionsList.add(que20)


        return questionsList
    }
}