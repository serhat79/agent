import Vue from 'vue'
import Router from 'vue-router'
import IntroPage from '@/components/IntroPage'
import ChatPage from '@/components/ChatPage'
import TrainingDataInputPage from '@/components/TrainingDataInputPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'IntroPage',
      component: IntroPage
    },
    {
      path: '/chat',
      name: 'ChatPage',
      component: ChatPage
    },
    {
      path: '/trainingData',
      name: 'TrainingDataInputPage',
      component: TrainingDataInputPage
    }
  ]
})
