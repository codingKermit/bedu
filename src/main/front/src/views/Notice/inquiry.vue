<template>
  <p id="Cschead">1:1 문의하기</p>
  <div class="card">
    <form @submit.prevent="submitInquiry">
      <div>
        <b-form-select id="selectbox" v-model="selected" :options="options"></b-form-select>
        <label id="title" for="title">제목</label>
        <b-form-input id="titletext" v-model="text" placeholder="제목을 입력해주세요"></b-form-input>
      </div>
      <div>
        <label id="textarea" for="content">질문 내용</label>
        <div>
          <b-form-textarea id="textarea-rows" placeholder="문의하실 내용을 기입해주세요" rows="7"></b-form-textarea>
        </div>
      </div>
      <div>
        <label id="attach" for="attachment"></label>
        <input type="file" id="attachment" @change="handleFileChange">
      </div>
      <div>
        <b-button id="list" href="#">목록으로</b-button>
        <b-button id="commit">저장</b-button>
      </div>
    </form>
  </div>
</template>

<script>
import '@/assets/css/inquiryStyle.css';
export default {
  data() {
    return {
      inquiry: {
        title: '',
        content: '',
        attachment: null
      },
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
  methods: {
    handleFileChange(event) {
      this.inquiry.attachment = event.target.files[0];
    },
    submitInquiry() {
      // 제출 로직을 작성합니다.
      // this.inquiry 객체에 저장된 제목, 내용, 첨부 파일을 이용하여 서버로 전송합니다.
      console.log(this.inquiry); // 예시로 콘솔에 출력하도록 설정했습니다.

      // 서버로의 실제 전송 로직을 추가해야 합니다.
      // 예를 들어, axios를 사용하여 HTTP 요청을 보낼 수 있습니다.
      // axios.post('/api/inquiry', this.inquiry).then(response => {
      //   console.log(response.data);
      // }).catch(error => {
      //   console.error(error);
      // });
    }
  }
};
</script>

