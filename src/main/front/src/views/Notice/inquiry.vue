<template>
  <p id="Cschead">1:1 문의하기</p>
  <div class="card">
    <form @submit.prevent="submitInquiry">
      <div>
        <b-form-select id="selectbox" v-model="selected" :options="options"></b-form-select>
        <label id="title" for="title">제목</label>
        <b-form-input placeholder="제목을 입력해주세요" class="my-3 qna-write-title" id="titletext" v-model="form.title"
          ref="title"></b-form-input>
      </div>
      <div>
        <label id="textarea" for="content">질문 내용</label>
        <div>
          <b-form-textarea id="textarea-rows" v-model="form.content" placeholder="내용을 작성해주세요" ref="content"
            rows="7"></b-form-textarea>
        </div>
      </div>
      <!-- <div>
        <label id="attach" for="attachment"></label>
        <input type="file" id="attachment" @change="handleFileChange">
      </div>-->
      <div>
        <b-button id="list" href="#">목록으로</b-button>
        <b-button type="submit" class="bedu-bg-custom-blue qna-writebtn" id="commit">저장</b-button>
      </div>
    </form>
  </div>
</template>

<script>
import '@/assets/css/inquiryStyle.css';
import router from '@/router';
export default {

  name: 'inquaryWrite',
  data() {
    return {
      form: {
        user_name: '',
        title: '',
        content: '',
      },

      userlist:[],

      selected: null,
      options: [
        { value: null, text: '상담유형선택' },
        { value: 'a', text: 'This is First option' },
        { value: 'b', text: 'Selected Option' },
        { value: { C: '3PO' }, text: 'This is an option with object value' },
        { value: 'd', text: 'This one is disabled', disabled: true }
      ]
    };
  },
  mounted() {
    const nick = this.$store.getters.getNickname;
    if (nick === '' || nick === null) {
      this.$swal('Error', '로그인을 해주세요!');
      router.push({
        name: "main"
      })
    }
    this.getUserId();
  },

  created() {
    const nick = this.$store.getters.getNickname;
    if (nick === '' || nick === null) {
      this.$swal('Error', '로그인을 해주세요!');
      router.push({
        name: "main"
      })
    }
  },
  methods: {

    getUserId() {
      const nickname = this.$store.getters.getNickname;

      this.$axiosSend('get', '/api/qna/getUserId', {
        userName: nickname
      }).then(res => {

        this.userlist = res.data;
        for (var i = 0; i < this.userlist.length; i++) {
          this.form.user_name = this.userlist[i].user_id;
        }

      })
        .catch((error) => {
          this.$swal('Error', '회원아이디가 정상적으로 불러오지 않았습니다.', error);
        })

    },

    qnaWrite() {

      if (this.form.title == null || this.form.title == "") {
        this.$swal({
          title: 'warning!',
          text: "제목을 입력해주세요",
          type: 'warning',
          icon: 'warning',
          didClose: () => {
            this.$refs.title.focus()
          }
        })
        return;
      }
      if (this.form.content == null || this.form.content == "") {
        this.$swal({
          title: 'warning!',
          text: "내용을 입력하세요",
          type: 'warning',
          icon: 'warning',
        })
        return;
      }
      const form = new FormData();

      form.append("user_name", this.form.user_name);
      form.append("title", this.form.title);
      form.append("content", this.form.content);

      this.$axiosSend('post', '/api/qna/qnaWrite', this.form)
        .then((response) => {
          if (response.data === 1) {
            this.$swal('Success', '작성완료!', 'success'),
              router.push({
                name: "inquriylist"
              })
          }
        })
        .catch((error) => {
          this.$swal('Error', '게시글이 정상적으로 작성되지 않았습니다', error)
        })
    }
  }
};
</script>

