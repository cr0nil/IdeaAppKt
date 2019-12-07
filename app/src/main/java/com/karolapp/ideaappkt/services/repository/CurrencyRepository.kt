package com.karolapp.ideaappkt.services.repository

import android.util.Log
import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.model.ItemHome
import com.karolapp.ideaappkt.model.ListIconsCurrency
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.api.ApiService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class CurrencyRepository(private val apiService: ApiService) {
    fun getDetails(): Observable<ItemHome> {

//        val ans = getAnswers()
//        Log.i("wpada getDet", ans.toString())
        return Observable.zip(
            apiService.getCryptocurrency(),
            apiService.getIcons(),
            BiFunction<Rates, List<IconsCurrency>, ItemHome>
            { rates, iconsCurrency ->
//                Log.i("rates", rates.toString())
                Log.i("rates", iconsCurrency.size.toString())
                ItemHome(rates,iconsCurrency)
//

               // createHomeItemModel(rates, iconsCurrency)
            })
    }

//    private fun createHomeItemModel(cryptocurrency: Rates, iconsCurrency: ListIconsCurrency): ItemHome {
//        Log.i("items", cryptocurrency.toString())
//        Log.i("items2", iconsCurrency.toString())
//        return ItemHome(cryptocurrency, cryptocurrency)
//    }

//    private fun getAnswers(): Single<ListIconsCurrency> {
//        return apiService.getIcons()
//            .map { t: ListIconsCurrency ->
//                Log.i("icons", t.toString())
//                ListIconsCurrency(t.items)
//            }
//    }
//
//    private fun mapAnswersToAnswerViewModels(answers: List<IconsCurrency>): Single<List<IconsCurrency>> {
//        val ids = answers
//            .map { it.assets_id }
//            .joinToString(separator = ",")
////        val x :Single<ListIconsCurrency>
//         val questionsListModel = apiService.getCryptocurrency()
//        return questionsListModel.map { it -> mapI(answers) }
////            .map { it ->
////                addTitlesToAnswers(answers, questionListModel?.items ?: emptyList()) }
//    }
////
//    fun mapI(answers: List<IconsCurrency>): List<IconsCurrency> {
//    Log.i("anwser",answers.toString())
//        return answers.map { (answerId, questionId) ->
//            IconsCurrency(answerId, questionId)
//        }
//    }
//    private fun addTitlesToAnswers(answers: List<Answer>, questions: List<Question>) : List<AnswerViewModel> {
//        return answers.map { (answerId, questionId, score, accepted) ->
//            val question = questions.find { it.questionId == questionId }
//            AnswerViewModel(answerId, score, accepted, question?.title ?: "Unknown")
//        }
    // }
}