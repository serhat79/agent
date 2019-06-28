<template>
  <div>
    <div class="row">
      <div class="col-3 offset-7">
        <h5><a href="/#" v-on:click="goMainPage">Ana Sayfa</a></h5>
      </div>
    </div>
    <div class="pt-3" style="margin-left: 30%;margin-right: 30%">
      <basic-vue-chat :initialFeed="feedArr" @newOwnMessage="onSend" title="HVL ASISTAN"></basic-vue-chat>
    </div>
  </div>
</template>

<script>
import BasicVueChat from 'basic-vue-chat'
import axios from 'axios'
import router from '@/router'

const baseURI = 'http://localhost:8080/api/predict'

export default {
  name: 'ChatPage',
  components: {BasicVueChat},
  component: {
    BasicVueChat
  },
  data () {
    return {
      feedArr: [
        {
          id: 1,
          author: 'Hvl-Asist',
          contents: 'Merhaba',
          date: new Date().toLocaleTimeString()
        },
        {
          id: 1,
          author: 'Hvl-Asist',
          contents: 'Nasıl yardımcı olabilirim?',
          date: new Date().toLocaleTimeString()
        }
      ]
    }
  },
  methods: {
    onSend: function (newMessage) {
      if (newMessage) {
        axios.post(baseURI, {question: newMessage}
        )
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
      }
    },
    goMainPage: function () {
      router.push('/')
    }
  }
}
</script>
