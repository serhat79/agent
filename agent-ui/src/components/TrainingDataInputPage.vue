<template>
  <div>
    <div class="row">
      <div class="col-3 offset-7">
        <h5><a href="/#" v-on:click="goMainPage">Ana Sayfa</a></h5>
      </div>
    </div>
    <div class="card pt-3">
      <h5 class="card-header info-color white-text text-center py-4">
        <strong>Veri Seti Girişi</strong>
      </h5>
      <form class="p-3">
        <div class="form-group">
          <label for="answerId">Cevap</label>
          <input type="text" v-model="answer" class="form-control" id="answerId">
        </div>
        <label for="questionId">Soru</label>
        <div class="form-group row">
          <div class="col-11">
            <input type="text" v-model="question" class="form-control" id="questionId">
          </div>
          <div class="col-1">
            <button type="button" class="btn btn-info" v-on:click="addQuestion">Ekle</button>
          </div>
        </div>
        <div v-for="(item, index) in questionArr" v-bind:key="index">
          <div class="alert alert-warning alert-dismissible fade show" role="alert">
            {{ item }}
            <button type="button" class="close" data-dismiss="alert" v-on:click="deleteQuestion(index)">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
        </div>
        <button type="button" class="btn btn-primary" v-on:click="addData">Eğitim Verisini Kaydet</button>
        <table class="table table-striped" v-if="dataArr.length > 0">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Cevap</th>
            <th scope="col"></th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(item, index) in dataArr" v-bind:key="index" v-on:click="rowClick(index)">
            <th scope="row">{{ index+1 }}</th>
            <td>{{ item.answer }}</td>
            <td>
              <button type="button" class="close" data-dismiss="alert" v-on:click="deleteDataItem(index)">
                <span aria-hidden="true">&times;</span>
              </button>
            </td>
          </tr>
          </tbody>
        </table>
        <div v-if="visibleQuestionTextArea">
          <div class="form-group">
            <label for="exampleFormControlTextarea1">'{{ this.answerTitle }}' Cevabına Ait Sorular</label>
            <textarea class="form-control" v-model="selectedItemQuestionString" id="exampleFormControlTextarea1" rows="3"></textarea>
          </div>
          <div class="pt-2">
            <button type="submit" v-on:click="saveChange" class="btn btn-primary">Değişiklikleri Kaydet</button>
          </div>
        </div>
        <div class="pt-5 text-center">
          <button type="submit" v-on:click="trainData" class="btn btn-primary">Verileri Eğit</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>

import axios from 'axios'
import router from '@/router'

const baseURI = 'http://localhost:8080/api/train'

export default {
  name: 'TrainingDataPage',
  components: {},
  data () {
    return {
      question: '',
      answer: '',
      questionArr: [],
      answerArr: [],
      dataArr: [],
      selectedItemQuestionString: undefined,
      answerTitle: '',
      visibleQuestionTextArea: false,
      selectedItemWithIndex: undefined
    }
  },
  methods: {
    addData: function () {
      if (this.canBeDataInput()) {
        let data = {
          answer: this.answer,
          questionList: this.questionArr
        }
        this.dataArr.push(data)
        this.questionArr = []
        this.answer = ''
        this.question = ''
      }
    },
    addQuestion: function () {
      if (this.canBeQuestionInput()) {
        this.questionArr.push(this.question)
        this.question = ''
        this.closeSelectedQuestionsTxtArea()
      }
    },
    deleteQuestion: function (index) {
      if (this.questionArr.lenth > 0) {
        this.questionArr.splice(index, 1)
      }
    },
    deleteDataItem: function (index) {
      if (this.dataArr.lenth > 0) {
        this.dataArr.splice(index, 1)
        this.closeSelectedQuestionsTxtArea()
      }
    },
    rowClick: function (paramIndex) {
      this.selectedItemQuestionString = ''
      this.selectedItemWithIndex = {
        index: paramIndex,
        item: this.dataArr[paramIndex]
      }
      this.answerTitle = this.selectedItemWithIndex.item.answer
      let tmpQuestions = this.selectedItemWithIndex.item.questionList
      tmpQuestions.forEach((question, index) => {
        if (tmpQuestions.length > index++) {
          this.selectedItemQuestionString = this.selectedItemQuestionString + question + '\n'
        } else {
          this.selectedItemQuestionString = this.selectedItemQuestionString + question
        }
      })
      this.visibleQuestionTextArea = true
    },
    saveChange: function () {
      let selectedItemIndex = this.selectedItemWithIndex.index
      let selectedItem = this.selectedItemWithIndex.item
      let changedQuestions = this.selectedItemQuestionString.split('\n')
      selectedItem.questionList = changedQuestions
      this.dataArr[selectedItemIndex] = selectedItem
      this.closeSelectedQuestionsTxtArea()
    },
    closeSelectedQuestionsTxtArea: function () {
      this.selectedItemWithIndex = undefined // secili olan obje arrayda guncellendikten sonra siliniyor
      this.visibleQuestionTextArea = false
    },
    trainData: function () {
      axios.post(baseURI, this.dataArr)
        .then((result) => {
          if (result && result != null) {
            let answer = {
              id: 1,
              author: 'HvlAssist',
              contents: result.data,
              date: new Date().toLocaleTimeString()
            }
            this.feedArr.push(answer)
          }
        })
    },
    canBeDataInput: function () {
      return this.answer !== '' && this.questionArr.length > 0
    },
    canBeQuestionInput: function () {
      return this.answer !== '' && this.question !== ''
    },
    goMainPage: function () {
      router.push('/')
    }
  }
}
</script>
