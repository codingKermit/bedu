<template>
  <div class="d-flex">
    <div class="cscView">
      <CscCategory :currentTab="'inquiry'"></CscCategory>
    </div>
    <div id="cscMain">
      <div>
        <div>
          <div id="cscBoradSearch">
            <div @submit="qnasearch()" class="searchForm">
              <font-awesome-icon id="csc-search-icon" :icon="['fas', 'magnifying-glass']" />
              <input class="cscviewkeyword" v-model="form.keyword" ref="keyword" @keyup.enter="qnasearch">
              <b-button id="csc-writepath-btn" @click="goToInquiryPage">
                <font-awesome-icon :icon="['fas', 'pencil']" />
                문의하기
              </b-button>
            </div>
          </div>
        </div>
        <h2>이용 문의 </h2>
      </div>
      <table id="cscboard-table">
        <thead>
          <tr>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일자</th>
            <th>답변여부</th>
          </tr>
        </thead>
        <tbody>
          <tr :key="index" v-for="(inquiry, index) in paginatedInquiryList">
            <td id="cscboard-table-tds" @click="password(inquiry)">
              <b-link class="text-start text-body">
                <font-awesome-icon :icon="['fas', 'lock']" /> {{ inquiry.title }}
              </b-link>
            </td>
            <td>
              {{ inquiry.userName }}</td>
            <td>{{ formatDateTime(inquiry.regDate) }}</td>
            <td id="answer">
              <p v-if="inquiry.replyCnt > 0">답변완료</p>
              <p v-else>답변대기</p>
            </td>
          </tr>
        </tbody>
      </table>
      <div id="pagination">
        <b-pagination v-model="currentPage" :total-rows="inquirylist.length" :per-page="pageSize"
          size="lg"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script>

import CscCategory from '@/components/CscCategory.vue';
//import InquiryDetail from '@/Notice/InquiryDetail.vue';

import '@/assets/css/CscViewStyle.css';
export default {
  name: 'inquirylist',

  data() {
    return {
      inquirylist: [
      ],

      form: {
        keyword: '',
      },
      currentPage: 1,
      replyCnt: '',
    };

  },

  created() {
    this.qnasearch();
  },

  components: {
    CscCategory
  },

  mounted() {
  },

  computed: {
    paginatedInquiryList() {

      // 현재 페이지에 해당하는 데이터의 시작과 끝 인덱스를 계산합니다.
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;

      // 현재 페이지에 해당하는 데이터를 추출하여 반환합니다.
      return this.inquirylist.slice(startIndex, endIndex);
    },
  },

  methods: {

    goToInquiryPage() {
      window.location.href = "/inquiry"; // 원하는 문의 페이지의 URL로 변경해주세요
    },

    qnasearch() {
      this.$axiosSend('post', '/api/inquiry/inquiryList')
        .then(res => {
          console.log(res)
          this.inquirylist = res.data;

          // 페이징 처리를 위한 변수들을 설정합니다.
          this.pageSize = 10; // 한 페이지에 보여줄 게시물 수를 설정합니다.
          this.totalPages = Math.ceil(this.inquirylist.length / this.pageSize); // 총 페이지 수를 계산합니다.
          this.currentPage = 1; // 현재 페이지 번호를 초기화합니다.

          // 페이징 처리된 데이터를 가져옵니다.
          this.paginatedInquiryList = this.inquirylist.slice(0, this.pageSize);

        })
        .catch(error => {
          alert(error);
        });
    },

    password(inquiry) {
      this.$swal({
        title: '비밀번호를 입력하세요',
        html: '<input id="test" type="password">',
        icon: 'info',
        confirmButtonText: '확인',
        cancelButtonText: '취소',
        showCancelButton: true,
        inputAttributes: {
          autocapitalize: 'off',
        },

      })
        .then((result) => {
          if (result.isConfirmed) {
            // 사용자가 입력한 비밀번호
            const userInput = document.getElementById("test").value;
            if (userInput !== null) {
              // 입력받은 비밀번호와 inquiry.password 비교
              if (userInput == inquiry.password) {
                this.$routerPush('inquiryDetail', { vocNum: inquiry.vocNum }, true)
              } else {
                this.$swal({
                  title: '올바른 비밀번호를 입력해주세요',
                  icon: 'error',
                })
              }
            }
          }
        })

    },

    formatDateTime(value) {
      // value는 날짜 값입니다
      const now = new Date();
      const date = new Date(value);

      const diffInMilliseconds = now - date;
      const diffInSeconds = Math.floor(diffInMilliseconds / 1000);
      const diffInMinutes = Math.floor(diffInSeconds / 60);
      const diffInHours = Math.floor(diffInMinutes / 60);
      const diffInDays = Math.floor(diffInHours / 24);

      if (diffInDays > 0) {
        return `${diffInDays}일 전`;
      } else if (diffInHours > 0) {
        return `${diffInHours}시간 전`;
      } else if (diffInMinutes > 0) {
        return `${diffInMinutes}분 전`;
      } else {
        return '방금 전';
      }
    },
  },
};
</script>