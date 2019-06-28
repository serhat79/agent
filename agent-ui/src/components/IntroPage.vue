<template>
  <div class="text-center row">
    <div class="col-3 offset-3">
      <a href="/#" v-on:click="goChatPage">
        <img src="@/assets/asistan-logo.png">
      </a>
    </div>
    <div class="col-3 text-center pt-3">
      <a href="/#" v-on:click="goTrainingPage">
        <img src="@/assets/training-logo.png">
      </a>
    </div>
  </div>
</template>

<script>
import router from '@/router'

export default {
  name: 'IntroPage',
  components: {},
  data () {
    return {
      result: undefined,
      feedArr: [
        {
          id: 1,
          author: 'HvlAssist',
          contents: 'Selam',
          date: new Date().toLocaleTimeString()
        },
        {
          id: 1,
          author: 'HvlAssist',
          contents: 'Nasıl yardımcı olabilirim?',
          date: new Date().toLocaleTimeString()
        }
      ]
    }
  },
  methods: {
    goChatPage: function () {
      router.push('/chat')
    },
    goTrainingPage: function () {
      router.push('/trainingData')
    }
  }
}
</script>
