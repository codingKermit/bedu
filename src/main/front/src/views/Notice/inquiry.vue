<template>
  <p id="Cschead">1:1 문의하기</p>
  <div class="card">
    <form @submit="inquiryWrite()">
      <div>
        <b-form-select id="selectbox" v-model="selected" :options="options"></b-form-select>
        <label id="title" for="title">제목</label>
        <b-form-input placeholder="제목을 입력해주세요" id="titletext" v-model="form.title" ref="title"></b-form-input>
      </div>
      <div>
        <label id="textarea" for="content">질문 내용</label>
        <div>
          <b-form-textarea id="textarea-rows" v-model="form.content" placeholder="내용을 작성해주세요" ref="content"
            rows="7"></b-form-textarea>
        </div>
      </div>
      <div>
        <b-button type="submit" id="commit">저장</b-button>
        <b-button id="list" href="#">목록으로</b-button>
      </div>
    </form>
  </div>
</template>

<script>
import '@/assets/css/inquiryStyle.css';
import router from '@/router';
export default {

  name: 'inquiryWrite',
  data() {
    return {

      selected: null,
      options: [
        { value: null, text: '상담유형선택' },
        { value: 'a', text: 'This is First option' },
        { value: 'b', text: 'Selected Option' },
        { value: { C: '3PO' }, text: 'This is an option with object value' },
        { value: 'd', text: 'This one is disabled', disabled: true }
      ],

      form: {
        userName: '',
        title: '',
        content: '',
      },
      userlist: [],
    };
  },

  mounted() {
    const nick = this.$store.getters.getNickname;
    if (nick === '' || nick === null) {
      this.$swal('Error', '로그인을 해주세요!');
      router.push({
        name: "login"
      })
      return;
    }
    this.getUserId();
  },

  created() {
    const nick = this.$store.getters.getNickname;
    if (nick === '' || nick === null) {
      this.$swal('Error', '로그인을 해주세요!');
      router.push({
        name: "login"
      })
    }
  },

  methods: {

    getUserId() {
      const nickname = this.$store.getters.getNickname;

      this.$axiosSend('get', 'api/inquiry/getUserId', {
        userName: nickname
      }).then(res => {
        
        this.userlist = res.data;
        for (var i = 0; i < this.userlist.length; i++) {
          this.form.userName = this.userlist[i].regId;
        }
      })
        .catch((error) => {
          this.$swal('Error', '회원아이디가 정상적으로 불러오지 않았습니다.', error);
        })

    },

    inquiryWrite() {

      if (this.form.title == null || this.form.title == "") {
        this.$swal({
          title: 'warning!',
          text: "제목을 입력해주세요",
          type: 'warning',
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
        })
        return;
      }
      const form = new FormData();

      form.append("userName", this.form.userName);
      form.append("title", this.form.title);
      form.append("content", this.form.content);

      this.$axiosSend('post', '/api/inquiry/inquiryWrite', this.form)
        .then((response) => {
          if (response.data === 1) {
            this.$swal('Success', '작성완료!', 'success'),
              router.push({
                name: "/"
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

