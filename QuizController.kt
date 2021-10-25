import java.io.File

class QuizController {
    val questions = arrayListOf<Question>()

    init{
        val lines = File("kerdesek.txt").readLines()

        var answer = arrayListOf<String>()

        var text:String = " "
        var question:Question = Question(text, answer)
        val sor = lines.size-1

        for(i in 0..sor){
            if(i%5 == 0){
                answer = arrayListOf<String>()
                text = lines[i]
            }
            else{
                answer.add(lines.get(i))
            }
            if(i%5 == 4){
                question = Question(text, answer)
                questions.add(question)
            }
        }
        println(questions)
    }

    fun randomizeQuestions():Unit{
        questions.shuffle()
    }

    fun doQuiz(d:Int):Unit{
        val copy = questions.clone() as ArrayList<Question>
        println(copy.get(1).answer.first())

        randomizeQuestions()

        val list_latter = arrayListOf<String>("A","B","C","D")
        var correct = 0
        var total = 0

        for(i in 0..d){
            val question = questions.get(i).text
            var j = i+1
            println("$j." + question)

            val result = list_latter.zip(questions.get(i).answer.shuffled()){a,b->"$a.$b"}
            total++
            result.forEach{println(" $it")}

            var latter:String?
            while(true){
                print("Enter the correct answer:")
                latter=readLine()

                if(latter == questions.get(i).answer.first()){
                    println("Correct!")
                    correct++
                    break
                }
                else{
                    println("Sorry!")
                    break
                }
            }
        }
        println("Correct/Total question:$correct/$total")

    }

}